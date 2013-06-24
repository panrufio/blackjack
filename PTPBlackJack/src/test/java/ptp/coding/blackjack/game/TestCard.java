package ptp.coding.blackjack.game;

import junit.framework.TestCase;

public class TestCard extends TestCase {

	public void setUp(){} // end setUp
	
	public void tearDown(){} // end tearDown

	public void testCardValue(){
		Card c = new Card("AS");
		assertEquals("AS", c.getValue());
	} // end testCardValue
	public void testCardBadFaceValue(){
		Card c = new Card("10M");
		assertEquals(0, c.getFaceValue());
	} // end testCardBadValue
	
	public void testCardFaceValue(){
		Card c = new Card("KC");
		assertEquals(10, c.getFaceValue());
		Card c2 = new Card("AD");
		assertEquals(1, c2.getFaceValue());
		Card c3 = new Card("5H");
		assertEquals(5, c3.getFaceValue());
	}
	
	public void testIsAce(){
		Card c = new Card("AH");
		assertTrue(c.isAce());
		Card c2 = new Card("9S");
		assertFalse(c2.isAce());
	}
	
} // end testCard
