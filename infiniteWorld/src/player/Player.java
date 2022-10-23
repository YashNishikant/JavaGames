package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Player extends engine{

	public double x;
	
	public double width;
	public double height;
	public double speedx = 0;
	public double speedX = 8;
	public double speedy = 0;
	public double speedY = 8;
	
	public boolean flip;
	
	public Player(double x1, double y1) {
		x = 900;
		y = 500;
		width = x1;
		height = y1;
	}
	
	public void draw(Graphics g) {
		if(!flip)
		addImage(g, "\\Backgrounds\\Player.png", x + 25, (int)y + 10);
		else {
			addImage(g, "\\Backgrounds\\PlayerFlip.png", x + 25, (int)y + 10);	
		}
		
	}
	
	
	public Rectangle bounds() {
	
		return(new Rectangle((int)x, (int)y, (int)(width), (int)(height)));
		
	}
}
