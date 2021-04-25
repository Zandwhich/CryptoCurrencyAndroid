package com.example.phone.utility.currencies;

import android.content.Context;

import com.example.phone.R;

import java.util.HashMap;
import java.util.Map;

/**
 * An enum that holds all possible fiat currencies
 */
public enum FiatCurrency implements Currency {

    //    AED, // United Arab Emirates Dirham
//    AFN, // Afghan Afghani
//    ALL, // Albanian Lek
//    AMD, // Armenian Dram
//    ANG, // Netherlands Antillean Gulden
//    AOA, // Angolan Kwanza
//    ARS, // Argentine Peso
    AUD, // Australian Dollar
    //    AWG, // Aruban Florin
//    AZN, // Azerbaijani Manat
//    BAM, // Bosnia and Herzegovina Convertible Mark
//    BBD, // Barbadian Dollar
//    BDT, // Bangladeshi Taka
//    BGN, // Bulgarian Lev
//    BHD, // Bahraini Dinar
//    BIF, // Burundian Franc
//    BMD, // Bermudian Dollar
//    BND, // Brunei Dollar
//    BOB, // Bolivian Boliviano
//    BRL, // Brazilian Real
//    BSD, // Bahamian Dollar
//    BTC, // Bitcoin
//    BTN, // Bhutanese Ngultrum
//    BWP, // Botswana Pula
//    BYN, // Belarusian Ruble
//    BYR, // Belarusian Ruble
//    BZD, // Belize Dollar
    CAD, // Canadian Dollar
    //    CDF, // Congolese Franc
//    CHF, // Swiss Franc
//    CLF, // Unidad de Fomento
//    CLP, // Chilean Peso
//    CNH, // Chinese Renminbi Yuan Offshore
//    CNY, // Chinese Renminbi Yuan
//    COP, // Colombian Peso
//    CRC, // Costa Rican Colón
//    CUC, // Cuban Convertible Peso
//    CVE, // Cape Verdean Escudo
//    CZK, // Czech Koruna
//    DJF, // Djiboutian Franc
//    DKK, // Danish Krone
//    DOP, // Dominican Peso
//    DZD, // Algerian Dinar
//    EEK, // Estonian Kroon
//    EGP, // Egyptian Pound
//    ERN, // Eritrean Nakfa
//    ETB, // Ethiopian Birr
//    ETH, // Ethereum
    EUR, // Euro
    //    FJD, // Fijian Dollar
//    FKP, // Falkland Pound
    GBP, // British Pound
    //    GEL, // Georgian Lari
//    GGP, // Guernsey Pound
//    GHS, // Ghanaian Cedi
//    GIP, // Gibraltar Pound
//    GMD, // Gambian Dalasi
//    GNF, // Guinean Franc
//    GTQ, // Guatemalan Quetzal
//    GYD, // Guyanese Dollar
//    HKD, // Hong Kong Dollar
//    HNL, // Honduran Lempira
//    HRK, // Croatian Kuna
//    HTG, // Haitian Gourde
//    HUF, // Hungarian Forint
//    IDR, // Indonesian Rupiah
//    ILS, // Israeli New Sheqel
//    IMP, // Isle of Man Pound
//    INR, // Indian Rupee
//    IQD, // Iraqi Dinar
//    ISK, // Icelandic Króna
//    JEP, // Jersey Pound
//    JMD, // Jamaican Dollar
//    JOD, // Jordanian Dinar
    JPY, // Japanese Yen
    //    KES, // Kenyan Shilling
//    KGS, // Kyrgyzstani Som
//    KHR, // Cambodian Riel
//    KMF, // Comorian Franc
//    KRW, // South Korean Won
//    KWD, // Kuwaiti Dinar
//    KYD, // Cayman Islands Dollar
//    KZT, // Kazakhstani Tenge
//    LAK, // Lao Kip
//    LBP, // Lebanese Pound
//    LKR, // Sri Lankan Rupee
//    LRD, // Liberian Dollar
//    LSL, // Lesotho Loti
//    LTC, // Litecoin
//    LTL, // Lithuanian Litas
//    LVL, // Latvian Lats
//    LYD, // Libyan Dinar
//    MAD, // Moroccan Dirham
//    MDL, // Moldovan Leu
//    MGA, // Malagasy Ariary
//    MKD, // Macedonian Denar
//    MMK, // Myanmar Kyat
//    MNT, // Mongolian Tögrög
//    MOP, // Macanese Pataca
//    MRO, // Mauritanian Ouguiya
//    MTL, // Maltese Lira
//    MUR, // Mauritian Rupee
//    MVR, // Maldivian Rufiyaa
//    MWK, // Malawian Kwacha
    MXN, // Mexican Peso
    //    MYR, // Malaysian Ringgit
//    MZN, // Mozambican Metical
//    NAD, // Namibian Dollar
//    NGN, // Nigerian Naira
//    NIO, // Nicaraguan Córdoba
//    NOK, // Norwegian Krone
//    NPR, // Nepalese Rupee
    NZD, // New Zealand Dollar
    //    OMR, // Omani Rial
//    PAB, // Panamanian Balboa
//    PEN, // Peruvian Sol
//    PGK, // Papua New Guinean Kina
//    PHP, // Philippine Peso
//    PKR, // Pakistani Rupee
    PLN, // Polish Złoty
    //    PYG, // Paraguayan Guaraní
//    QAR, // Qatari Riyal
//    RON, // Romanian Leu
//    RSD, // Serbian Dinar
//    RUB, // Russian Ruble
//    RWF, // Rwandan Franc
//    SAR, // Saudi Riyal
//    SBD, // Solomon Islands Dollar
//    SCR, // Seychellois Rupee
    SEK, // Swedish Krona
    //    SGD, // Singapore Dollar
//    SHP, // Saint Helenian Pound
//    SLL, // Sierra Leonean Leone
//    SOS, // Somali Shilling
//    SRD, // Surinamese Dollar
//    SSP, // South Sudanese Pound
//    STD, // São Tomé and Príncipe Dobra
//    SVC, // Salvadoran Colón
//    SZL, // Swazi Lilangeni
//    THB, // Thai Baht
//    TJS, // Tajikistani Somoni
//    TMT, // Turkmenistani Manat
//    TND, // Tunisian Dinar
//    TOP, // Tongan Paʻanga
//    TRY, // Turkish Lira
//    TTD, // Trinidad and Tobago Dollar
//    TWD, // New Taiwan Dollar
//    TZS, // Tanzanian Shilling
//    UAH, // Ukrainian Hryvnia
//    UGX, // Ugandan Shilling
    USD, // United States Dollar
//    UYU, // Uruguayan Peso
//    UZS, // Uzbekistan Som
//    VEF, // Venezuelan Bolívar
//    VND, // Vietnamese Đồng
//    VUV, // Vanuatu Vatu
//    WST, // Samoan Tala
//    XAF, // Central African Cfa Franc
//    XAG, // Silver (Troy Ounce)
//    XAU, // Gold (Troy Ounce)
//    XCD, // East Caribbean Dollar
//    XDR, // Special Drawing Rights
//    XOF, // West African Cfa Franc
//    XPD, // Palladium
//    XPF, // Cfp Franc
//    XPT, // Platinum
//    YER, // Yemeni Rial
//    ZAR, // South African Rand
//    ZEC, // Zcash
//    ZMK, // Zambian Kwacha
//    ZMW, // Zambian Kwacha
//    ZWL // Zimbabwean Dollar
    ;

