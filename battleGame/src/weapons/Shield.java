package weapons;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Textures;

public class Shield extends Textures{

	public double x;
	public int y;
	public int width;
	public int height;

	public boolean activateShield;

	public Shield(double x1, int y1) {

		x = x1;
		y = y1;

		width = 10;
		height = 80;
	}

	public void draw(Graphics g) {
		addImage(g, "//Armor//shield.png", x, y);
	}
	
	public Rectangle bounds() {
		if (activateShield) {
			return (new Rectangle((int)x, y, width, height));
		} else {
			return (new Rectangle(0, 0, 0, 0));
		}
	}

}
