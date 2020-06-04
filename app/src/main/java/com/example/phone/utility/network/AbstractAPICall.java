package com.example.phone.utility.network;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.example.phone.activities.CurrencyActivity;
import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;
import com.example.phone.utility.network.errors.CryptoNotAccepted;
import com.example.phone.utility.network.errors.FiatNotAccepted;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Abstract class for making a call to an API
 */
public abstract class AbstractAPICall {

    /**
     * The cryptocurrencies that this website can use
     */
    private CryptoCurrency[] acceptedCryptoCurrencies;

    /**
     * The fiat currencies that this website can use
     */
    private FiatCurrency[] acceptedFiatCurrencies;

    /**
     * The activity that holds which currencies are being used right now
     */
    protected CurrencyActivity activity;

    /**
     * The name of the endpoint
     */
    private String name;


    public AbstractAPICall(String name, CurrencyActivity activity, CryptoCurrency[] acceptedCryptoCurrencies,
                           FiatCurrency[] acceptedFiatCurrencies) {
        this.acceptedCryptoCurrencies = acceptedCryptoCurrencies;
        this.acceptedFiatCurrencies = acceptedFiatCurrencies;
        this.activity = activity;
        this.name = name;
    }//end AbstractAPICall

    /**
     * Returns if this API uses the given cryptocurrency
     * @param crypto The given cryptocurrency
     * @return If this API uses the given cryptocurrency
     */
    private boolean canUseCryptocurrency(@NonNull final CryptoCurrency crypto) {
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
    private boolean canUseFiatCurrency(@NonNull final FiatCurrency fiat) {
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
    public abstract double extractPrice(String response);

    /**
     * Gets the name to print to the screen
     * @return The name of the endpoint to print to the screen
     */
    public String getName() {
        return this.name;
    }//end getName()
}//end AbstractAPICall