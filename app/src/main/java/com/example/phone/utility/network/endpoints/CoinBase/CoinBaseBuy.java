package com.example.phone.utility.network.endpoints.CoinBase;

import com.example.phone.activities.CurrencyInterface;

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
    private static final String EXT_NAME = "Buy";

    /**
     * The constructor for the CoinBase 'buy' URL
     */
    public CoinBaseBuy(CurrencyInterface activity) {
        super(CoinBaseBuy.EXT_NAME, activity, CoinBaseBuy.BUY_EXT);
    }//end CoinBaseBuy()

    /**
     * The constructor used only for figuring out if this endpoint can support certain currencies.
     * <br><br>
     * <b>DON'T USE THIS CONSTRUCTOR FOR ACTUALLY CONNECTING TO THE ENDPOINTS THEMSELVES!!</b>
     */
    public CoinBaseBuy() {
        super(CoinBaseBuy.EXT_NAME, null, CoinBaseBuy.BUY_EXT);
    }
}//end CoinBaseBuy
