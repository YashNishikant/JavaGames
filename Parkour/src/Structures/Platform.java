package Structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import engine.engine;

public class Platform extends engine {

	public double width;
	public double height;

	BufferedImage grass = addImageG2D("GrassTexture.png");
	
	public Platform(int x, int y) {
		width = randomInt(150, 450);
		height = randomInt(150, 400);

		this.setXPos(500 + x);
		this.setYPos(y);

	}

	public void Update() {
		gravityInverse(DOWNWARD_FORCE);
		AffectedForceVerticallyInverse(15);
		AffectedForceHorizontally(20);
	}
	
	public void draw(Graphics g, Graphics2D g2d) {
		g.setColor(Color.RED);
		g.fillRect((int) xPos(), (int) yPos(), (int) (width), (int) (height));

		g2d.drawImage(grass, (int) xPos(), (int) yPos(), (int) width, (int) height, null);
		
	}

	public BufferedImage addImageG2D(String imageStr) {
		BufferedImage img = LoadImage(imageStr);
		return img;	
	}
	
	BufferedImage LoadImage(String FileName) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(new File(FileName));
		} catch (IOException e) {
			System.out.println("ERROR: CANNOT FIND IMAGE");
		}
		return img;
	}
	
	public Rectangle bounds() {
		return (new Rectangle((int) xPos(), (int) yPos(), (int) (width), (int) (height)));
	}

}
