package atari_breakout;

import java.awt.Rectangle;

public class playbutton {

	int x = 880;
	int y = 350;
	
	public playbutton(){
		
	x = 880;
	y = 350;
		
	}
	
	public Rectangle bounds() {

		return (new Rectangle(x, y, 80, 80));

	}
	
}
