package Environment;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import engine.Textures;

public class land extends Textures{

	public double X;
	public double XGround;
	public int Y;
	public double backgroundspeed;

	String assetsPath;
	
	public land(double x1) {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		X = x1;
		Y = 400;
		backgroundspeed = 0;
	}

	public void draw(Graphics g) {
		addImage(g, "Backgrounds//Background.png", (int) X, Y);
		X += backgroundspeed;
	}
}
