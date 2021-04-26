package com.example.phone.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.phone.R;
import com.example.phone.activities.recyclerview.PriceAdapter;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.AbstractAPICall;
import com.example.phone.utility.network.endpoints.CoinBase.CoinBaseBuy;
import com.example.phone.utility.network.endpoints.CoinBase.CoinBaseSell;
import com.example.phone.utility.network.endpoints.CoinBase.CoinBaseSpot;
import com.example.phone.utility.network.endpoints.CoinCap;
import com.example.phone.utility.network.endpoints.CryptoCompare;

import java.util.ArrayList;

/**
 * The main activity of the app, that displays the list of all of the prices
 */
public final class MainActivity
        extends AppCompatActivity
        implements CurrencyActivity, PriceAdapter.PriceAdapterOnClickHelper {

    private TextView mResponseView;
    private ProgressBar mProgressBar;

    private ArrayList<AbstractAPICall> websites;

    private PriceAdapter mPriceAdapter;
    private RecyclerView mRecyclerView;

    /**
     * {@inheritDoc}
     */
    @Override
    public CryptoCurrency getCurrentCrypto() {
        int currentCryptoAbbreviated
                = getSharedPreferences(OptionsActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE)
                .getInt(OptionsActivity.CRYPTO_SELECTED, CryptoCurrency.DEFAULT_CRYPTO.getAbbreviatedName());
        CryptoCurrency currentCrypto = CryptoCurrency.getCryptocurrencyFromAbbreviatedName(currentCryptoAbbreviated);

        return (currentCrypto == null) ? CryptoCurrency.DEFAULT_CRYPTO : currentCrypto;
    }//end getCurrentCrypto()

    /**
     * {@inheritDoc}
     */
    @Override
    public FiatCurrency getCurrentFiat() {
        int currentFiatAbbreviated
                = getSharedPreferences(OptionsActivity.SHARED_PREFERENCES, Context.MODE_PRIVATE)
                .getInt(OptionsActivity.FIAT_SELECTED, FiatCurrency.DEFAULT_FIAT.getAbbreviatedName());
        FiatCurrency currentFiat = FiatCurrency.getFiatCurrencyFromAbbreviatedName(currentFiatAbbreviated);

        return (currentFiat == null) ? FiatCurrency.DEFAULT_FIAT : currentFiat;
    }//end getCurrentFiat()

    /**
     * {@inheritDoc}
     */
    @Override
    public double getCurrencyPrice(int orderInList) {
        return this.websites.get(orderInList).getPrice();
    }//end getCurrencyPrice()

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCurrencyName(int orderInList) {
        return this.websites.get(orderInList).getName();
    }//end getCurrencyName()

    /**
     * The function to call to refresh all of the APIs
     */
    private void refresh() {
        // TODO: Update this once we have the recycler view working
        this.mResponseView.setText("");
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
        this.websites.add(new CoinBaseBuy(this));
        this.websites.add(new CoinBaseSell(this));
        this.websites.add(new CoinBaseSpot(this));
        this.websites.add(new CryptoCompare(this));
        this.websites.add(new CoinCap(this));

        // Doing these for testing purposes:
        this.websites.add(new CoinBaseBuy(this));
        this.websites.add(new CoinBaseSell(this));
        this.websites.add(new CoinBaseSpot(this));
        this.websites.add(new CryptoCompare(this));
        this.websites.add(new CoinCap(this));
        this.websites.add(new CoinBaseBuy(this));
        this.websites.add(new CoinBaseSell(this));
        this.websites.add(new CoinBaseSpot(this));
        this.websites.add(new CryptoCompare(this));
        this.websites.add(new CoinCap(this));
        this.websites.add(new CoinBaseBuy(this));
        this.websites.add(new CoinBaseSell(this));
        this.websites.add(new CoinBaseSpot(this));
        this.websites.add(new CryptoCompare(this));
        this.websites.add(new CoinCap(this));
        this.websites.add(new CoinBaseBuy(this));
        this.websites.add(new CoinBaseSell(this));
        this.websites.add(new CoinBaseSpot(this));
        this.websites.add(new CryptoCompare(this));
        this.websites.add(new CoinCap(this));

        this.mPriceAdapter = new PriceAdapter((this.websites.size()), this, this);

        this.mRecyclerView = findViewById(R.id.recycler_view);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setAdapter(this.mPriceAdapter);
    }//end onCreate()

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }//end onCreateOptionsMenu()

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.refresh) {
            this.refresh();

            return true;
        } else if (item.getItemId() == R.id.options) {
            launchOptionsPage();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected()

    @Override
    public void onPriceAdapterClick(int position, View view) {
        this.launchAboutPage(this.websites.get(position).getClassName());
    }//end priceAdapterOnClick()

    /**
     * Launches the about page for the given API call
     * @param apiCall The given API call
     */
    private void launchAboutPage(String apiCall) {
        Intent intent = new Intent(this, AboutActivity.class);
        intent.putExtra(AboutActivity.API_KEY, apiCall);
        startActivity(intent);
    }//end launchAboutPage()

    private void launchOptionsPage() {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    /**
     * An Async Task to refresh websites to get their prices for cryptocurrencies
     */
    private class RefreshAsync extends AsyncTask<AbstractAPICall, Void, String> {

        @Override
        protected void onPreExecute() {
            // TODO: When I've got the list in place, figure out a way to grab which entry in the
            //  list I'm referring to here, so I can use that later on in the doInBackground and
            //  onPostExecute methods

            mResponseView.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.INVISIBLE);
            mProgressBar.setVisibility(View.VISIBLE);
        }//end onPreExecute()

        /**
         * Hit the website
         * @param websites The APICalls (there is only one)
         * @return Returns the contents of the string, which is then to be
         */
        @Override
        protected String doInBackground(AbstractAPICall... websites) {
            if (websites.length == 0) return null;

            websites[0].updatePrice();
            return websites[0].getName() + ": " + websites[0].getPrice() + "\n";
        }//end doInBackground()

        @Override
        protected void onPostExecute(String s) {
            // TODO: Get rid of this in the future, as this will be caught somewhere else
            if (s == null) mResponseView.append("There was an error along the way... RIP\n");

            mRecyclerView.getAdapter().notifyDataSetChanged();

            mProgressBar.setVisibility(View.INVISIBLE);
            mResponseView.setVisibility(View.INVISIBLE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }//end onPostExecute()
    }//end RefreshAsync
}//end MainActivity
