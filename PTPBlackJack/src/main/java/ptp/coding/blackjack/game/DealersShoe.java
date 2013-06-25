package ptp.coding.blackjack.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * DealersShoe is an implementation of the shoe used for Black Jack.
 * This implements Iterator so that the shoe can hand out cards with .next().
 * 
 * @author andrew
 *
 */
public class DealersShoe implements Iterator{

	// set the default number of decks
	protected int DS_DECKS = 1;

	// value for determining when to shuffle
	protected double DS_SHUFFLE_AT = 0.8; // 0.0 for continuous shuffle
	
	private Card[] DS_Shoe;

	// use Random for help in the shuffle
	private Random DS_Rand = null;

	// a base deck of cards
	protected static String[] DS_BASEDECK = {
			"AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC",
			"AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD",
			"AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH",
			"AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS"
	};

	// index of the current card that will be dealt
	private int DS_CurCard = 0;
	
	/**
	 * Basic constructor that creates the shoe and shuffles it
	 */
	public DealersShoe(){
		createShoe();
		shuffle();
	} // end basic constructor

	
	/**
	 * 
	 * @param decks - sets the number of decks used in the shoe
	 */
	public DealersShoe(int decks){
		DS_DECKS = decks;
		createShoe();
		shuffle();
	} // end constructor


	/**
	 * setShuffleAt() allows for a new value to be set to indicate a
	 * shuffle needs to occur
	 * 
	 * @param d - value to indicate that shuffle needs to be done
	 */
	public void setShuffleAt(double d){
		if(d >= 0 && d <= 1){
			DS_SHUFFLE_AT = d;
		}
	} // end setShuffleAt
	

	/**
	 * createShoe() with produce the array of all the cards in the shoe
	 */
	private void createShoe(){
		
		// allocate the space needed for the cards
		DS_Shoe = new Card[DS_DECKS * DS_BASEDECK.length];

		// go through multiple decks and assign the card values
		for(int x = 0; x < DS_DECKS; x++){
			for(int y = 0; y < DS_BASEDECK.length; y++){
				int pos = x * DS_BASEDECK.length + y;
				DS_Shoe[pos] = new Card(DS_BASEDECK[y]);
			}
		}
		
	} // end createShoe
	
	
	/**
	 * Shuffling is a run through the array of all cards and swap positions
	 */
	public void shuffle(){
		if(DS_Rand == null){
			DS_Rand = new Random(System.currentTimeMillis());
		}
		
		// touch every position in the deck once
		for(int x = 0; x < DS_Shoe.length; x++){
			int p1 = DS_Rand.nextInt(DS_Shoe.length);
			int p2 = DS_Rand.nextInt(DS_Shoe.length);
			if(p1 != p2){
				Card tmp = DS_Shoe[p1];
				DS_Shoe[p1] = DS_Shoe[p2];
				DS_Shoe[p2] = tmp;				
			}
		}
		
		// set the pointer of the next card at the top
		DS_CurCard = 0;
		
	} // end shuffle

	
	public int getDealtCount(){
		return DS_CurCard;
	} // end getDepth

	
	/**
	 * 
	 * @return - how much of the shoe has been used
	 */
	public double getDepth(){
		double retVal = (double) DS_CurCard / (double) DS_Shoe.length;
		return retVal;
	} // end getDepth
	

	/**
	 * 
	 * @return - true if the usage of the cards is beyond the threshold of where to shuffle
	 */
	public boolean shouldShuffle(){

		// check if we are at the top of the shoe
		if(DS_CurCard == 0){
			return false;
		}
		
		// evaluate the depth
		if(getDepth() >= DS_SHUFFLE_AT){
			return true;
		} else {
			return false;
		}
		
	} // end shouldShuffle

	
	public int getNumberCards(){
		return DS_Shoe.length;
	}

	/**
	 * the show always has more cards - it is BlackJack
	 */
	public boolean hasNext(){
		return true;
	} // end hasNext

	
	public void remove(){} // end remove
	
	
	/**
	 * hand out cards
	 */
	public Card next(){
		
		// shuffle if at the end
		if(DS_CurCard >= DS_Shoe.length){
			shuffle();
			DS_CurCard = 0;
		}
		
		Card retCard = DS_Shoe[DS_CurCard];
		DS_CurCard++;
		return retCard;
		
	} // end next
	
} // end DealersShow
