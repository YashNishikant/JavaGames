package Environment;

import java.awt.Graphics;

import engine.Textures;

public class sun extends Textures{

	public double xsun;
	public double ysun;
	public float light = 0;
	
	public sun() {
		xsun = 0;
		ysun = 200;
	}
	
	public void sunPath() {
		xsun += 0.1;
		
		if(xsun > 3000) {
			xsun = -200;
		}
		
	}
	
	public void draw(Graphics g) {
		addImage(g, "Backgrounds//Sun.png", xsun, (int) ysun);
	}
	
}
