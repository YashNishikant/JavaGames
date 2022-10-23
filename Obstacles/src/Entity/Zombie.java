package Entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.engine;

public class Zombie extends Player {

	private int width;
	private int height;

	private boolean onGround;
	
	BufferedImage zombie = addImageG2D("Zombie.png");

	public Zombie(double x1, double y1, int w1, int w2) {
		
		super(x1, y1, w1, w2);
		
		x = x1;
		y = y1;
		width = w1;
		height = w2;
	}

	public void draw(Graphics2D g2d, Graphics g) {
		g2d.drawImage(zombie, (int) x, (int) y, width, height, null);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setOnGround(boolean x) {
		onGround = x;
	}
	
	public boolean getOnGround() {
		return onGround;
	}
	
	public Rectangle bounds() {
		return (new Rectangle((int) x, (int) y, (int) (width), (int) (height)));
	}

}
