package com.example.phone.utility.network.endpoints;

import android.net.Uri;

import com.example.phone.activities.CurrencyInterface;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * The class for CoinMarketCap calls
 */
final public class CoinMarketCap extends AbstractAPICall {

    /* *********** *
     *  Constants  *
     * *********** */

    /**
     * The base url for the CoinMarketCap website
     */
    private final static String BASE_URL =
            "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";

    /**
     * The name of the CoinMarketCap endpoint
     */
    public static final String NAME = "CoinMarketCap";

    /**
     * The cryptocurrencies that CoinMarketCap uses
     */
    public final static CryptoCurrency[] ACCEPTED_CRYPTO_CURRENCIES = {CryptoCurrency.BTC,
            CryptoCurrency.ETH, CryptoCurrency.LTC, CryptoCurrency.XRP};

    /**
     * The fiat currencies that CoinMarketCap uses
     */
    public final static FiatCurrency[] ACCEPTED_FIAT_CURRENCIES = {FiatCurrency.AUD,
            FiatCurrency.CAD, FiatCurrency.EUR, FiatCurrency.GBP, FiatCurrency.JPY,
            FiatCurrency.MXN, FiatCurrency.NZD, FiatCurrency.PLN, FiatCurrency.SEK,
            FiatCurrency.USD};

    /**
     * CoinMarketCap supports exchanging cryptocurrencies to fiat currencies
     */
    public final static boolean SUPPORTS_CRYPTO_TO_FIAT = true;

    /**
     * CoinMarketCap does not support exchanging cryptocurrencies to other cryptocurrencies
     *
     * TODO: Double-check this. I think they actually do support this, but I'm too lazy to check atm
     */
    public final static boolean SUPPORTS_CRYPTO_TO_CRYPTO = false;

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
     * A mapping of cryptos to strings that are used for the request and parsing
     */
    private final static Map<CryptoCurrency, String> cryptoParamMap = CryptoCurrency.abbreviatedMap;

    /**
     * A mapping of fiats to strings that are used for the request and parsing
     */
    private final static Map<FiatCurrency, String> fiatParamMap = FiatCurrency.abbreviatedMap;


    /* ************** *
     *  Constructors  *
     * ************** */

    /**
     * The constructor for CoinMarketCap
     */
    public CoinMarketCap(CurrencyInterface activity) {
        super(CoinMarketCap.NAME, activity, CoinMarketCap.ACCEPTED_CRYPTO_CURRENCIES,
                CoinMarketCap.ACCEPTED_FIAT_CURRENCIES, CoinMarketCap.SUPPORTS_CRYPTO_TO_FIAT,
                CoinMarketCap.SUPPORTS_CRYPTO_TO_CRYPTO);
    }//end CoinMarketCap()

    /**
     * The constructor used only for figuring out if this endpoint can support certain currencies.
     * <br><br>
     * <b>DON'T USE THIS CONSTRUCTOR FOR ACTUALLY CONNECTING TO THE ENDPOINTS THEMSELVES!!</b>
     */
    public CoinMarketCap() {
        super(CoinMarketCap.NAME, null, CoinMarketCap.ACCEPTED_CRYPTO_CURRENCIES,
                CoinMarketCap.ACCEPTED_FIAT_CURRENCIES, CoinMarketCap.SUPPORTS_CRYPTO_TO_FIAT,
                CoinMarketCap.SUPPORTS_CRYPTO_TO_CRYPTO);
    }


    /* ********* *
     *  Methods  *
     * ********* */

    /**
     * {@inheritDoc}
     */
    @Override
    protected Uri buildUri(CryptoCurrency baseCrypto, FiatCurrency targetFiat) {
        // TODO: Figure out how to add the secret key to the header
        return Uri.parse(CoinMarketCap.BASE_URL).buildUpon()
                .appendQueryParameter(CoinMarketCap.QUERY_PARAM_SYMBOL, cryptoParamMap.get(baseCrypto))
                .appendQueryParameter(CoinMarketCap.QUERY_PARAM_CONVERT, fiatParamMap.get(targetFiat))
                .build();
    }//end buildUri()

    /**
     * {@inheritDoc}
     */
    @Override
    public double extractPrice(String response) {
        try {
            return new JSONObject(response).getJSONObject(CoinMarketCap.JSON_DATA)
                    .getJSONObject(cryptoParamMap.get(super.activity.getBaseCrypto()))
                    .getJSONObject(CoinMarketCap.JSON_QUOTE)
                    .getJSONObject(fiatParamMap.get(super.activity.getTargetFiat()))
                    .getDouble(JSON_PRICE);
        } catch (JSONException e) {
            e.printStackTrace();
            return AbstractAPICall.NO_PRICE;
        }//end try/catch
    }//end extractPrice()

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return CoinMarketCap.NAME;
    }
}//end CoinMarketCap
