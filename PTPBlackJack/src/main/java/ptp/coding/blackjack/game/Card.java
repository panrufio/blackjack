package ptp.coding.blackjack.game;

import java.util.HashSet;

/**
 * Card is a playing card
 * 
 * @author andrew
 *
 */
public class Card {
	
	protected String value = null;
	
	// valid suits
	protected static String[] C_SUITS = { "C", "D", "H", "S" };

	// valid values
	protected static String[] C_VALUES = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
	
	
	/**
	 * Constructor with a value
	 * 
	 * @param v - the assigned value of the card
	 */
	public Card(String v){
		
		value = new String(v);
		
	} // end constructor

	
	/**
	 * Constructor with Card
	 * 
	 * @param c - the card that this will be
	 */
	public Card(Card c){

		value = new String(c.getValue());

	} // end constructor
	
	
	/**
	 * 
	 * @return - the string that is the value of the card
	 */
	public String getValue(){
		return value;
	}

	
	/**
	 * 
	 * @return - the numerical value of the card
	 */
	public int getFaceValue(){
		
		// check
		if(value == null){
			return 0;
		}

		// check on the suit of the card
		boolean fs = false;
		for(int x = 0; x < C_SUITS.length; x++){
			if(value.endsWith(C_SUITS[x])){
				fs = true;
				break;
			}
		}
		
		// did not find the suit
		if(!fs){
			return 0;
		}
		
		// check on the face value of the card
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

	} // end getFaceValue

	
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
