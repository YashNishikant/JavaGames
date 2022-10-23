package Structures;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Block extends engine{

	public double width;
	public double height;
	
	public Block() {
		x = 550;
		y = 800;
		width = 100;
		height = 100;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillOval((int)x, (int)y, (int)(width), (int)(height));
		
	}
	
	public Rectangle bounds() {
		return(new Rectangle((int)x, (int)y, (int)(width), (int)(height)));
	}

	
}
