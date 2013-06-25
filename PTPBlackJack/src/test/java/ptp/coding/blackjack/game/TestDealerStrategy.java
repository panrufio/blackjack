package ptp.coding.blackjack.game;

import junit.framework.TestCase;

public class TestDealerStrategy extends TestCase {

	public void setUp(){} // end setUp
	
	public void tearDown(){} // end tearDown

	public void testTakeCard(){
		
		DealerStrategy ds = new DealerStrategy();
		for(int x = 4; x < 17; x++){
			assertTrue(ds.takeCard(x, x));
		}
		for(int x = 17; x <= 21; x++){
			assertFalse(ds.takeCard(x, x));
		}
		
		
	} // end testTakeCard
	
	
} // end TestDealerStrategy
