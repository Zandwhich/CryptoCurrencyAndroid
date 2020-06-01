package com.example.phone.utility.network.CoinBase;

import com.example.phone.CurrencyActivity;

/**
 * The implementation of the CoinBase API that hits the 'spot' endpoint
 */
public final class CoinBaseSpot extends AbstractCoinBase {

    /**
     * The extension that hits the 'spot' endpoint at CoinBase
     */
    private static final String SPOT_EXT = "/spot";

    /**
     * The name of the endpoint
     */
    private static final String NAME = "CoinBase Spot";

    /**
     * The constructor for the CoinBase 'spot' URL
     */
    public CoinBaseSpot(CurrencyActivity activity) {
        super(activity, CoinBaseSpot.SPOT_EXT);
    }//end CoinBaseSpot()

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return CoinBaseSpot.NAME;
    }//end getName()
}//end CoinBaseSpot
