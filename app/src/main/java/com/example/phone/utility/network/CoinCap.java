package com.example.phone.utility.network;

import android.net.Uri;
import android.util.Log;

import com.example.phone.CurrencyActivity;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;

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
    private static String RATE_USD = "rateUsd";

    /**
     * The key used to pull out the price data from the response JSON
     */
    private static String DATA = "data";

    /**
     * The cryptocurrencies used in the CoinCap endpoint
     */
    private static CryptoCurrency[] ACCEPTED_CRYPTO_CURRENCY = {CryptoCurrency.BTC,
            CryptoCurrency.ETH, CryptoCurrency.LTC};

    /**
     * The name used for Bitcoin
     */
    private static String BTC_NAME = "bitcoin";

    /**
     * The name used for Ethereum
     */
    private static String ETH_NAME = "ethereum";

    /**
     * The name used for Litecoin
     */
    private static String LTC_NAME = "litecoin";

    /**
     * The fiat currencies used in the CoinCap endpoint
     */
    private static FiatCurrency[] ACCEPTED_FIAT_CURRENCY = {FiatCurrency.USD};

    /**
     * The constructor for the CoinCap endpoint
     * @param activity The activity that gives the current fiat and cryptocurrencies
     */
    public CoinCap(CurrencyActivity activity) {
        super (activity, CoinCap.ACCEPTED_CRYPTO_CURRENCY, CoinCap.ACCEPTED_FIAT_CURRENCY);
    }//end CoinCap()

    /**
     * Converts a given cryptocurrency to the appropriate string to put into the url
     * @param crypto The cryptocurrency to be converted
     * @return The CoinCap specified name
     */
    private static String convertCryptoCurrency(CryptoCurrency crypto) {
        switch (crypto) {
            case BTC:
                return CoinCap.BTC_NAME;
            case ETH:
                return CoinCap.ETH_NAME;
            case LTC:
                return CoinCap.LTC_NAME;
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
        JSONObject jsonResponse = new JSONObject();
        try {
            jsonResponse = new JSONObject(response);
            JSONObject data = jsonResponse.getJSONObject(CoinCap.DATA);
            return data.getDouble(CoinCap.RATE_USD);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.println(Log.ERROR, "JSON ERROR: ", jsonResponse.toString());
            return -1;
        }
    }//end extractPrice()

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return CoinCap.NAME;
    }//end getName()
}//end CoinCap
