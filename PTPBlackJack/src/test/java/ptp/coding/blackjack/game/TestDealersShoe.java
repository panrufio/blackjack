package ptp.coding.blackjack.game;

import junit.framework.TestCase;

public class TestDealersShoe extends TestCase {

	public void setUp(){} // end setUp
	
	public void tearDown(){} // end tearDown
	
	public void testDeckSizes(){
		for(int x = 1; x <= 6; x++){
			DealersShoe ds = new DealersShoe(x);
			assertEquals(DealersShoe.DS_BASEDECK.length * x, ds.getNumberCards());
		}
		
	} // end testOneDeck

	public void testShuffle(){
		DealersShoe ds = new DealersShoe();
		Card[] c = new Card[ds.getNumberCards()];
		for(int x = 0; x < c.length; x++){
			c[x] = ds.next();
		}
		ds.shuffle();
		int same = 0;
		int diff = 0;
		for(int x = 0; x < c.length; x++){
			if(c[x].equals(ds.next())){
				same++;
			} else {
				diff++;
			}
		}
		assertTrue(diff > same);
		
	} // end testShuffle
	
	public void testShouldShuffle(){
		
		DealersShoe ds = new DealersShoe();
		ds.setShuffleAt(0.0);
		ds.next();
		assertTrue(ds.shouldShuffle());
		ds.shuffle();
		assertFalse(ds.shouldShuffle());
		ds.setShuffleAt(0.5);
		for(int x = 0; x < (ds.getNumberCards() / 2); x++){
			assertFalse(ds.shouldShuffle());
			ds.next();
		}
		assertTrue(ds.shouldShuffle());
		ds.shuffle();
		assertFalse(ds.shouldShuffle());
	} // end testShouldShuffle
	
	
	
} // end TestDealersShoe
