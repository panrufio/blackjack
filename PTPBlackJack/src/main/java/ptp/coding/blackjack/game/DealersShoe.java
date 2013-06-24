package ptp.coding.blackjack.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * DealersShoe is an implementation of the shoe used for Black Jack.
 * 
 * @author andrew
 *
 */
public class DealersShoe implements Iterator{

	protected int DS_DECKS = 1;
	protected double DS_SHUFFLE_AT = 0.8; // 0.0 for continuous shuffle
	//protected boolean DS_CUT = true;
	
	
	
	private Card[] DS_Shoe;
	private Random DS_Rand = null;
	protected static String[] DS_BASEDECK = {
			"AC", "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC",
			"AD", "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD",
			"AH", "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH",
			"AS", "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS"
	};
	
	private int DS_CurCard = 0;
	
	
	public DealersShoe(){
		createShoe();
		shuffle();
	} // end basic constructor
	
	public DealersShoe(int decks){
		DS_DECKS = decks;
		createShoe();
		shuffle();
	} // end constructor
	
	public void setShuffleAt(double d){
		if(d >= 0 && d <= 1){
			DS_SHUFFLE_AT = d;
		}
	}
	

	private void createShoe(){
		DS_Shoe = new Card[DS_DECKS * DS_BASEDECK.length];
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
		
		for(int x = 0; x < DS_Shoe.length; x++){
			int p1 = DS_Rand.nextInt(DS_Shoe.length);
			int p2 = DS_Rand.nextInt(DS_Shoe.length);
			if(p1 != p2){
				Card tmp = DS_Shoe[p1];
				DS_Shoe[p1] = DS_Shoe[p2];
				DS_Shoe[p2] = tmp;				
			}
		}
		DS_CurCard = 0;
	} // end shuffle
	
	public int getDealtCount(){
		return DS_CurCard;
	} // end getDepth
	public double getDepth(){
		double retVal = (double) DS_CurCard / (double) DS_Shoe.length;
		return retVal;
	} // end getDepth
	
	public boolean shouldShuffle(){
		if(DS_CurCard == 0){
			return false;
		}
		if(getDepth() >= DS_SHUFFLE_AT){
			return true;
		} else {
			return false;
		}		
	}
	
	public int getNumberCards(){
		return DS_Shoe.length;
	}
	
	public boolean hasNext(){
		return true;
	} // end hasNext
	public void remove(){} // end remove
	public Card next(){
		if(DS_CurCard >= DS_Shoe.length){
			shuffle();
			DS_CurCard = 0;
		}
		Card retCard = DS_Shoe[DS_CurCard];
		DS_CurCard++;
		return retCard;
		
	} // end next
	
	
	
	
	
} // end DealersShow
