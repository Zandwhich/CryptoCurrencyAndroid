package com.example.phone.utility.network.endpoints;

import android.net.Uri;

import com.example.phone.activities.CurrencyActivity;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.AbstractAPICall;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * The class for the CoinCap endpoint
 */
final public class CoinCap extends AbstractAPICall {

    /**
     * The base url for the endpoint
     */
    private static final String BASE_URL = "https://api.coincap.io/v2/rates/";

    /**
     * The name of the endpoint
     */
    public static final String NAME = "CoinCap";

    /**
     * The key used to pull out the price data from the response JSON
     */
    private static final String JSON_RATE_USD = "rateUsd";

    /**
     * The key used to pull out the price data from the response JSON
     */
    private static final String JSON_DATA = "data";

    /**
     * The cryptocurrencies used in the CoinCap endpoint
     */
    public static final CryptoCurrency[] ACCEPTED_CRYPTO_CURRENCY = {CryptoCurrency.BTC,
            CryptoCurrency.ETH, CryptoCurrency.LTC};

    /**
     * The fiat currencies used in the CoinCap endpoint
     */
    public static final FiatCurrency[] ACCEPTED_FIAT_CURRENCY = {FiatCurrency.USD};

    /**
     * A mapping of cryptos to strings that are used for the request and parsing
     */
    private static final Map<CryptoCurrency, String> cryptoParamMap =
            new HashMap<CryptoCurrency, String>() {{
                put(CryptoCurrency.BTC, "bitcoin");
                put(CryptoCurrency.ETH, "ethereum");
                put(CryptoCurrency.LTC, "litecoin");
            }};

    /**
     * The constructor for the CoinCap endpoint
     * @param activity The activity that gives the current fiat and cryptocurrencies
     */
    public CoinCap(CurrencyActivity activity) {
        super (CoinCap.NAME, activity, CoinCap.ACCEPTED_CRYPTO_CURRENCY,
                CoinCap.ACCEPTED_FIAT_CURRENCY);
    }//end CoinCap()

    /**
     * {@inheritDoc}
     */
    @Override
    protected Uri buildUri(CryptoCurrency crypto, FiatCurrency fiat) {
        return Uri.parse(CoinCap.BASE_URL + cryptoParamMap.get(crypto));
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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return CoinCap.NAME;
    }
}//end CoinCap
