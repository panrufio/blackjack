package ptp.coding.blackjack.game;

import java.util.HashSet;

public class Card {
	protected String value = null;
	
	protected static String[] C_SUITS = { "C", "D", "H", "S" };
	protected static String[] C_VALUES = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	
	
	public Card(String v){
		
		value = new String(v);
		
	} // end constructor
	public Card(Card c){
		value = new String(c.getValue());
	}
	
	
	public String getValue(){
		return value;
	}

	public int getFaceValue(){
		int retVal = 0;
		if(value == null){
			return 0;
		}

		boolean fs = false;
		for(int x = 0; x < C_SUITS.length; x++){
			if(value.endsWith(C_SUITS[x])){
				fs = true;
				break;
			}
		}
		
		if(!fs){
			return 0;
		}
		
		for(int x = 0; x < C_VALUES.length; x++){
			if(value.startsWith(C_VALUES[x])){
				if(x >= 10){
					return 10;
				} else {
					return x+1;
				}
			}
		}
		return 0;
	}
	
	public boolean isAce(){
		if(value.startsWith("A")){
			return true;
		} else {
			return false;
		}
	}
	
	public boolean equals(Card c){
		return value.equals(c.getValue());
	}
	
	public String toString(){
		return value;
	}

} // end Card
