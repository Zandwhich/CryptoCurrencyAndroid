package com.example.phone.utility.currencies;

/**
 * A parent enum to both FiatCurrency and CryptoCurrency
 */
public interface Currency {

    /**
     * Returns the full name of the currency
     * @return The full name of the currency
     */
    String getFullName();

    /**
     * Returns the abbreviated name of the currency
     * @return The abbreviated name of the currency
     */
    String getAbbreviatedName();

}//end Currency