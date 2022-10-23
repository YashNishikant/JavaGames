package Item;

import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.Rectangle;

import engine.engine;

	public class Item extends engine{

	int width;
	int height;
	
	public Item() {
		width = 50;
		height = 50;
	}
	
	public void draw(Graphics2D g2d, Graphics g) {
	}
	
	public Rectangle bounds() {
		return (new Rectangle((int)x, (int)y, width, height));
	}
	
}
