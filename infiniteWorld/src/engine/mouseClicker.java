package engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class mouseClicker {

	public int x;
	public int y;
	
	public mouseClicker(){
		
	}

	public Rectangle bounds() {
		return(new Rectangle(x, y, 5, 5));
	}
	
}