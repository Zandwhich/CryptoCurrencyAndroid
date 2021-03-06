package com.example.phone.utility.network.endpoints.CoinBase;

import com.example.phone.activities.CurrencyActivity;

/**
 * The implementation of the CoinBase API that hits the 'sell' endpoint
 */
public final class CoinBaseSell extends AbstractCoinBase {

    /**
     * The extension that hits the 'sell' endpoint at CoinBase
     */
    private static final String SELL_EXT = "/sell";

    /**
     * The name of the endpoint
     */
    private static final String EXT_NAME = "Sell";

    /**
     * The constructor for the CoinBase 'sell' URL
     */
    public CoinBaseSell(CurrencyActivity activity) {
        super(CoinBaseSell.EXT_NAME, activity, CoinBaseSell.SELL_EXT);
    }//end CoinBaseBuy()
}//end CoinBaseSell
