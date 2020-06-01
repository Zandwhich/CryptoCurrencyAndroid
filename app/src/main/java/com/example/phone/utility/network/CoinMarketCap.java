package com.example.phone.utility.network;

import android.net.Uri;

import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;

/**
 * The class for CoinMarketCap calls
 */
final public class CoinMarketCap extends AbstractAPICall {

    // TODO: CoinMarketCap has changed the way it does its API calls. I'll probably have to
    //  make an account and come back and update this later

    /**
     * The base url for the CoinMarketCap website
     */
    private final static String BASE_URL = "https://api.coinmarketcap.com/v2/ticker/";

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
     * The number that CoinMarketCap uses for BTC in its URLs
     */
    private static final String BTC_NUMBER = "1";

    /**
     * The number that CoinMarketCap uses for ETH in its URLs
     */
    private static final String ETH_NUMBER = "1027";

    /**
     * The number that CoinMarketCap uses for LTC in its URLs
     */
    private static final String LTC_NUMBER = "2";

    /**
     * The number that CoinMarketCap uses for XRP in its URLs
     */
    private static final String XRP_NUMBER = "57";

    /**
     * The constructor for CoinMarketCap
     */
    public CoinMarketCap() {
        super(CoinMarketCap.ACCEPTED_CRYPTO_CURRENCIES, CoinMarketCap.ACCEPTED_FIAT_CURRENCIES);
    }//end CoinMarketCap()

    /**
     * Converts a given cryptocurrency to the appropriate string to put into the url
     * @param crypto The cryptocurrency to be converted
     * @return The CoinMarketCap specified number
     */
    private static String convertCryptoCurrency(CryptoCurrency crypto) {
        switch (crypto) {
            case BTC:
                return CoinMarketCap.BTC_NUMBER;
            case ETH:
                return CoinMarketCap.ETH_NUMBER;
            case LTC:
                return CoinMarketCap.LTC_NUMBER;
            case XRP:
                return CoinMarketCap.XRP_NUMBER;
            default:
                // TODO: We shouldn't get here, but maybe throw an error in the future?
                return "";
        }//end switch
    }//end convertCryptoCurrency()

    @Override
    protected Uri buildUri(CryptoCurrency crypto, FiatCurrency fiat) {
        return null;
    }//end buildUri()

    @Override
    public double extractPrice(String response) {
        /* PLEASE READ THE "TO DO" ABOVE */
        return 0;
    }//end extractPrice()

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        /* PLEASE READ THE "TO DO" ABOVE */
        return CoinMarketCap.NAME;
    }//end getName()


}//end CoinMarketCap
