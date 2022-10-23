package Structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class JumpPlatform extends engine {

	double width = 80;
	double height = 20;

	public JumpPlatform(double x, double y) {
		this.x = x;
		this.y = y;
	}
 
	public void draw(Graphics g) {
		addImage(g, "JumpPlatform.png", x, (int) y);
	}

	public Rectangle bounds() {

		return (new Rectangle((int) x, (int) y, (int) (width), (int) (height)));

	}

}
