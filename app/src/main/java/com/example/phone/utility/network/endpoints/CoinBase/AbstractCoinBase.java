package com.example.phone.utility.network.endpoints.CoinBase;

import android.net.Uri;

import com.example.phone.activities.CurrencyInterface;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.endpoints.AbstractAPICall;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * The abstract class for CoinBase calls
 */
public abstract class AbstractCoinBase extends AbstractAPICall {

    /**
     * The base name for the CoinBase extensions
     */
    public static final String NAME = "CoinBase";

    /**
     * The base URL for the CoinBase API
     */
    private static final String BASE_URL = "https://api.coinbase.com/v2/prices/";

    /**
     * Used to get data out from the response object
     */
    private static final String JSON_DATA = "data";

    /**
     * Used to get the price out of the response object
     */
    private static final String JSON_AMOUNT = "amount";

    /**
     * A list of the accepted cryptocurrencies (that we're also using) at the moment
     */
    public static final CryptoCurrency[] ACCEPTED_CRYPTOCURRENCIES = {CryptoCurrency.BTC,
            CryptoCurrency.ETH, CryptoCurrency.LTC, CryptoCurrency.XRP};

    /**
     * A list of the accepted fiat currencies (that we're also using) at the moment
     */
    public static final FiatCurrency[] ACCEPTED_FIAT_CURRENCIES = {FiatCurrency.AUD,
            FiatCurrency.CAD, FiatCurrency.EUR, FiatCurrency.GBP, FiatCurrency.JPY,
            FiatCurrency.MXN, FiatCurrency.NZD, FiatCurrency.PLN, FiatCurrency.SEK,
            FiatCurrency.USD};

    /**
     * Coinbase supports converting from fiat cryptocurrencies to fiat currencies
     */
    public static final boolean SUPPORTS_CRYPTO_TO_FIAT = true;

    /**
     * Coinbase does not support converting from cryptocurrencies to other cryptocurrencies
     */
    public static final boolean SUPPORTS_CRYPTO_TO_CRYPTO = false;

    /**
     * The CoinBase extension (one of 'buy', 'sell', or 'spot')
     */
    private final String ext;

    /**
     * A mapping of cryptos to strings that are used for the request and parsing
     */
    private final static Map<CryptoCurrency, String> cryptoParamMap = CryptoCurrency.abbreviatedMap;

    /**
     * A mapping of fiats to strings that are used for the request and parsing
     */
    private final static Map<FiatCurrency, String> fiatParamMap = FiatCurrency.abbreviatedMap;

    /**
     * The constructor for the CoinBase website
     * @param ext The extension of (one of 'buy', 'sell', or 'spot')
     */
    AbstractCoinBase(String ext_name, CurrencyInterface activity, String ext) {
        super(AbstractCoinBase.NAME + " " + ext_name, activity,
                AbstractCoinBase.ACCEPTED_CRYPTOCURRENCIES,
                AbstractCoinBase.ACCEPTED_FIAT_CURRENCIES,
                AbstractCoinBase.SUPPORTS_CRYPTO_TO_FIAT,
                AbstractCoinBase.SUPPORTS_CRYPTO_TO_CRYPTO);
        this.ext = ext;
    }//end AbstractCoinBase()

    /**
     * {@inheritDoc}
     */
    @Override
    protected Uri buildUri(CryptoCurrency baseCrypto, FiatCurrency targetFiat) {
        return Uri.parse(AbstractCoinBase.BASE_URL + cryptoParamMap.get(baseCrypto) + "-" +
                fiatParamMap.get(targetFiat) + this.ext)
                .buildUpon()
                .build();
    }//end buildUri()

    /**
     * {@inheritDoc}
     * Extracts the price for CoinBase
     */
    @Override
    public double extractPrice(String response) {
        try {
            return new JSONObject(response)
                    .getJSONObject(AbstractCoinBase.JSON_DATA)
                    .getDouble(AbstractCoinBase.JSON_AMOUNT);
        } catch (JSONException e) {
            // TODO: Figure this out better (maybe parent method should throw JSONException?)
            e.printStackTrace();
            return AbstractAPICall.NO_PRICE;
        }//end try/catch
    }//end extractPrice()

    /**
     * {@inheritDoc}
     */
    @Override
    public String getClassName() {
        return AbstractCoinBase.NAME;
    }
}//end AbstractCoinBase
