package ptp.coding.blackjack.game;

/**
 * Player if the class to keep track of the players hand and the progress
 * of win/tie/loss
 * There is also a strategy available for the player.  So, if it desired
 * to automate playing, the strategy can be used.
 * 
 * 
 * @author andrew
 *
 */
public class Player {

	protected PlayersHand P_Hand;
	protected PlayerStrategy P_Strategy;
	
	protected int wins = 0;
	protected int losses = 0;
	protected int ties = 0;
	
	public Player(){
		P_Hand = new PlayersHand();
		P_Strategy = new PlayerStrategy();
	} // end constructor

	public boolean takeCard(int showing){
		return P_Strategy.takeCard(P_Hand.getHandValue(), showing);		
	} // end takeCard
	
	public void getCard(Card c){
		P_Hand.addCard(c);
	}
	
	public void lost(){
		losses++;
	}
	public void won(){
		wins++;
	}
	public void tie(){
		ties++;
	}
	
	public String toString(){
		
		return "wins = " + wins + " ties = " + ties + " losses = " + losses;
	} // end toString
	
} // end Player
