package com.example.phone.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.phone.R;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.AbstractAPICall;
import com.example.phone.utility.network.endpoints.*;
import com.example.phone.utility.network.endpoints.CoinBase.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The class that controls the 'About' page
 */
final public class AboutActivity extends AppCompatActivity {

    public static final String API_KEY = "API";

    private CryptoCurrency[] acceptedCryptoCurrencies;
    private FiatCurrency[] acceptedFiatCurrencies;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView TVName = findViewById(R.id.tv_about_name);
        TextView TVSummary = findViewById(R.id.tv_about_summary);
        TextView TVAcceptedCryptos = findViewById(R.id.tv_about_accepted_cryptos);
        TextView TVAcceptedFiats = findViewById(R.id.tv_about_accepted_fiats);

        switch (getIntent().getStringExtra(AboutActivity.API_KEY)) {
            case AbstractCoinBase.NAME:
                this.acceptedCryptoCurrencies = AbstractCoinBase.ACCEPTED_CRYPTOCURRENCIES;
                this.acceptedFiatCurrencies = AbstractCoinBase.ACCEPTED_FIAT_CURRENCIES;

                TVName.setText(getText(R.string.coinbase_name));
                TVSummary.setText(getText(R.string.coinbase_summary));

                break;
            case CoinCap.NAME:
                this.acceptedCryptoCurrencies = CoinCap.ACCEPTED_CRYPTO_CURRENCY;
                this.acceptedFiatCurrencies = CoinCap.ACCEPTED_FIAT_CURRENCY;

                TVName.setText(getText(R.string.coincap_name));
                TVSummary.setText(getText(R.string.coincap_summary));

                break;
            case CoinMarketCap.NAME:
                this.acceptedCryptoCurrencies = CoinMarketCap.ACCEPTED_CRYPTO_CURRENCIES;
                this.acceptedFiatCurrencies = CoinMarketCap.ACCEPTED_FIAT_CURRENCIES;

                TVName.setText(getText(R.string.coinmarketcap_name));
                TVSummary.setText(getText(R.string.coinmarketcap_summary));

                break;
            case CryptoCompare.NAME:
                this.acceptedCryptoCurrencies = CryptoCompare.ACCEPTED_CRYPTO_CURRENCIES;
                this.acceptedFiatCurrencies = CryptoCompare.ACCEPTED_FIAT_CURRENCIES;

                TVName.setText(getText(R.string.cryptocompare_name));
                TVSummary.setText(getText(R.string.cryptocompare_summary));

                break;
            case ShapeShift.NAME:
                this.acceptedCryptoCurrencies = ShapeShift.ACCEPTED_CRYPTOCURRENCIES;
                this.acceptedFiatCurrencies = ShapeShift.ACCEPTED_FIAT_CURRENCIES;

                TVName.setText(getText(R.string.shapeshift_name));
                TVSummary.setText(getText(R.string.shapeshift_summary));

                break;
            default:
        }

        for (CryptoCurrency crypto : this.acceptedCryptoCurrencies) {
            TVAcceptedCryptos.append(crypto.getAbbreviatedName());
        }

        for (FiatCurrency fiat : this.acceptedFiatCurrencies) {
            TVAcceptedFiats.append(fiat.getAbbreviatedName());
        }


    }//end onCreate()
}//end AboutActivity