    /* ************ */
    /*  Constants   */
    /* ************ */

    /**
     * The default fiat currency
     *
     * TODO: Should we allow this to be changed in the future?
     */
    public static final FiatCurrency DEFAULT_FIAT = USD;

    /**
     * Multiple endpoints use this mapping, so I've provided it here
     */
    public static final Map<FiatCurrency, String> abbreviatedMap =
            new HashMap<FiatCurrency, String>() {{
                put(FiatCurrency.AUD, FiatCurrency.AUD.toString());
                put(FiatCurrency.CAD, FiatCurrency.CAD.toString());
                put(FiatCurrency.EUR, FiatCurrency.EUR.toString());
                put(FiatCurrency.GBP, FiatCurrency.GBP.toString());
                put(FiatCurrency.JPY, FiatCurrency.JPY.toString());
                put(FiatCurrency.MXN, FiatCurrency.MXN.toString());
                put(FiatCurrency.NZD, FiatCurrency.NZD.toString());
                put(FiatCurrency.PLN, FiatCurrency.PLN.toString());
                put(FiatCurrency.SEK, FiatCurrency.SEK.toString());
                put(FiatCurrency.USD, FiatCurrency.USD.toString());
            }};


    /* ************ */
    /*    Fields    */
    /* ************ */

    /**
     * The full name of the fiat currency (R.string key)
     */
    private int fullName;

