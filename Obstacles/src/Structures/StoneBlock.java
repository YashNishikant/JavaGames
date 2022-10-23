package Structures;

import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class StoneBlock extends Platform{

	private boolean upHill = false;
	BufferedImage Stone = addImageG2D("StoneBrick.png");
	BufferedImage Ore = addImageG2D("Ore.png");
	public StoneBlock(double x, double y) {
		super(x, y);
		setTag("destructible");
	}
	public void draw(Graphics2D g2d, Graphics g) {	
		if(!getOre()) {
			g2d.drawImage(Stone, (int)x, (int)y, (int)width, (int)height, null);
		}
		else {
			g2d.drawImage(Ore, (int)x, (int)y, (int)width, (int)height, null);
		}
	}
	
	public void setUpHill(boolean x) {
		upHill = x;
	}
	public boolean getUpHill() {
		return upHill;
	}
}
