package ptp.coding.blackjack.game;

public class Dealer {

	protected PlayersHand D_Hand;
	protected DealerStrategy D_Strategy;
	
	protected int wins = 0;
	protected int losses = 0;
	protected int ties = 0;
	
	public Dealer(){
		D_Hand = new PlayersHand(true);
		D_Strategy = new DealerStrategy();
	} // end constructor

	public boolean takeCard(int showing){
		return D_Strategy.takeCard(D_Hand.getHandValue(), showing);		
	} // end takeCard
	
	public void getCard(Card c){
		D_Hand.addCard(c);
	}
	
	public String showCurrentHand(){
		if(D_Hand.getNumberCards() > 0){
			return D_Hand.showCard(0) + ", XX";
		}
		return null;
		
	} // end showCurrentHand
	
	public void lost(){
		losses++;
	}
	public void won(){
		wins++;
	}
	public void tie(){
		ties++;
	}
	
} // end Dealer
