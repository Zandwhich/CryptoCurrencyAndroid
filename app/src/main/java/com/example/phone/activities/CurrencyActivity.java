package com.example.phone.activities;

import com.example.phone.utility.currencies.CryptoCurrency;
import com.example.phone.utility.currencies.FiatCurrency;

/**
 * The activity that holds the current fiat and cryptocurrencies needs to extend this
 * TODO: Change name?
 */
public interface CurrencyActivity {

    /**
     * Returns the current fiat currency being used
     * @return The current fiat currency being used
     */
    FiatCurrency getCurrentFiat();

    /**
     * Returns the current cryptocurrency being used
     * @return The current cryptocurrency being used
     */
    CryptoCurrency getCurrentCrypto();

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
