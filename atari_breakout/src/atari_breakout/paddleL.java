package atari_breakout;

import java.awt.Rectangle;

public class paddleL {
	
	int paddleXL;
	
	public paddleL(){
		
	paddleXL = 825;
		
	}
	
	public void moveL() {
		
		if(paddleXL < 0) {
			
			paddleXL = 0;	

		}
		
		if(paddleXL > 1615) {
			
			paddleXL = 1615;	
		}
		
		
	}
	
	public Rectangle bounds() {
		
		return (new Rectangle(paddleXL, 970, 75, 20));
		
	}
	
}