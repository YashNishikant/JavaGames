package Structures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StoneBlock extends Platform{

	BufferedImage Stone = addImageG2D("StoneBrick.png");
	
	public StoneBlock(double x, double y) {
		super(x, y);
	}
	public void draw(Graphics2D g2d, Graphics g) {	
		g2d.drawImage(Stone, (int)x, (int)y, (int)width, (int)height, null);
	}
}
