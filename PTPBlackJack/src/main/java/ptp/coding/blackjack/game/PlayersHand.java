package ptp.coding.blackjack.game;

import java.util.ArrayList;

/**
 * PlayersHand is a class that serves to keep track of the card of a black jack hand.
 * 
 * @author andrew
 *
 */
public class PlayersHand {

	// the cards of the hand
	protected ArrayList<Card> PH_Hand;

	protected boolean PH_isDealer = false;
	protected boolean PH_isBusted = false;
	
	// count of aces in the hand
	protected int PH_aceCnt = 0;

	// is this a soft count
	protected boolean PH_isSoft = false;

	// best hand
	protected int PH_BEST = 21;
	
	
	/**
	 * basic constructor
	 */
	public PlayersHand(){
		PH_Hand = new ArrayList<Card>();		
	} // end constructor

	
	/**
	 * Dealear constructor
	 * 
	 * @param b - true for setting this hand as the hand of the dealer
	 */
	public PlayersHand(boolean b){
		PH_Hand = new ArrayList<Card>();
		PH_isDealer = b;
	} // end constructor

	
	/**
	 * Constructor for player with an initial card
	 * 
	 * @param c - first card in the hand
	 */
	public PlayersHand(Card c){
		PH_Hand = new ArrayList<Card>();
		PH_Hand.add(c);
	} // end constructor
	

	/**
	 * Dealer constructor with card
	 * 
	 * @param b - true for setting this hand as the hand of the dealer
	 * @param c - first card in the hand
	 */
	public PlayersHand(boolean b, Card c){
		PH_isDealer = b;
		PH_Hand = new ArrayList<Card>();
		PH_Hand.add(c);
	}

	/**
	 * addCard will add a card to the hand
	 * 
	 * @param c - card to add
	 */
	public void addCard(Card c){
		PH_Hand.add(c);
	} // end addCard
	

	/**
	 * showing with display the cards showing for the hand.
	 * If this is a dealers hand, then the down card shows as XX
	 * 
	 * @return the sum of the cards or the value of the one card for the dealer
	 */
	public int showing(){
		if(PH_Hand.size() == 0){
			return 0;
		}
		if(PH_isDealer){
			if(PH_Hand.get(0).isAce()){
				return 11;
			}
			
			return PH_Hand.get(0).getFaceValue();
		} else {
			return getHandValue();
		}
	} // end showing
	
	
	/**
	 * getHandValue() counts up the cards in the hand
	 * 
	 * @return the value of the hand
	 */
	public int getHandValue(){

		int count = 0;

		// reset items
		PH_aceCnt = 0;
		PH_isSoft = false;
		PH_isBusted = false;

		// go through the hand
		for(int x = 0; x < PH_Hand.size(); x++){
			if(PH_Hand.get(x).isAce()){
				PH_aceCnt++;
			}
			count += PH_Hand.get(x).getFaceValue();
			
		}

		// see if an ace can be used as 11
		if(PH_aceCnt > 0 && count <= PH_BEST - 10){
			count += 10;
			PH_isSoft = true;
		}

		// see if we are busted
		if(count > PH_BEST){
			PH_isBusted = true;
		}
		
		return count;

	} // end getHandValue
	
	
	public boolean isDealer(){
		return PH_isDealer;
	}

	
	public boolean isPlayer(){
		return !PH_isDealer;
	}
	
	
	public boolean isSoft(){
		return PH_isSoft;
		
	}
	
	
	public int getNumberCards(){
		return PH_Hand.size();
	}

	
	public void resetHand(){
		PH_Hand.clear();
		PH_isBusted = false;
		PH_aceCnt = 0;
		PH_isSoft = false;
	} // end resetHand

	
	/**
	 * showCard allows for individual cards to be shown
	 * 
	 * @param n - is the individual card to show
	 * @return - the value of the card n
	 */
	public String showCard(int n){
		if(n > PH_Hand.size()){
			return "";
		} else {
			return PH_Hand.get(n).toString();
		}
	} // end showCard

	
	/**
	 * override of toString to show the whole hand
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int x = 0; x < PH_Hand.size(); x++){
			sb.append(PH_Hand.get(x).toString());
			if(x < PH_Hand.size() - 1){
				sb.append(", ");
			}
		}
		return sb.toString();
	} // end toString
	
	
} // end PlayersHand