    /**
     * The abbreviated name of the fiat currency (R.string key)
     */
    private int abbreviatedName;

    /* ************ */
    /* Initializers */
    /* ************ */

    /*
     * Static initializer that sets the full names and the abbreviated names of the fiat currencies being used
     */
    static {

        // AUD
        AUD.fullName = R.string.aud_full_name;
        AUD.abbreviatedName = R.string.aud_abbreviation;

        // CAD
        CAD.fullName = R.string.cad_full_name;
        CAD.abbreviatedName = R.string.cad_abbreviation;

        // EUR
        EUR.fullName = R.string.eur_full_name;
        EUR.abbreviatedName = R.string.eur_abbreviation;

        // GBP
        GBP.fullName = R.string.gbp_full_name;
        GBP.abbreviatedName = R.string.gbp_abbreviation;

        // JPY
        JPY.fullName = R.string.jpy_full_name;
        JPY.abbreviatedName = R.string.jpy_abbreviation;

        // MXN
        MXN.fullName = R.string.mxn_full_name;
        MXN.abbreviatedName = R.string.mxn_abbreviation;

        // NZD
        NZD.fullName = R.string.nzd_full_name;
        NZD.abbreviatedName = R.string.nzd_abbreviation;

        // PLN
        PLN.fullName = R.string.pln_full_name;
        PLN.abbreviatedName = R.string.pln_abbreviation;

        // SEK
        SEK.fullName = R.string.sek_full_name;
        SEK.abbreviatedName = R.string.sek_abbreviation;

        // USD
        USD.fullName = R.string.usd_full_name;
        USD.abbreviatedName = R.string.usd_abbreviation;
    }// end static initializer


    /* ************ */
    /*    Methods   */
    /* ************ */

    /* Public */

    // Getters

    /**
     * Returns the full name of the fiat currency
     * @return The full name of the fiat currency
     */
    @Override
    public int getFullName() {
        return this.fullName;
    }// end getFullName()

    /**
     * Returns the abbreviated name of the fiat currency
     * @return The abbreviated name of the fiat currency
     */
    @Override
    public int getAbbreviatedName() {
        return this.abbreviatedName;
    }//end getAbbreviatedName()

    /**
     * Returns the corresponding fiat currency for which this abbreviated name corresponds;
     * null if no match
     * @param abbreviatedName The abbreviated name of the fiat currency
     * @return The fiat currency for which the name corresponds; null if no match
     */
    public static FiatCurrency getFiatCurrencyFromAbbreviatedName(int abbreviatedName) {
        for (FiatCurrency fiat : FiatCurrency.values()) {
            if (abbreviatedName == fiat.abbreviatedName) {
                return fiat;
            }
        }
        return null;
    }

    /**
     * Returns the corresponding fiat currency for which this full name corresponds;
     * null if no match
     * @param fullName The full name of the fiat currency
     * @return The fiat currency for which the corresponds; null if no match
     */
    public static FiatCurrency getFiatCurrencyFromFullName(int fullName) {
        for (FiatCurrency fiat : FiatCurrency.values()) {
            if (fullName == fiat.fullName) {
                return fiat;
            }
        }
        return null;
    }

    /**
     * Returns the FiatCurrency for which this abbreviated name matches;
     * null if there is no match
     * @param abbreviatedName The abbreviated name of the fiat currency
     * @param context The context (so that <code>getString</code> can be run)
     * @return The fiat currency for which this abbreviated name corresponds; null if no match
     */
    public static FiatCurrency getFiatCurrencyFromAbbreviatedName(String abbreviatedName, Context context) {
        for (FiatCurrency fiat : FiatCurrency.values()) {
            if (abbreviatedName.equals(context.getString(fiat.abbreviatedName))) {
                return fiat;
            }
        }
        return null;
    }

    /**
     * Returns the FiatCurrency for which this full name matches;
     * null if there is no match
     * @param fullName The full name of the fiat currency
     * @param context The context (so that <code>getString</code> can be run)
     * @return The fiat currency for which this full name corresponds; null if no match
     */
    public static FiatCurrency getFiatCurrencyFromFullName(String fullName, Context context) {
        for (FiatCurrency fiat : FiatCurrency.values()) {
            if (fullName.equals(context.getString(fiat.fullName))) {
                return fiat;
            }
        }
        return null;
    }
}//end FiatCurrency
