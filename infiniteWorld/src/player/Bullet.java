package player;

import java.awt.Color;
import java.awt.Graphics;
import engine.engine;

public class Bullet extends engine{
	
	public double speedx;
	public double speedy;
	
	public double additionalSpeedX = 0;
	public double additionalSpeedY = 0;
	public boolean click = false;
	public boolean fire;
	
	public Bullet() {
		x = 500;
		y = 500;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval((int)x, (int)y, 20, 20);
	}
	
	public void move() {
		x += additionalSpeedX;
		y += additionalSpeedY;
	}

}