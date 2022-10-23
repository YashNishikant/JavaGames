package Item;

import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Item extends engine{

	int width;
	int height;
	
	public Item() {
		width = 50;
		height = 50;
	}
	
	public void draw(Graphics g) {
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public Rectangle bounds() {
		return (new Rectangle((int)x, (int)y, width, height));
	}
	
}
