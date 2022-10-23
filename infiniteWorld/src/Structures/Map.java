package Structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Map extends engine {
	String assetsPath;

	int offsetx;
	int offsety;
	
	public Map() {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		x = -1250;
		y = -1200;
		offsetx = 900 + 1250;
		offsety = 450 + 1200-50;
	}

	public void drawBackground(Graphics g) {
		addImage(g, "\\Backgrounds\\background.png", x, (int) y);
	
		//g.setColor(Color.RED);
//		g.fillRect((int) (x + offsetx + 170), (int) (y + offsety + 550), 650, 50);
//		g.fillRect((int) (x + offsetx), (int) (y + offsety), 130, 50);
//		g.fillRect((int) (x + offsetx + 870), (int) (y + offsety+ 200), 650, 50);
//		g.fillRect((int) (x + offsetx + 1520), (int) (y + offsety - 20), 950, 50);
//		g.fillRect((int) (x + offsetx + 2500), (int) (y + offsety - 240), 810, 50);
//		g.fillRect((int) (x + offsetx + 2500), (int) (y + offsety - 240), 50, 800);
		
	}

	public Rectangle spawnBorderVertTop() {
		return new Rectangle((int) (x + offsetx), (int) (y + offsety), 130, 50);
	}
	public Rectangle mapHorizontalTop1() {
		return new Rectangle((int) (x + offsetx + 170), (int) (y + offsety + 550), 650, 50);
	}
	public Rectangle mapHorizontalTop2() {
		return new Rectangle((int) (x + offsetx + 870), (int) (y + offsety+ 200), 650, 50);
	}
	public Rectangle mapHorizontalTop3() {
		return new Rectangle((int) (x + offsetx + 1520), (int) (y + offsety - 20), 950, 50);
	}
	public Rectangle mapHorizontalTop4() {
		return new Rectangle((int) (x + offsetx + 2500), (int) (y + offsety - 240), 810, 50);
	}
	public Rectangle mapVert1() {
		return new Rectangle((int) (x + offsetx + 2500), (int) (y + offsety - 240), 50, 800);
	}
}
