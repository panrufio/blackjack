package ptp.coding.blackjack.game;

public class DealerStrategy extends Strategy {

	public DealerStrategy(){} // end constructor
	
	public boolean takeCard(int currentCount, int opponentCount){
		if(currentCount < 17){
			return true;
		}
		return false;
	} // end DealerStrategy
	
} // end DealerStrategy
