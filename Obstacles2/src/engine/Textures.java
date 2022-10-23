package engine;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Textures extends Physics{

	String assetsPath;

	public Textures() {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
	}

	public void addText(Graphics g, String text, int x ,int y, int size) {
		g.setFont(new Font("default", Font.BOLD, size));
		g.drawString(text, x, y);
	}
	
	public void addImage(Graphics g, String s, double x, int y) {
		ImageIcon i = new ImageIcon(assetsPath + s);
		i.paintIcon(this, g, (int) x, (int) y);

	}

	public AffineTransform setTransformations(int x, int y) {
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		return at;
	}
	
	public BufferedImage addImageG2D(String imageStr) {
		BufferedImage img = LoadImage(imageStr);
		return img;
		
	}
	
	public void FlipImage(Graphics2D g2d, BufferedImage a, int x, int y, int w, int h) {
		g2d.drawImage(a, x, y, -w, h, null);
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
	
	public int animation(Graphics g, int animationDelay, int animationNumber, boolean animateDirection, String PlayerDirection, double x, int y, int animationLimit, int animationSpeed) {
		
		animationDelay++;
		if (animateDirection) {
			addImage(g, ("//Player//" + PlayerDirection + animationNumber + ".png"), (int) x, (int) y);
			
			if (animationDelay % animationSpeed == 0) {
				animationNumber++;
				
			}
			if (animationNumber == animationLimit) {	
				animationNumber = 1;
			}
		}		
		return animationNumber;
	}
}

