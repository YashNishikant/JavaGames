package freeworld;

import java.awt.Color;
import java.awt.Graphics;

import engine.engine;

public class Miscellaneous extends engine{

	int width;
	int height;
	
	public Miscellaneous() {
		width = 2000;
		height = 2000;
	}
	
	public void drawBackground(Graphics g) {
		addImage(g, "Sky.png", 0, 0);
	}
	
}
