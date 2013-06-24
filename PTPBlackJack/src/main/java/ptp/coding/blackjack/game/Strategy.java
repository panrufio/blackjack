package ptp.coding.blackjack.game;

abstract class Strategy {

	
	
	public Strategy(){
		
	}
	
	abstract public boolean takeCard(int currentCount, int opponentCount);
	
	
} // end Strategy
