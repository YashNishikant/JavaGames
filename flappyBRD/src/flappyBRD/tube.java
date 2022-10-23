package flappyBRD;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class tube {

	int tubeX;
	int tubeY;
	int separate;
	int speed;
	int boundaryY;
	int offsetX;
	boolean collide;
	public tube(int x, int y, int z) {
		
	tubeX = x;
	tubeY = y;
	speed = -5;	
	separate = z;
	offsetX = 25;
	collide = false;
	boundaryY = 0;
	
	}
	
	public void shuffle() {
		tubeY = (int)((Math.random() * 500) + -1500);
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.GRAY);
		g.fillRect(tubeX + separate,tubeY,50,1500);
		g.setColor(Color.GRAY);
		g.fillRect(tubeX + separate,tubeY + 1700,50,1500);
	}
	
	public void move() {
		tubeX = tubeX + speed;
	}
	
	public Rectangle boundsTOP() {	
		return (new Rectangle(tubeX + separate,tubeY,50,1500));	
	}
	public Rectangle boundsBOTTOM() {
		return (new Rectangle(tubeX + separate,tubeY + 1700,50,1500));	
	}
	public Rectangle GAP() {
		return (new Rectangle(tubeX + separate + offsetX, boundaryY,1,20000));	
	}
	
}
