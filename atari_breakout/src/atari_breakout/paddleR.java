package atari_breakout;

import java.awt.Rectangle;

public class paddleR {
	
	int paddleXR;
	
	public paddleR(){
		
	paddleXR = 1050;
		
	}
	
	public void moveR() {
			
		if(paddleXR > 1840) {
			
			paddleXR = 1840;	
		}
		
		if(paddleXR < 225) {
			
			paddleXR = 225;
			
		}		
		
	}
	
	public Rectangle bounds() {
		
		return (new Rectangle(paddleXR, 970, 75, 20));
		
	}
	
}