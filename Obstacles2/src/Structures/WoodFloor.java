package Structures;

import java.awt.Graphics;

import engine.engine;

public class WoodFloor extends engine{

	int width;
	int height;
	
	public WoodFloor() {
		width = 100;
		height = 10;
	}
	
	public void draw(Graphics g) {
		g.fillRect((int)x, (int)y, width, height);
	}
	
}
