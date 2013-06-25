package ptp.coding.blackjack.game;

/**
 * This is a place holder for a player strategy class
 * 
 * @author andrew
 *
 */
public class PlayerStrategy extends Strategy {

	protected String[][] strategy;
	
	public PlayerStrategy(){
	} // end constructor
	
	public boolean takeCard(int currentCount, int opponentCount){
		return true;
	} // end takeCard
	
} // end PlayerStrategy
