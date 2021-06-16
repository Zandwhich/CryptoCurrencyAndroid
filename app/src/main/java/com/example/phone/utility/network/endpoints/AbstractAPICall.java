package com.example.phone.utility.network.endpoints;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.phone.activities.CurrencyInterface;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.NetworkUtils;
import com.example.phone.utility.network.errors.CryptoNotAccepted;
import com.example.phone.utility.network.errors.FiatNotAccepted;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Abstract class for making a call to an API
 */
public abstract class AbstractAPICall {

    /* *********** *
     *  Constants  *
     * *********** */

    /**
     * The name of the class
     */
    public static final String NAME = "AbstractApiCall";

    /**
     * The TAG used for logging
     */
    private static final String TAG = AbstractAPICall.class.getSimpleName();

    /**
     * The default price when no price is available
     */
    public final static double NO_PRICE = -1;


    /* ******** *
     *  Fields  *
     * ******** */

    /**
     * The cryptocurrencies that this website can use
     */
    private final CryptoCurrency[] acceptedCryptoCurrencies;

    /**
     * The fiat currencies that this website can use
     */
    private final FiatCurrency[] acceptedFiatCurrencies;

    /**
     * The activity that holds which currencies are being used right now
     */
    protected CurrencyInterface activity;

    /**
     * The name of the endpoint
     */
    private final String name;

    /**
     * The price that was last retrieved
     */
    private double price;

    /**
     * If the endpoint supports fiat to crypto conversions
     */
    private final boolean supportsCryptoToFiat;

    /**
     * If the endpoint supports crypto to crypto conversions
     */
    private final boolean supportsCryptoToCrypto;


    /* ************** *
     *  Constructors  *
     * ************** */

    /**
     * The constructor for the AbstractAPICall
     * @param name The name of the API call
     * @param activity The parent activity that holds call
     * @param acceptedCryptoCurrencies The accepted cryptocurrencies for this call
     * @param acceptedFiatCurrencies The accepted fiat currencies for this call
     */
    public AbstractAPICall(final String name, final CurrencyInterface activity,
                           final CryptoCurrency[] acceptedCryptoCurrencies,
                           final FiatCurrency[] acceptedFiatCurrencies,
                           final boolean supportsCryptoToFiat,
                           final boolean supportsCryptoToCrypto) {
        this.acceptedCryptoCurrencies = acceptedCryptoCurrencies;
        this.acceptedFiatCurrencies = acceptedFiatCurrencies;
        this.activity = activity;
        this.name = name;
        this.price = AbstractAPICall.NO_PRICE;
        this.supportsCryptoToFiat = supportsCryptoToFiat;
        this.supportsCryptoToCrypto = supportsCryptoToCrypto;
    }//end AbstractAPICall


    /* ********* *
     *  Methods  *
     * ********* */

    /**
     * Returns if this API uses the given cryptocurrency
     * @param crypto The given cryptocurrency
     * @return If this API uses the given cryptocurrency
     */
    public boolean canUseCryptocurrency(@NonNull final CryptoCurrency crypto) {
        for (CryptoCurrency acceptedCrypto : this.acceptedCryptoCurrencies) {
            if (crypto == acceptedCrypto) return true;
        }//end for
        return false;
    }//end usesCryptocurrency()

    /**
     * Returns if this API uses the given fiat currency
     * @param fiat THe given fiat currency
     * @return If this API uses the given fiat currency
     */
    public boolean canUseFiatCurrency(@NonNull final FiatCurrency fiat) {
        for (FiatCurrency acceptedFiat: this.acceptedFiatCurrencies) {
            if (fiat == acceptedFiat) return true;
        }//end for
        return false;
    }//end usesFiatCurrency()

    /**
     * Method that throws if CoinBase can't use either the crypto or the fiat currency
     * @param crypto The cryptocurrency to check
     * @param fiat The fiat currency to check
     * @throws CryptoNotAccepted Throws if the cryptocurrency is not accepted
     * @throws FiatNotAccepted Throws if the fiat currency is not accepted
     */
    private void throwIfNotAccepted(CryptoCurrency crypto, FiatCurrency fiat)
            throws CryptoNotAccepted, FiatNotAccepted {
        if (!this.canUseCryptocurrency(crypto)) throw new CryptoNotAccepted();
        if (!this.canUseFiatCurrency(fiat)) throw new FiatNotAccepted();
    }//end canUseCurrencies()

    /**
     * Builds the Uri to be converted later into a URL
     * @param crypto The cryptocurrency of the conversion
     * @param fiat The fiat currency of the conversion
     * @return The Uri to be turned into a URL
     */
    protected abstract Uri buildUri(CryptoCurrency crypto, FiatCurrency fiat);

    /**
     * Constructs the URL with a given cryptocurrency and a given fiat currency
     * @param crypto The given cryptocurrency
     * @param fiat The given fiat currency
     * @return The constructed URL
     */
    public URL buildURL(CryptoCurrency crypto, FiatCurrency fiat)
            throws FiatNotAccepted, CryptoNotAccepted {
        throwIfNotAccepted(crypto, fiat);
        Uri uri = this.buildUri(crypto, fiat);

        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }//end try/catch

        return url;
    }//end buildURL()

    /**
     * Extracts the price for the given website
     * @return The price extracted from the string version of the response
     * Returns -1 if there was an error
     */
    protected abstract double extractPrice(String response);

    /**
     * Gets the name to print to the screen
     * @return The name of the endpoint to print to the screen
     */
    public String getName() {
        return this.name;
    }//end getName()

    /**
     * Returns the price
     * @return The price
     */
    public double getPrice() { return this.price; }//end getPrice()

    /**
     * Hits the URL, and then updates the price.
     * NOTE: Does not update the price if the received price is -1
     */
    public void updatePrice() {
        String contents;
        try {
            contents = NetworkUtils.connect(this.buildURL(this.activity.getCurrentCrypto(),
                    this.activity.getCurrentFiat()));
        } catch (IOException | FiatNotAccepted | CryptoNotAccepted e) {
            e.printStackTrace();
            // TODO: Somehow alert that there was an error in connecting to the website
            return;
        }//end try/catch

        double price = this.extractPrice(contents);
        if (price == AbstractAPICall.NO_PRICE) {
            // TODO: Somehow alert that there was an error in the response
            return;
        }//end if == -1

        Log.d(AbstractAPICall.TAG, "Price: " + price);

        this.price = price;
    }//end updatePrice()

    /**
     * Returns the name of the class
     * @return The name of the class
     */
    public String getClassName() {
        return AbstractAPICall.NAME;
    }

    /**
     * Returns if supports fiat to crypto conversion
     * @return If supports fiat to crypto conversion
     */
    public boolean supportsFiatToCrypto() {
        return supportsCryptoToFiat;
    }

    /**
     * Returns if supports crypto to crypto conversion
     * @return If this endpoint supports crypto to crypto conversion
     */
    public boolean supportsCryptoToCrypto() {
        return supportsCryptoToCrypto;
    }
}//end AbstractAPICall
