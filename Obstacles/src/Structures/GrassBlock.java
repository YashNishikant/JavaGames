package Structures;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class GrassBlock extends Platform {
	BufferedImage Grass = addImageG2D("Platform.png");
	BufferedImage Dirt = addImageG2D("PlatformDirt.png");

	BufferedImage Grass2 = addImageG2D("Platform2.png");
	BufferedImage Dirt2 = addImageG2D("PlatformDirt2.png");
	
	public GrassBlock(double x, double y) {
		super(x, y);
		setTag("grass");
	}

	public void draw(Graphics2D g2d, Graphics g) {
		
		if (MClol) {
			if (!getChangeTexture()) {
				g2d.drawImage(Grass2, (int) x, (int) y, (int) width, (int) height, null);
			}

			else {
				g2d.drawImage(Dirt2, (int) x, (int) y, (int) width, (int) height, null);
			}
		}
		if (!MClol) {
			if (!getChangeTexture()) {
				g2d.drawImage(Grass, (int) x, (int) y, (int) width, (int) height, null);
			}

			else {
				g2d.drawImage(Dirt, (int) x, (int) y, (int) width, (int) height, null);
			}
		}
		
		//showColliders(g);
	}
}
