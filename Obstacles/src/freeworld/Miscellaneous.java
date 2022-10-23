package freeworld;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.engine;

public class Miscellaneous extends engine{

	int width;
	int height;
	
	boolean fallingFactorReset;
	int spacingX = 0;
	int spacingY = 700;
	boolean onground;
	double jumpStrength;
	boolean disableleft;
	boolean disableright;
	int randomColor = randomInt(1, 2);
	boolean blockBreak;
	int changeHeight = randomInt(1, 3);
	
	BufferedImage sidebackground = addImageG2D("BackgroundLeft.png");
	public Miscellaneous() {
		width = 2000;
		height = 2000;
	}
	
	public void drawBackground(Graphics2D g2d, Graphics g) {
		addImage(g, "Sky.png", 0, 0);
		//g2d.drawImage(sidebackground, (int)x, (int)y, 800, 1200, null);
	}
	
	public Rectangle bounds() {
		return (new Rectangle((int)x, (int)y, 800, 1200));
	}
	
}
