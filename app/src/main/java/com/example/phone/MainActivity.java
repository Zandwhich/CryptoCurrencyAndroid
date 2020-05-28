package com.example.phone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.phone.utility.currencies.CryptoCurrencies;
import com.example.phone.utility.currencies.FiatCurrencies;
import com.example.phone.utility.network.AbstractAPICall;
import com.example.phone.utility.network.CoinBase.CoinBaseBuy;
import com.example.phone.utility.network.NetworkUtils;
import com.example.phone.utility.network.errors.CryptoNotAccepted;
import com.example.phone.utility.network.errors.FiatNotAccepted;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public final class MainActivity extends AppCompatActivity {

    private TextView mResponseView;
    private ProgressBar mProgressBar;

    private ArrayList<AbstractAPICall> websites;

    private CryptoCurrencies currentCrypto;

    private FiatCurrencies currentFiat;

    /**
     * The function to call to refresh all of the APIs
     */
    private void refresh() {
        // TODO: Update this once we have the recycler view working
        for (AbstractAPICall website : this.websites) {
            new RefreshAsync().execute(website);
        }//end for
    }//end refresh()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mResponseView = findViewById(R.id.tv_response);
        this.mProgressBar = findViewById(R.id.pb_loading_indicator);

        this.websites = new ArrayList<>();
        this.websites.add(new CoinBaseBuy());

        this.currentCrypto = CryptoCurrencies.BTC;
        this.currentFiat = FiatCurrencies.USD;
    }//end onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }//end onCreateOptionsMenu()

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                this.refresh();
                break;

            default:
                return super.onOptionsItemSelected(item);
        }//end switch
        return true;
    }//end onOptionsItemSelected()

    /**
     * An Async Task to refresh websites to get their prices for cryptocurrencies
     */
    private class RefreshAsync extends AsyncTask<AbstractAPICall, Void, Double> {

        @Override
        protected void onPreExecute() {
            // TODO: When I've got the list in place, figure out a way to grab which entry in the
            //  list I'm referring to here, so I can use that later on in the doInBackground and
            //  onPostExecute methods

            mResponseView.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        }//end onPreExecute()

        /**
         * Hit the website
         * @param websites The APICalls (there is only one)
         * @return Returns the contents of the string, which is then to be
         */
        @Override
        protected Double doInBackground(AbstractAPICall... websites) {
            if (websites.length == 0) return (double) -1;

            try {
                String contents = NetworkUtils.connect(websites[0].buildURL(currentCrypto, currentFiat));
                return websites[0].extractPrice(contents);
            } catch (IOException | FiatNotAccepted | CryptoNotAccepted e) {
                // TODO: In the future, separate what the Fiat and Crypto exceptions
                //  and show it to the screen
                e.printStackTrace();
                return (double) -1;
            }//end try/catch
        }//end doInBackground()

        @Override
        protected void onPostExecute(Double s) {
            // TODO: Get rid of this in the future, as this will be caught somewhere else
            if (s == -1) mResponseView.setText("There was an error along the way... RIP");

            // TODO: Update this later
            else mResponseView.setText(s.toString());
            mProgressBar.setVisibility(View.INVISIBLE);
            mResponseView.setVisibility(View.VISIBLE);
        }//end onPostExecute()
    }//end RefreshAsync
}//end MainActivity
