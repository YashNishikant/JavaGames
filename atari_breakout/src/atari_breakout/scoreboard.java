package atari_breakout;

public class scoreboard {

	int score = 0;
	int boardY = -200;
	int boardSpeed = 1;
	
	boolean bringDown = false;
	
	public scoreboard(){
		
	score = 0;	
	boardY = 0;	
	boardSpeed = 1;
	boardY = -200;
	
	}
	
	public void scoreboardmove() {	

		if(bringDown) {
		boardY+=boardSpeed;

		if(boardY > 20) {
			
			boardSpeed = 0;
			
			}
		}
	}	
}