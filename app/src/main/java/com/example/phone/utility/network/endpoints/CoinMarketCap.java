package com.example.phone.utility.network.endpoints;

import android.net.Uri;

import com.example.phone.activities.CurrencyActivity;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.AbstractAPICall;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The class for CoinMarketCap calls
 */
final public class CoinMarketCap extends AbstractAPICall {

    /**
     * The base url for the CoinMarketCap website
     */
    private final static String BASE_URL =
            "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";

    /**
     * The name of the CoinMarketCap endpoint
     */
    private static String NAME = "CoinMarketCap";

    /**
     * The cryptocurrencies that CoinMarketCap uses
     */
    private final static CryptoCurrency[] ACCEPTED_CRYPTO_CURRENCIES = {CryptoCurrency.BTC,
            CryptoCurrency.ETH, CryptoCurrency.LTC, CryptoCurrency.XRP};

    /**
     * The fiat currencies that CoinMarketCap uses
     */
    private final static FiatCurrency[] ACCEPTED_FIAT_CURRENCIES = {FiatCurrency.AUD,
            FiatCurrency.CAD, FiatCurrency.EUR, FiatCurrency.GBP, FiatCurrency.JPY,
            FiatCurrency.MXN, FiatCurrency.NZD, FiatCurrency.PLN, FiatCurrency.SEK,
            FiatCurrency.USD};

    /**
     * The key to get the data from the JSON response
     */
    private final static String JSON_DATA = "data";

    /**
     * The key to get the quote data from the JSON response
     */
    private final static String JSON_QUOTE = "quote";

    /**
     * The key to get the price from the JSON response
     */
    private final static String JSON_PRICE = "price";

    /**
     * The query parameter for the cryptocurrency to be added to the request url
     */
    private final static String QUERY_PARAM_SYMBOL = "symbol";

    /**
     * The query parameter for the fiat currency to be added to the request url
     */
    private final static String QUERY_PARAM_CONVERT = "convert";

    /**
     * The constructor for CoinMarketCap
     */
    public CoinMarketCap(CurrencyActivity activity) {
        super(CoinMarketCap.NAME, activity, CoinMarketCap.ACCEPTED_CRYPTO_CURRENCIES,
                CoinMarketCap.ACCEPTED_FIAT_CURRENCIES);
    }//end CoinMarketCap()

    /**
     * {@inheritDoc}
     */
    @Override
    protected Uri buildUri(CryptoCurrency crypto, FiatCurrency fiat) {
        // TODO: Figure out how to add the secret key to the header
        return Uri.parse(CoinMarketCap.BASE_URL).buildUpon()
                .appendQueryParameter(CoinMarketCap.QUERY_PARAM_SYMBOL, crypto.getAbbreviatedName())
                .appendQueryParameter(CoinMarketCap.QUERY_PARAM_CONVERT, fiat.getAbbreviatedName())
                .build();
    }//end buildUri()

    /**
     * {@inheritDoc}
     */
    @Override
    public double extractPrice(String response) {
        try {
            return new JSONObject(response).getJSONObject(CoinMarketCap.JSON_DATA)
                    .getJSONObject(super.activity.getCurrentCrypto().getAbbreviatedName())
                    .getJSONObject(CoinMarketCap.JSON_QUOTE)
                    .getJSONObject(super.activity.getCurrentFiat().getAbbreviatedName())
                    .getDouble(JSON_PRICE);
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }//end try/catch
    }//end extractPrice()
}//end CoinMarketCap
