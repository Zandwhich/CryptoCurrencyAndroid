package com.example.phone.utility.network.endpoints.CoinBase;

import android.net.Uri;

import com.example.phone.CurrencyActivity;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.AbstractAPICall;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The abstract class for CoinBase calls
 */
public abstract class AbstractCoinBase extends AbstractAPICall {

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
            FiatCurrency.CAD, FiatCurrency.EUR, FiatCurrency.JPY, FiatCurrency.MXN,
            FiatCurrency.NZD, FiatCurrency.PLN, FiatCurrency.SEK, FiatCurrency.USD};

    /**
     * The CoinBase extension (one of 'buy', 'sell', or 'spot')
     */
    private String ext;

    /**
     * The constructor for the CoinBase website
     * @param ext The extension of (one of 'buy', 'sell', or 'spot')
     */
    AbstractCoinBase(CurrencyActivity activity, String ext) {
        super(activity, AbstractCoinBase.ACCEPTED_CRYPTOCURRENCIES,
                AbstractCoinBase.ACCEPTED_FIAT_CURRENCIES);
        this.ext = ext;
    }//end AbstractCoinBase()

    /**
     * {@inheritDoc}
     */
    @Override
    protected Uri buildUri(CryptoCurrency crypto, FiatCurrency fiat) {
        return Uri.parse(AbstractCoinBase.BASE_URL + crypto.getAbbreviatedName() + "-" +
                fiat.getAbbreviatedName() + this.ext)
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
            return -1;
        }//end try/catch
    }//end extractPrice()
}//end AbstractCoinBase
