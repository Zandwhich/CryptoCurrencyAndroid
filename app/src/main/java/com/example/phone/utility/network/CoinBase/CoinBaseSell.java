package com.example.phone.utility.network.CoinBase;

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
    private static final String NAME = "CoinBase Sell";

    /**
     * The constructor for the CoinBase 'sell' URL
     */
    public CoinBaseSell() {
        super(CoinBaseSell.SELL_EXT);
    }//end CoinBaseBuy()

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return CoinBaseSell.NAME;
    }//end getName()
}//end CoinBaseSell
