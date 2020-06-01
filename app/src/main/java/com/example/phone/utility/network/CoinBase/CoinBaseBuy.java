package com.example.phone.utility.network.CoinBase;

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
    private static final String NAME = "CoinBase Buy";

    /**
     * The constructor for the CoinBase 'buy' URL
     */
    public CoinBaseBuy(CurrencyActivity activity) {
        super(activity, CoinBaseBuy.BUY_EXT);
    }//end CoinBaseBuy()

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return CoinBaseBuy.NAME;
    }//end getName()
}//end CoinBaseBuy
