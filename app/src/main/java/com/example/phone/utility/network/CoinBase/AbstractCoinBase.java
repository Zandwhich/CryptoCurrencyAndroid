package com.example.phone.utility.network.CoinBase;

import android.net.Uri;

import com.example.phone.utility.currencies.CryptoCurrencies;
import com.example.phone.utility.currencies.FiatCurrencies;
import com.example.phone.utility.network.AbstractAPICall;

/**
 * The abstract class for CoinBase calls
 */
public abstract class AbstractCoinBase extends AbstractAPICall {

    /**
     * The base URL for the CoinBase API
     */
    public static final String BASE_URL = "https://api.coinbase.com/v2/prices/";

    /**
     * A list of the accepted cryptocurrencies (that we're also using) at the moment
     */
    public static final CryptoCurrencies[] ACCEPTED_CRYPTOCURRENCIES = {CryptoCurrencies.BTC,
            CryptoCurrencies.ETH, CryptoCurrencies.LTC, CryptoCurrencies.XRP};

    /**
     * A list of the accepted fiat currencies (that we're also using) at the moment
     */
    public static final FiatCurrencies[] ACCEPTED_FIAT_CURRENCIES = {FiatCurrencies.AUD,
            FiatCurrencies.CAD, FiatCurrencies.EUR, FiatCurrencies.JPY, FiatCurrencies.MXN,
            FiatCurrencies.NZD, FiatCurrencies.PLN, FiatCurrencies.SEK, FiatCurrencies.USD};

    /**
     * The CoinBase extension (one of 'buy', 'sell', or 'spot')
     */
    private String ext;

    /**
     * The constructor for the CoinBase website
     * @param ext The extension of (one of 'buy', 'sell', or 'spot')
     */
    AbstractCoinBase(String ext) {
        super(AbstractCoinBase.ACCEPTED_CRYPTOCURRENCIES,
                AbstractCoinBase.ACCEPTED_FIAT_CURRENCIES);
        this.ext = ext;
    }//end AbstractCoinBase()

    /**
     * {@inheritDoc}
     */
    @Override
    protected Uri buildUri(CryptoCurrencies crypto, FiatCurrencies fiat) {
        // TODO: In the future, figure out how to better utilize the Uri class
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
        // TODO: Fill this in
        return 0;
    }//end extractPrice()
}//end AbstractCoinBase
