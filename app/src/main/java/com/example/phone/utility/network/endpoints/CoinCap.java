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
 * The class for the CoinCap endpoint
 */
final public class CoinCap extends AbstractAPICall {

    /**
     * The base url for the endpoint
     */
    private static String BASE_URL = "https://api.coincap.io/v2/rates/";

    /**
     * The name of the endpoint
     */
    private static String NAME = "CoinCap";

    /**
     * The key used to pull out the price data from the response JSON
     */
    private static String JSON_RATE_USD = "rateUsd";

    /**
     * The key used to pull out the price data from the response JSON
     */
    private static String JSON_DATA = "data";

    /**
     * The cryptocurrencies used in the CoinCap endpoint
     */
    private static CryptoCurrency[] ACCEPTED_CRYPTO_CURRENCY = {CryptoCurrency.BTC,
            CryptoCurrency.ETH, CryptoCurrency.LTC};

    /**
     * The fiat currencies used in the CoinCap endpoint
     */
    private static FiatCurrency[] ACCEPTED_FIAT_CURRENCY = {FiatCurrency.USD};

    /**
     * The constructor for the CoinCap endpoint
     * @param activity The activity that gives the current fiat and cryptocurrencies
     */
    public CoinCap(CurrencyActivity activity) {
        super (CoinCap.NAME, activity, CoinCap.ACCEPTED_CRYPTO_CURRENCY,
                CoinCap.ACCEPTED_FIAT_CURRENCY);
    }//end CoinCap()

    /**
     * Converts a given cryptocurrency to the appropriate string to put into the url
     * @param crypto The cryptocurrency to be converted
     * @return The CoinCap specified name
     */
    private static String convertCryptoCurrency(CryptoCurrency crypto) {
        switch (crypto) {
            case BTC:
                return CryptoCurrency.BTC.getFullName().toLowerCase();
            case ETH:
                return CryptoCurrency.ETH.getFullName().toLowerCase();
            case LTC:
                return CryptoCurrency.LTC.getFullName().toLowerCase();
            default:
                // TODO: This should probably be gotten rid of?, as we catch used/unused cryptos
                //  early on
                return "";
        }//end switch
    }//end convertCryptoCurrency()

    /**
     * {@inheritDoc}
     */
    @Override
    protected Uri buildUri(CryptoCurrency crypto, FiatCurrency fiat) {
        return Uri.parse(CoinCap.BASE_URL + CoinCap.convertCryptoCurrency(crypto));
    }//end buildUri()

    /**
     * {@inheritDoc}
     */
    @Override
    public double extractPrice(String response) {
        try {
            return new JSONObject(response)
                    .getJSONObject(CoinCap.JSON_DATA)
                    .getDouble(CoinCap.JSON_RATE_USD);
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }//end try/catch
    }//end extractPrice()
}//end CoinCap
