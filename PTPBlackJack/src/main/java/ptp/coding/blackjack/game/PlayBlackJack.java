package ptp.coding.blackjack.game;

import java.util.Scanner;

public enum PlayBlackJack {

	NEWDEAL,
	EVALUATEINITIAL,
	QUICKGAME,
	SHOWANDPROMPT,
	HIT,
	PLAYERSTAND,
	PLAYERBUSTED,
	DEALERBUSTED,
	EVALUATE,
	QUIT;

	public static void main(String[] args){
		
		
		Dealer dealer = new Dealer();
		Player player = new Player();
		DealersShoe ds = new DealersShoe();
		ds.setShuffleAt(0.0);
		boolean playing = true;
		Scanner uInput = new Scanner(System.in);
		String input;

		PlayBlackJack state = NEWDEAL;
		while(playing){
			switch (state) {
				case NEWDEAL:
					player.P_Hand.resetHand();
					dealer.D_Hand.resetHand();
					if(ds.shouldShuffle()){
						ds.shuffle();
					}
					player.getCard(ds.next());
					dealer.getCard(ds.next());
					player.getCard(ds.next());
					dealer.getCard(ds.next());
					
					state = EVALUATEINITIAL;

					break;

				case EVALUATEINITIAL:
					
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
					System.out.println("Quick game with dealer having:\t" + dealer.D_Hand.getHandValue() + "\t\tcards: " + dealer.D_Hand.toString());
					System.out.println("Quick game with you having:   \t" + player.P_Hand.getHandValue() + "\t\tcards: " + player.P_Hand.toString());
					System.out.println("Your record: wins = " + player.wins +
							   " ties = " + player.ties +
							   " losses = " + player.losses);
					System.out.println("===");

					state = NEWDEAL;
					break;
				
				
				case SHOWANDPROMPT:
					System.out.println("Dealer showing:\t" + dealer.D_Hand.showing() + "\t\tcards: " + dealer.showCurrentHand());
					System.out.print("You have:\t" + player.P_Hand.showing());
					if(player.P_Hand.isSoft()){
						System.out.print(" (soft) ");
					}
					System.out.println("\t\tcards: " + player.P_Hand.toString());
					System.out.print("Your choice (S - stand, H - hit, Q - quit): ");
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
					player.getCard(ds.next());

					if(player.P_Hand.getHandValue() > player.P_Hand.PH_BEST){
						// busted
						state = PLAYERBUSTED;
					} else {
						state = SHOWANDPROMPT;
					}
					break;
					
					
				case PLAYERSTAND:
					System.out.println("Dealer has:\t" + dealer.D_Hand.getHandValue() + "\t\tcards: " + dealer.D_Hand.toString());
					if(dealer.takeCard(0)){
						dealer.getCard(ds.next());
						//System.out.println("Dealer has:\t" + dealer.D_Hand.getHandValue() + "\t\tcards: " + dealer.D_Hand.toString());
						if(dealer.D_Hand.getHandValue() > dealer.D_Hand.PH_BEST){
							state = DEALERBUSTED;
						} else {
							state = PLAYERSTAND;
						}
						
					} else {
						state = EVALUATE;
					}
					break;
					
					
				case PLAYERBUSTED:
					System.out.println("You busted:\t" + player.P_Hand.getHandValue() + "\t\tcards: " + player.P_Hand.toString());
					player.lost();
					System.out.println("Your record: wins = " + player.wins +
							   " ties = " + player.ties +
							   " losses = " + player.losses);
					
					System.out.println("===");
					state = NEWDEAL;
					break;
					
					
				case DEALERBUSTED:
					System.out.println("Dealer busted:\t" + dealer.D_Hand.getHandValue() + "\t\tcards: " + dealer.D_Hand.toString());
					player.won();
					System.out.println("Your record: wins = " + player.wins +
							   " ties = " + player.ties +
							   " losses = " + player.losses);

					System.out.println("===");
					state = NEWDEAL;
					break;
					
				case EVALUATE:
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
					System.out.println("Your record: wins = " + player.wins +
							   " ties = " + player.ties +
							   " losses = " + player.losses);

					System.out.println("===");
					state = NEWDEAL;
					
					
					break;
					
					
				case QUIT:
					System.out.println("Your record: wins = " + player.wins +
									   " ties = " + player.ties +
									   " losses = " + player.losses);
										
					playing = false;
					break;
					
				default:
					break;
			
			
			} // end switch
			
			
			
			
			
		} // endwhile
		
		
		
		
		
		
	} // end main
	
	
	
} // end PlayBlackJack
