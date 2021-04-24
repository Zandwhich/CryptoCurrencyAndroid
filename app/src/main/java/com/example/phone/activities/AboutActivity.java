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

    private TextView TVName;

    private TextView TVSummary;

    private TextView TVAcceptedCryptos;

    private TextView TVAcceptedFiats;

    private CryptoCurrency[] acceptedCryptoCurrencies;
    private FiatCurrency[] acceptedFiatCurrencies;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        this.TVName = findViewById(R.id.tv_about_name);
        this.TVSummary = findViewById(R.id.tv_about_summary);
        this.TVAcceptedCryptos = findViewById(R.id.tv_about_accepted_cryptos);
        this.TVAcceptedFiats = findViewById(R.id.tv_about_accepted_fiats);

        switch (getIntent().getStringExtra(AboutActivity.API_KEY)) {
            case AbstractCoinBase.NAME:
                this.acceptedCryptoCurrencies = AbstractCoinBase.ACCEPTED_CRYPTOCURRENCIES;
                this.acceptedFiatCurrencies = AbstractCoinBase.ACCEPTED_FIAT_CURRENCIES;

                this.TVName.setText(getText(R.string.coinbase_name));
                this.TVSummary.setText(getText(R.string.coinbase_summary));

                break;
            case CoinCap.NAME:
                this.acceptedCryptoCurrencies = CoinCap.ACCEPTED_CRYPTO_CURRENCY;
                this.acceptedFiatCurrencies = CoinCap.ACCEPTED_FIAT_CURRENCY;

                this.TVName.setText(getText(R.string.coincap_name));
                this.TVSummary.setText(getText(R.string.coincap_summary));

                break;
            case CoinMarketCap.NAME:
                this.acceptedCryptoCurrencies = CoinMarketCap.ACCEPTED_CRYPTO_CURRENCIES;
                this.acceptedFiatCurrencies = CoinMarketCap.ACCEPTED_FIAT_CURRENCIES;

                this.TVName.setText(getText(R.string.coinmarketcap_name));
                this.TVSummary.setText(getText(R.string.coinmarketcap_summary));

                break;
            case CryptoCompare.NAME:
                this.acceptedCryptoCurrencies = CryptoCompare.ACCEPTED_CRYPTO_CURRENCIES;
                this.acceptedFiatCurrencies = CryptoCompare.ACCEPTED_FIAT_CURRENCIES;

                this.TVName.setText(getText(R.string.cryptocompare_name));
                this.TVSummary.setText(getText(R.string.cryptocompare_summary));

                break;
            case ShapeShift.NAME:
                this.acceptedCryptoCurrencies = ShapeShift.ACCEPTED_CRYPTOCURRENCIES;
                this.acceptedFiatCurrencies = ShapeShift.ACCEPTED_FIAT_CURRENCIES;

                this.TVName.setText(getText(R.string.shapeshift_name));
                this.TVSummary.setText(getText(R.string.shapeshift_summary));

                break;
            default:
        }

        for (CryptoCurrency crypto : this.acceptedCryptoCurrencies) {
            this.TVAcceptedCryptos.append(crypto.getAbbreviatedName());
        }

        for (FiatCurrency fiat : this.acceptedFiatCurrencies) {
            this.TVAcceptedFiats.append(fiat.getAbbreviatedName());
        }


    }//end onCreate()

    private Class<? extends AbstractAPICall> getAPIClassFromString(String APIName) {

        switch (APIName) {
            case AbstractCoinBase.NAME:
                this.acceptedCryptoCurrencies = AbstractCoinBase.ACCEPTED_CRYPTOCURRENCIES;
                this.acceptedFiatCurrencies = AbstractCoinBase.ACCEPTED_FIAT_CURRENCIES;
                return AbstractCoinBase.class;
            case CoinCap.NAME:
                this.acceptedCryptoCurrencies = CoinCap.ACCEPTED_CRYPTO_CURRENCY;
                this.acceptedFiatCurrencies = CoinCap.ACCEPTED_FIAT_CURRENCY;
                return CoinCap.class;
            case CoinMarketCap.NAME:
                this.acceptedCryptoCurrencies = CoinMarketCap.ACCEPTED_CRYPTO_CURRENCIES;
                this.acceptedFiatCurrencies = CoinMarketCap.ACCEPTED_FIAT_CURRENCIES;
                return CoinMarketCap.class;
            case CryptoCompare.NAME:
                this.acceptedCryptoCurrencies = CryptoCompare.ACCEPTED_CRYPTO_CURRENCIES;
                this.acceptedFiatCurrencies = CryptoCompare.ACCEPTED_FIAT_CURRENCIES;
                return CryptoCompare.class;
            case ShapeShift.NAME:
                this.acceptedCryptoCurrencies = ShapeShift.ACCEPTED_CRYPTOCURRENCIES;
                this.acceptedFiatCurrencies = ShapeShift.ACCEPTED_FIAT_CURRENCIES;
                return ShapeShift.class;
            default:
                return AbstractAPICall.class;
        }
    }
}//end AboutActivity
