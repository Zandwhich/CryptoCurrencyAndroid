package com.example.phone.activities;

import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;

/**
 * The activity that holds the current fiat and cryptocurrencies needs to extend this
 */
public interface CurrencyInterface {

    /**
     * Returns the base cryptocurrency
     * @return The base cryptocurrency
     */
    CryptoCurrency getBaseCrypto();

    /**
     * Returns the fiat currency that is being converted into
     * @return The fiat currency that is being converted into
     */
    FiatCurrency getTargetFiat();

    /**
     * Returns the target cryptocurrency that is being converted into
     * @return The target cryptocurrency that is being converted into
     */
    CryptoCurrency getTargetCrypto();

    /**
     * Returns the currency's name
     * @param orderInList The order that it's in the list
     * @return The name of the currency
     */
    String getCurrencyName(int orderInList);

    /**
     * Returns the currency's price
     * @param orderInList The order that it's in the list
     * @return The price of the currency
     */
    double getCurrencyPrice(int orderInList);
}//end CurrencyActivity
