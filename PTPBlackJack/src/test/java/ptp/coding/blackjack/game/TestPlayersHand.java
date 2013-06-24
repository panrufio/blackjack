package ptp.coding.blackjack.game;

import junit.framework.TestCase;

public class TestPlayersHand extends TestCase {

	public void setUp(){} // end setUp
	
	public void tearDown(){} // end tearDown
	
	public void testIsDealer(){
		PlayersHand ph = new PlayersHand();
		assertFalse(ph.isDealer());
		assertTrue(ph.isPlayer());
		
		PlayersHand ph2 = new PlayersHand(true);
		assertTrue(ph2.isDealer());
		assertFalse(ph2.isPlayer());

		PlayersHand ph3 = new PlayersHand(new Card("2C"));
		assertFalse(ph3.isDealer());
		assertTrue(ph3.isPlayer());

		
		PlayersHand ph4 = new PlayersHand(true, new Card("3D"));
		assertTrue(ph4.isDealer());
		assertFalse(ph4.isPlayer());
		
	} // end DealerShowing
	

	public void testShowing(){
		PlayersHand ph = new PlayersHand(new Card("3S"));
		ph.addCard(new Card("4D"));
		assertEquals(7, ph.showing());
		
		PlayersHand ph2 = new PlayersHand(true, new Card("3S"));
		ph2.addCard(new Card("4D"));
		assertEquals(3, ph2.showing());
		
	} // end testShowing
	
	public void testSoftValue(){
		PlayersHand ph = new PlayersHand(new Card("AS"));
		ph.addCard(new Card("8C"));
		assertEquals(19, ph.showing());
		assertTrue(ph.isSoft());
		ph.resetHand();
		
		ph.addCard(new Card("10C"));
		ph.addCard(new Card("9C"));
		assertEquals(19, ph.showing());
		assertFalse(ph.isSoft());
		ph.resetHand();
		
		ph.addCard(new Card("6H"));
		ph.addCard(new Card("6D"));
		ph.addCard(new Card("AD"));
		ph.addCard(new Card("AD"));
		assertEquals(14, ph.showing());
		assertFalse(ph.isSoft());
		
	} // end testSoftValue
	
	
} // end TestPlayersHand
