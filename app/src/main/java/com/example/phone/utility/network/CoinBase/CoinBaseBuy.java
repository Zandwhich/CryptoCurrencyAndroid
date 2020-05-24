package com.example.phone.utility.network.CoinBase;

/**
 * The class for CoinBase calls for "buy" prices
 */
public final class CoinBaseBuy extends AbstractCoinBase {

    /**
     * The extension that hits the "buy" endpoint at CoinBase
     */
    private static final String BUY_EXT = "/buy";

    /**
     * The constructor for the CoinBase 'buy' URL
     */
    public CoinBaseBuy() {
        super(CoinBaseBuy.BUY_EXT);
    }//end CoinBaseBuy()
}//end CoinBaseBuy
