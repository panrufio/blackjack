package ptp.coding.blackjack.game;

import java.util.Scanner;

/**
 * PlayBlackJack is the driver class for playing BlackJack
 * This is a simple version of the game as per the instructions for creating
 * the coding exercise that this is.
 * 
 * This class is structured as a state machine.  BlackJack breaks down into
 * well defined states.
 * 
 * States for BlackJack:
 *   NEWDEAL - hands reset, shuffle the shoe if needed, do initial deal
 *       - state becomes QUICKGAME if there is a 21
 *       - state becomes SHOWANDPROMPT if no 21
 *   QUICKGAME - for showing the results because there was a 21
 *       - state becomes NEWDEAL
 *   SHOWANDPROMPT - player is shown the state of the hands and is
 *                   prompted for stand, hit, quit
 *       - state becomes HIT if player chooses H
 *       - state becomes QUIT if player chooses Q
 *       - state becomes PLAYERSTAND if player chooses S
 *   HIT - player gets a card
 *       - state becomes PLAYERBUSTED if player's hand is over 21
 *       - state becomes SHOWANDPROMPT if player's hand is 21 or less
 *   PLAYERSTAND - dealer evaluates if it needs a card
 *       - state becomes DEALERBUSTED if dealer takes card and busts
 *       - state becomes PLAYERSTAND if dealer takes card and does not bust
 *       - state becomes EVALUATE if dealer does not need card
 *   PLAYERBUSTED - reports situation of hands
 *       - state becomes NEWDEAL
 *   DEALERBUSTD - reports situation of hands
 *       - state becomes NEWDEAL
 *   EVALUATE - is for comparing hands of the players
 *       - state becomes NEWDEAL
 *   QUIT - reports the progress of win, tie, loss and exits
 * 
 * @author andrew
 *
 */
public enum PlayBlackJack {

	NEWDEAL,
	QUICKGAME,
	SHOWANDPROMPT,
	HIT,
	PLAYERSTAND,
	PLAYERBUSTED,
	DEALERBUSTED,
	EVALUATE,
	QUIT;

