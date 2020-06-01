package com.example.phone.utility.network.endpoints;

import android.net.Uri;
import android.util.Log;

import com.example.phone.CurrencyActivity;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.AbstractAPICall;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The class for CryptoCompare calls
 */
final public class CryptoCompare extends AbstractAPICall {

    /**
     * The base url for the CryptoCompare website
     */
    private static String BASE_URL = "https://min-api.cryptocompare.com/data/price";

    /**
     * The name of this endpoint
     */
    private static String NAME = "CryptoCompare";

    /**
     * The cryptocurrencies that CryptoCompare uses
     */
    private final static CryptoCurrency[] ACCEPTED_CRYPTO_CURRENCIES = {CryptoCurrency.BTC,
            CryptoCurrency.ETH, CryptoCurrency.LTC, CryptoCurrency.XRP};

    /**
     * The fiat currencies that CryptoCompare uses
     */
    private final static FiatCurrency[] ACCEPTED_FIAT_CURRENCIES = {FiatCurrency.AUD,
            FiatCurrency.CAD, FiatCurrency.EUR, FiatCurrency.GBP, FiatCurrency.JPY,
            FiatCurrency.MXN, FiatCurrency.NZD, FiatCurrency.PLN, FiatCurrency.SEK,
            FiatCurrency.USD};

    /**
     * The key for the query param for the cryptocurrency
     */
    private static final String QUERY_PARAM_CRYPTO_KEY = "fsym";

    /**
     * The key for the query param for the fiat currency
     */
    private static final String QUERY_PARAM_FIAT_KEY = "tsyms";

    /**
     * The constructor for CryptoCompare
     * @param activity The activity providing the current fiat and cryptocurrencies
     */
    public CryptoCompare(CurrencyActivity activity) {
        super(activity, CryptoCompare.ACCEPTED_CRYPTO_CURRENCIES,
                CryptoCompare.ACCEPTED_FIAT_CURRENCIES);
    }//end CryptoCompare()

    /**
     * {@inheritDoc}
     */
    @Override
    protected Uri buildUri(CryptoCurrency crypto, FiatCurrency fiat) {
        return Uri.parse(CryptoCompare.BASE_URL)
                .buildUpon()
                .appendQueryParameter(QUERY_PARAM_CRYPTO_KEY, crypto.getAbbreviatedName())
                .appendQueryParameter(QUERY_PARAM_FIAT_KEY, fiat.getAbbreviatedName())
                .build();
    }//end buildUri()

    /**
     * {@inheritDoc}
     */
    @Override
    public double extractPrice(String response) {
        try {
            return new JSONObject(response)
                    .getDouble(super.activity.getCurrentFiat().getAbbreviatedName());
        } catch (JSONException e) {
            // TODO: Figure this out later
            e.printStackTrace();
            return -1;
        }//end try/catch
    }//end extractPrice()

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return CryptoCompare.NAME;
    }//end getName()
}//end CryptoCompare
