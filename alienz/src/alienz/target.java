package alienz;

import java.awt.Rectangle;

public class target {

	int xBlock = (int)(Math.random() * 1800) + 30;
	int yBlock = (int)(Math.random() * 900) + 1;
	
	public void target() {
		
	xBlock = (int)(Math.random() * 1800) + 20;	
	yBlock = (int)(Math.random() * 900) + 1;	
	}
	
	public Rectangle bounds() {
		
		return (new Rectangle(xBlock, yBlock, 20, 20));
		
	}
	
}
