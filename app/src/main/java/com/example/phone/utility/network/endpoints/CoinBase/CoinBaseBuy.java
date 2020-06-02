package com.example.phone.utility.network.endpoints.CoinBase;

import com.example.phone.CurrencyActivity;

/**
 * The class for CoinBase calls for "buy" prices
 */
public final class CoinBaseBuy extends AbstractCoinBase {

    /**
     * The extension that hits the "buy" endpoint at CoinBase
     */
    private static final String BUY_EXT = "/buy";

    /**
     * The name of the endpoint
     */
    private static final String EXT_NAME = "CoinBase Buy";

    /**
     * The constructor for the CoinBase 'buy' URL
     */
    public CoinBaseBuy(CurrencyActivity activity) {
        super(CoinBaseBuy.EXT_NAME, activity, CoinBaseBuy.BUY_EXT);
    }//end CoinBaseBuy()
}//end CoinBaseBuy
