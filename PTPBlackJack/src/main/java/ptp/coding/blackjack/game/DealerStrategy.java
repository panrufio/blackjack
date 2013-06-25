package ptp.coding.blackjack.game;

/**
 * This is the strategy for a dealer.  It is simple - take until over 17 or busted
 * 
 * @author andrew
 *
 */
public class DealerStrategy extends Strategy {

	public DealerStrategy(){} // end constructor
	
	public boolean takeCard(int currentCount, int opponentCount){
		if(currentCount < 17){
			return true;
		}
		return false;
	} // end DealerStrategy
	
} // end DealerStrategy
