package Structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class GrassBlock extends Platform {
	BufferedImage Grass = addImageG2D("Platform.png");
	BufferedImage Dirt = addImageG2D("PlatformDirt.png");

	public GrassBlock(double x, double y) {
		super(x, y);
	}

	public void draw(Graphics2D g2d, Graphics g) {

		if (!getChangeTexture()) {
			g2d.drawImage(Grass, (int) x, (int) y, (int) width, (int) height, null);
		} else {
			g2d.drawImage(Dirt, (int) x, (int) y, (int) width, (int) height, null);
		}
	
		g.setColor(Color.RED);
		if (getCTop()) {
			g.fillRect((int) x, (int) y - 5, (int) (width), (int) (10));
		}
		if (getCLeft()) {
			g.fillRect((int) x - 5, (int) y, (int) (10), (int) (height));
		}
		if (getCRight()) {
			g.fillRect((int) (x + width-5), (int) y, (int) (10), (int) (height));
		}
		g.fillRect((int) x, (int) (y + height), (int) (width), (int) (5));
		g.setColor(Color.BLACK);
	}
}
