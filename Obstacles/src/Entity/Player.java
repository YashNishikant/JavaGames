package Entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import engine.engine;

public class Player extends engine{
	
	BufferedImage knight = addImageG2D("Player.png");
	BufferedImage knight2 = addImageG2D("Player2.png");
	BufferedImage sword = addImageG2D("Sword.png");
	BufferedImage sword2 = addImageG2D("Sword2.png");
	private boolean flip;
	int rotate;
	boolean swing;
	public double width;
	public double height;
	public double speedY;
	public double speed;
	public int yCoord = 300;
	int playerx;
	int playery;
	
	public Player(double xpos, double ypos, double x1, double y1) {
		x = xpos;
		y = ypos;
		width = x1;
		height = y1;
		speed = 10;
	
		if(MClol) {
			width = 30;
			height = 120;
		}
		
	}
	
	public void draw(Graphics2D g2d, Graphics g) {
		AffineTransform at = setTransformations(playerx, playery);
		PlayerAnimations(g2d, knight, sword, at);
	}
	
	public void setFlip(boolean x) {
		flip = x;
	}
	public void setSwing(boolean x) {
		swing = x;
	}
	
	public void PlayerAnimations(Graphics2D g2d, BufferedImage a, BufferedImage b, AffineTransform at) {
		if (flip) {

			FlipImage(g2d, a, (int) x + 55, (int) y, (int) width, (int) height);
			if (!swing) {
				g2d.drawImage(b, (int) (x + width) - 15, (int) (y) - 15, 25, 75, null);
			}
			playerx = (int) (x) + 40;
			playery = (int) (y);

		} else {

			g2d.drawImage(a, (int) (x - 5), (int) (y), (int) width, (int) height, null);
			if (!swing) {
				g2d.drawImage(b, (int) (x) - 15, (int) (y) - 15, 25, 75, null);
			}
			playerx = (int) (x) - 10;
			playery = (int) (y);
		}
		if (swing && !flip) {

			at.rotate(Math.toRadians(-rotate), 25 / 1.5, 75 / 1.5);
			g2d.drawImage(b, at, null);
			rotate += 10;

			if (rotate >= 100) {
				rotate = 0;
				swing = false;
			}
		}

		if (swing && flip) {

			at.rotate(Math.toRadians(-rotate), 25 / 1.5, 75 / 1.5);
			g2d.drawImage(b, at, null);
			rotate -= 10;

			if (rotate <= -100) {
				rotate = 0;
				swing = false;
			}
		}
	}
	
	public Rectangle bounds() {
	
		return(new Rectangle((int)x, (int)y, (int)(width), (int)(height)));
		
	}
}
