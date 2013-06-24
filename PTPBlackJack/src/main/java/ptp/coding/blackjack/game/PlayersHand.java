package ptp.coding.blackjack.game;

import java.util.ArrayList;

public class PlayersHand {

	protected ArrayList<Card> PH_Hand;
	protected boolean PH_isDealer = false;
	protected boolean PH_isBusted = false;
	protected int PH_aceCnt = 0;
	protected boolean PH_isSoft = false;
	protected int PH_BEST = 21;
	
	public PlayersHand(){
		PH_Hand = new ArrayList<Card>();
		
	}
	
	public PlayersHand(boolean b){
		PH_Hand = new ArrayList<Card>();
		PH_isDealer = b;
	} // end basic constructor

	public PlayersHand(Card c){
		PH_Hand = new ArrayList<Card>();
		PH_Hand.add(c);
	}
	
	public PlayersHand(boolean b, Card c){
		PH_isDealer = b;
		PH_Hand = new ArrayList<Card>();
		PH_Hand.add(c);
	}

	public void addCard(Card c){
		PH_Hand.add(c);
	}
	
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
	
	public int getHandValue(){
		int count = 0;
		PH_aceCnt = 0;
		PH_isSoft = false;
		PH_isBusted = false;
		for(int x = 0; x < PH_Hand.size(); x++){
			if(PH_Hand.get(x).isAce()){
				PH_aceCnt++;
			}
			count += PH_Hand.get(x).getFaceValue();
			
		}
		if(PH_aceCnt > 0 && count <= PH_BEST - 10){
			count += 10;
			PH_isSoft = true;
		}
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
		
	} // end 
	
	public int getNumberCards(){
		return PH_Hand.size();
	}
	
	public void resetHand(){
		PH_Hand.clear();
		PH_isBusted = false;
		PH_aceCnt = 0;
		PH_isSoft = false;
	} // end resetHand
	
	public String showCard(int n){
		if(n > PH_Hand.size()){
			return "";
		} else {
			return PH_Hand.get(n).toString();
		}
	}
	
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
