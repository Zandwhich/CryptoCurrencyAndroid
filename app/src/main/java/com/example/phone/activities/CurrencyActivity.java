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
     * @return The current cyrptocurrency being used
     */
    CryptoCurrency getCurrentCrypto();
}//end CurrencyActivity
