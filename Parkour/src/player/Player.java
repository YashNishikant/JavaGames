package player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.engine;

public class Player extends engine{
	
	double width;
	double height;
	double speedY;
	double speed = 10;
	
	public int yCoord = 400;
	
	public Player(double x1, double y1) {
		setXPos(700);
		setYPos(yCoord);
		width = x1;
		height = y1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect((int)xPos(), (int)yPos(), (int)(width), (int)(height-20));
		g.fillOval((int)xPos()-1, (int)(yPos()-height/4), (int)(width), (int)(height/2));
		g.fillOval((int)xPos()-1, (int)(yPos()+height-50), (int)(width), (int)(height/2));
	}
	
	public double objWidth() {
		return width;
	}
	public double objHeight() {
		return height;
	}
	
	public Rectangle bounds() {
		return(new Rectangle((int)xPos(), (int)yPos(), (int)(width), (int)(height)));
	}
}