	public static void main(String[] args){
		
		// set up the players and cards
		Dealer dealer = new Dealer();
		Player player = new Player();
		DealersShoe ds = new DealersShoe();
		ds.setShuffleAt(0.0);

		// flag to tell us if we are playing
		boolean playing = true;
		
		// input monitor
		Scanner uInput = new Scanner(System.in);
		String input;

		// set the initial state of the game
		PlayBlackJack state = NEWDEAL;

		// start
		while(playing){

			// evaluate the state of things
			switch (state) {

			  	case NEWDEAL:
			  		
			  		// reset hands
					player.P_Hand.resetHand();
					dealer.D_Hand.resetHand();

					// shuffle if needed
					if(ds.shouldShuffle()){
						ds.shuffle();
					}
					
					// deal
					player.getCard(ds.next());
					dealer.getCard(ds.next());
					player.getCard(ds.next());
					dealer.getCard(ds.next());

					// see if we have a quick game
					if(player.P_Hand.getHandValue() == player.P_Hand.PH_BEST &&
						dealer.D_Hand.getHandValue() == dealer.D_Hand.PH_BEST){
						player.tie();
						
						state = QUICKGAME;
					} else if(player.P_Hand.getHandValue() == player.P_Hand.PH_BEST){
						player.won();
						
						state = QUICKGAME;
					} else if(dealer.D_Hand.getHandValue() == dealer.D_Hand.PH_BEST){
						player.lost();
						
						state = QUICKGAME;
					} else {
						state = SHOWANDPROMPT;
					}
					
					
					break;
					
					
				case QUICKGAME:

					// show the progress
					System.out.println("Quick game with dealer having:\t" + dealer.D_Hand.getHandValue() + "\t\tcards: " + dealer.D_Hand.toString());
					System.out.println("Quick game with you having:   \t" + player.P_Hand.getHandValue() + "\t\tcards: " + player.P_Hand.toString());
					System.out.println("Your record: " + player.toString());
					System.out.println("===");

					state = NEWDEAL;
					break;
				
				
				case SHOWANDPROMPT:

					// show the state of the hands and prompt for action
					System.out.println("Dealer showing:\t" + dealer.D_Hand.showing() + "\t\tcards: " + dealer.showCurrentHand());
					System.out.print("You have:\t" + player.P_Hand.showing());
					if(player.P_Hand.isSoft()){
						System.out.print(" (soft) ");
					}
					System.out.println("\t\tcards: " + player.P_Hand.toString());
					System.out.print("Your choice (S - stand, H - hit, Q - quit): ");

					// get the user input and change state accordingly
					input = uInput.next();
					if(input.equals("Q")){
						state = QUIT;
					} else if(input.equals("H")){
						state = HIT;
					} else if(input.equals("S")){
						state = PLAYERSTAND;						
					}
					
					break;

					
				case HIT:
					
					// player takes a card
					player.getCard(ds.next());

					// evaluate
					if(player.P_Hand.getHandValue() > player.P_Hand.PH_BEST){
						// busted
						state = PLAYERBUSTED;
					} else {
						state = SHOWANDPROMPT;
					}

					break;
					
					
				case PLAYERSTAND:
					
					// show what the dealer has and see if he takes a card
					System.out.println("Dealer has:\t" + dealer.D_Hand.getHandValue() + "\t\tcards: " + dealer.D_Hand.toString());
					if(dealer.takeCard(0)){

						// take
						dealer.getCard(ds.next());
						
						// evaluate
						if(dealer.D_Hand.getHandValue() > dealer.D_Hand.PH_BEST){
							state = DEALERBUSTED;
						} else {
							state = PLAYERSTAND;
						}
						
					} else {
						
						// both players are done taking cards
						state = EVALUATE;

					}
					
					break;
					
					
				case PLAYERBUSTED:
					
					// show the state of things and players progress
					System.out.println("You busted:\t" + player.P_Hand.getHandValue() + "\t\tcards: " + player.P_Hand.toString());
					player.lost();
					System.out.println("Your record: " + player.toString());
					
					System.out.println("===");
					
					// hand over
					state = NEWDEAL;

					break;
					
					
				case DEALERBUSTED:
					
					// show the state of things and players progress
					System.out.println("Dealer busted:\t" + dealer.D_Hand.getHandValue() + "\t\tcards: " + dealer.D_Hand.toString());
					System.out.println("You won with: \t" + player.P_Hand.getHandValue() + "\t\tcards: " + player.P_Hand.toString());
					player.won();
					System.out.println("Your record: " + player.toString());

					System.out.println("===");
					
					// hand over
					state = NEWDEAL;

					break;
					
				case EVALUATE:
					
					// show the hands and see who won / tie / loss
					System.out.println("Dealer stands with:\t" + dealer.D_Hand.getHandValue() + "\t\tcards: " + dealer.D_Hand.toString());
					System.out.println("You stand with:    \t" + player.P_Hand.getHandValue() + "\t\tcards: " + player.P_Hand.toString());

					if(player.P_Hand.getHandValue() == dealer.D_Hand.getHandValue()){
						System.out.println("Tie at " + player.P_Hand.getHandValue());
						player.tie();
					} else if(player.P_Hand.getHandValue() > dealer.D_Hand.getHandValue()){
						System.out.println("You win!");
						player.won();
						
					} else {
						System.out.println("You lost.");
						player.lost();
					}

					// show progress
					System.out.println("Your record: " + player.toString());

					System.out.println("===");

					// hand over
					state = NEWDEAL;
					
					break;
					
					
				case QUIT:
					
					// show progress
					System.out.println("Your record: " + player.toString());
										
					playing = false;

					break;
					
				default:
					break;
			
			
			} // end switch
			
		} // end while
		
	} // end main
	
	
	
} // end PlayBlackJack
