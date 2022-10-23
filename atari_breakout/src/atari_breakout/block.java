package atari_breakout;

import java.awt.Rectangle;

public class block {

	boolean hitBlock = false;
	int xBlock;
	int yBlock;
	int blockWidth;
	int blockHeight;
	
	public block(int x, int y, int width, int height) {
		xBlock = x;
		yBlock = y;
		blockWidth = width;
		blockHeight = height;
		hitBlock = false;
	}
	
	public void disappear()
	{
		xBlock = -100;
	}
	
	public void hitdetect() {
		
	if(hitBlock) {
		
		xBlock = -100;	
		
	}	
		
	}
	
	//borders for squares
	public Rectangle bounds() {

		return (new Rectangle(xBlock, yBlock, blockWidth, blockHeight));

	}

	public String print() {
		return "x:" + xBlock + " y:" + yBlock + " width:" + blockWidth + " height:" + blockHeight;
	}
	
}