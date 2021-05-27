package com.example.phone.utility.network.endpoints;

import android.net.Uri;

import com.example.phone.activities.CurrencyInterface;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.AbstractAPICall;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The class that handles the ShapeShift API endpoint
 */
final public class ShapeShift extends AbstractAPICall {

    // TODO: So... ShapeShift is used to transfer one type of cryptocurrency to another (hence its
    //  name). For the time being I will leave it as is (i.e. only interchanging
    //  Fiat/Cryptocurrencies, but in the future, we should figure out how to incorporate it.
    //  Phase 2!

    /**
     * The name of the ShapeShift endpoint
     */
    public static final String NAME = "ShapeShift";

    /**
     * The base url for the ShapeShift endpoint
     */
    private static final String BASE_URL = "https://shapeshift.io/marketinfo/";

    /**
     * The list of accepted cryptocurrencies that ShapeShift uses
     */
    public static final CryptoCurrency[] ACCEPTED_CRYPTOCURRENCIES = {CryptoCurrency.BTC,
            CryptoCurrency.ETH, CryptoCurrency.LTC, CryptoCurrency.XRP /*, CryptoCurrency.DASH,
             CryptoCurrency.DGB, CryptoCurrency.BNT, CryptoCurrency.SC, CryptoCurrency.XMR,
             CryptoCurrency.BLK,CryptoCurrency.DOGE, CryptoCurrency.ZEC, CryptoCurrency.CVC,
             CryptoCurrency.DNT, CryptoCurrency.GNT, CryptoCurrency.MANA, CryptoCurrency.MKR,
             CryptoCurrency.PAX, CryptoCurrency.RCN, CryptoCurrency.REP, CryptoCurrency.USDC,
             CryptoCurrency.RLC, CryptoCurrency.USDT, CryptoCurrency.SNT, CryptoCurrency.ZRX*/};

    /**
     * The list of accepted fiat currencies that ShapeShift uses
     */
    public static final FiatCurrency[] ACCEPTED_FIAT_CURRENCIES = {};

    /**
     * The string used to get the rate from the returned JSON
     */
    private static final String JSON_RATE = "rate";

    /**
     * The constructor for the ShapeShift endpoint
     * @param activity The activity that provides which are the current fiat and cryptocurrencies
     */
    public ShapeShift(CurrencyInterface activity) {
        super(ShapeShift.NAME, activity, ShapeShift.ACCEPTED_CRYPTOCURRENCIES,
                ShapeShift.ACCEPTED_FIAT_CURRENCIES);
    }//end ShapeShift()

    /**
     * The constructor used only for figuring out if this endpoint can support certain currencies.
     * <br><br>
     * <b>DON'T USE THIS CONSTRUCTOR FOR ACTUALLY CONNECTING TO THE ENDPOINTS THEMSELVES!!</b>
     */
    public ShapeShift() {
        super(ShapeShift.NAME, null, ShapeShift.ACCEPTED_CRYPTOCURRENCIES,
                ShapeShift.ACCEPTED_FIAT_CURRENCIES);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Uri buildUri(CryptoCurrency crypto, FiatCurrency fiat) {
        return Uri.parse(ShapeShift.BASE_URL + crypto.getAbbreviatedName() + "_" +
                fiat.getAbbreviatedName())
                .buildUpon()
                .build();
    }//end buildUri()

    /**
     * {@inheritDoc}
     */
    @Override
    public double extractPrice(String response) {
        try {
            return new JSONObject(response)
                    .getDouble(ShapeShift.JSON_RATE);
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
        return ShapeShift.NAME;
    }
}//end ShapeShift
