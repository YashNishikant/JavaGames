package Item;

import java.awt.Graphics;

public class Gem extends Item {

	private int width;
	private int height;

	private boolean red;

	public Gem(double x1, double y1) {
		super();
		x = x1;
		y = y1;
		width = 22;
		height = 30;
	}

	public void draw(Graphics g) {
		if (!red) {
			addImage(g, "Gem.png", x, (int) y);
		} else {
			addImage(g, "Gem2.png", x, (int) y);
		}
	}

	public int getWidth() {
		return width;
	}

	public void setGem(boolean x) {
		red = x;
	}

	public int getHeight() {
		return height;
	}

}
