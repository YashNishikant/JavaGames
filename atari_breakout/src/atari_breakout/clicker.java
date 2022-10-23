package atari_breakout;

import java.awt.Rectangle;

public class clicker {

	int xclick = 0;
	int yclick = 0;
	
	public clicker() {
		
	xclick = 0;
	yclick = 0;
		
	}
	
	public Rectangle bounds() {
		
		return (new Rectangle(xclick+10, yclick+20, 30, 30));
		
	}
	
}
