package com.example.phone.utility.network.endpoints;

import android.net.Uri;

import com.example.phone.activities.CurrencyInterface;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.AbstractAPICall;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * The class for CryptoCompare calls
 */
final public class CryptoCompare extends AbstractAPICall {

    /**
     * The base url for the CryptoCompare website
     */
    private static final String BASE_URL = "https://min-api.cryptocompare.com/data/price";

    /**
     * The name of this endpoint
     */
    public static final String NAME = "CryptoCompare";

    /**
     * The cryptocurrencies that CryptoCompare uses
     */
    public final static CryptoCurrency[] ACCEPTED_CRYPTO_CURRENCIES = {CryptoCurrency.BTC,
            CryptoCurrency.ETH, CryptoCurrency.LTC, CryptoCurrency.XRP};

    /**
     * The fiat currencies that CryptoCompare uses
     */
    public final static FiatCurrency[] ACCEPTED_FIAT_CURRENCIES = {FiatCurrency.AUD,
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
     * A mapping of cryptos to strings that are used for the request and parsing
     */
    private static final Map<CryptoCurrency, String> cryptoParamMap = CryptoCurrency.abbreviatedMap;

    /**
     * A mapping of fiats to strings that are used for the request and parsing
     */
    private static final Map<FiatCurrency, String> fiatParamMap = FiatCurrency.abbreviatedMap;

    /**
     * The constructor for CryptoCompare
     * @param activity The activity providing the current fiat and cryptocurrencies
     */
    public CryptoCompare(CurrencyInterface activity) {
        super(CryptoCompare.NAME, activity, CryptoCompare.ACCEPTED_CRYPTO_CURRENCIES,
                CryptoCompare.ACCEPTED_FIAT_CURRENCIES);
    }//end CryptoCompare()

    /**
     * The constructor used only for figuring out if this endpoint can support certain currencies.
     * <br><br>
     * <b>DON'T USE THIS CONSTRUCTOR FOR ACTUALLY CONNECTING TO THE ENDPOINTS THEMSELVES!!</b>
     */
    public CryptoCompare() {
        super(CryptoCompare.NAME, null, CryptoCompare.ACCEPTED_CRYPTO_CURRENCIES,
                CryptoCompare.ACCEPTED_FIAT_CURRENCIES);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Uri buildUri(CryptoCurrency crypto, FiatCurrency fiat) {
        return Uri.parse(CryptoCompare.BASE_URL)
                .buildUpon()
                .appendQueryParameter(QUERY_PARAM_CRYPTO_KEY, cryptoParamMap.get(crypto))
                .appendQueryParameter(QUERY_PARAM_FIAT_KEY, fiatParamMap.get(fiat))
                .build();
    }//end buildUri()

    /**
     * {@inheritDoc}
     */
    @Override
    public double extractPrice(String response) {
        try {
            return new JSONObject(response)
                    .getDouble(fiatParamMap.get(super.activity.getCurrentFiat()));
        } catch (JSONException e) {
            // TODO: Figure this out later
            e.printStackTrace();
            return AbstractAPICall.NO_PRICE;
        }//end try/catch
    }//end extractPrice()

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return CryptoCompare.NAME;
    }
}//end CryptoCompare
