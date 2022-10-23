package atari_breakout;

import java.awt.Rectangle;

public class paddleClass {

	int paddleX;
	int speedPaddle;
	
	public paddleClass(){
		
	paddleX = 900;
	speedPaddle = 0;	
		
	}
	
	public void move() {
		
		if(paddleX < 75) {
			
			paddleX = 75;	
			
		}
		
		if(paddleX > 1690) {
			
			paddleX = 1690;	
			
		}
		paddleX = paddleX + speedPaddle;	
		
		
	}
	
	public Rectangle bounds() {
		
		return (new Rectangle(paddleX, 970, 150, 20));
		
	}
	
}
