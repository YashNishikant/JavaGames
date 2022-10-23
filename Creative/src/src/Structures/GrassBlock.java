package Structures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GrassBlock extends Platform{
	BufferedImage Grass = addImageG2D("Platform.png");
	BufferedImage Dirt = addImageG2D("PlatformDirt.png");

	public GrassBlock(double x, double y) {
		super(x, y);
	}
	public void draw(Graphics2D g2d, Graphics g) {
	
		if(!getChangeTexture())
		g2d.drawImage(Grass, (int)x, (int)y, (int)width, (int)height, null);
	else
		g2d.drawImage(Dirt, (int)x, (int)y, (int)width, (int)height, null);
	}
}
