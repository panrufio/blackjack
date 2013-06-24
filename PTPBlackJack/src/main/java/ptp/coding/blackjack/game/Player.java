package ptp.coding.blackjack.game;

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
	
} // end Player
