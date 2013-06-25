package ptp.coding.blackjack.game;

/**
 * Strategy is the evaluation of hands in that the decision on
 * taking a card or not is done by an extension of this class
 * @author andrew
 *
 */
abstract class Strategy {
	
	public Strategy(){}
	
	/**
	 * takeCard is the method that will evaluate the standing in order to take a card or not
	 * 
	 * @param currentCount - the count of this players hand
	 * @param opponentCount - the opponent's count
	 * @return true if the strategy says to take a card
	 */
	abstract public boolean takeCard(int currentCount, int opponentCount);
	
} // end Strategy
