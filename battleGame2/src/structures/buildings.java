package structures;

import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Textures;

public class buildings extends Textures{
	
	public double bX;
	public double bY = 250;
	int bW = 200;
	int bH = 700;
	public boolean enter;
	public double speed = 0;
	
	public buildings(int x) {	
		bX = x;
	}

	public void draw(Graphics g) {
		addImage(g, "//Backgrounds//buildingIMG.png", (int)bX, (int)bY);
	}
	
	public void move() {
		bX = bX + speed;
	}

	public Rectangle bounds() {
		return (new Rectangle((int)bX, (int)bY, bW, bH));
	}
	
}