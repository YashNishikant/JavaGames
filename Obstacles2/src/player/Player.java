package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Player extends engine{
	
	public double width;
	public double height;
	public double speedY;
	public double speed;
	public int yCoord = 400;
	
	public Player(double x1, double y1) {
		x = 800;
		y = yCoord;
		width = x1;
		height = y1;
		speed = 8;
	}
	
	public void draw(Graphics g) {

		addText(g,"FrostKnight1234", (int)x - 25, (int)y-5, 15);
		
	}
	
	
	public Rectangle bounds() {
	
		return(new Rectangle((int)x, (int)y, (int)(width), (int)(height)));
		
	}
}
