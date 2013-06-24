package ptp.coding.blackjack.game;

public class PlayerStrategy extends Strategy {

	protected String[][] strategy;
	
	public PlayerStrategy(){
		strategy = new String[22][11];
		for(int y = 0; y < 11; y++){
			for(int x = 0; x < 11; x++){
				strategy[y][x] = "H";
			}
		}
		//for(){}
		
		
	} // end constructor
	
	public boolean takeCard(int currentCount, int opponentCount){
		
		
		
		
		
		return true;
	} // end takeCard
	
} // end PlayerStrategy
