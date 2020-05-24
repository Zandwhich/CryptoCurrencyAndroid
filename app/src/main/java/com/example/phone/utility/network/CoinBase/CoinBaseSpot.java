package com.example.phone.utility.network.CoinBase;

/**
 * The implementation of the CoinBase API that hits the 'spot' endpoint
 */
public final class CoinBaseSpot extends AbstractCoinBase {

    /**
     * The extension that hits the 'spot' endpoint at CoinBase
     */
    private static final String SPOT_EXT = "/spot";

    /**
     * The constructor for the CoinBase 'spot' URL
     */
    public CoinBaseSpot() {
        super(CoinBaseSpot.SPOT_EXT);
    }//end CoinBaseSpot()
}//end CoinBaseSpot
