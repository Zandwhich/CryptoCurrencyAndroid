package com.example.phone.utility.network.endpoints.CoinBase;

import com.example.phone.activities.CurrencyActivity;

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
    private static final String EXT_NAME = "Spot";

    /**
     * The constructor for the CoinBase 'spot' URL
     */
    public CoinBaseSpot(CurrencyActivity activity) {
        super(CoinBaseSpot.EXT_NAME, activity, CoinBaseSpot.SPOT_EXT);
    }//end CoinBaseSpot()

    /**
     * The constructor used only for figuring out if this endpoint can support certain currencies.
     * <br><br>
     * <b>DON'T USE THIS CONSTRUCTOR FOR ACTUALLY CONNECTING TO THE ENDPOINTS THEMSELVES!!</b>
     */
    public CoinBaseSpot() {
        super(CoinBaseSpot.EXT_NAME, null, CoinBaseSpot.SPOT_EXT);
    }
}//end CoinBaseSpot
