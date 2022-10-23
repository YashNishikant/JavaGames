import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class trees {

	int speed;
	int xtree;
	int ytree;
	int separate;
	boolean treedissapear = false;
	
	public trees(int x, int y) {
		xtree = x + (int)(Math.random()*700)+ 1;
		ytree = y;
		speed = 0;
	}
	
	public void move() {
		xtree = xtree + speed;
		if(treedissapear) {
			ytree = 100000;
		}
	}
		
	public Rectangle bounds() {
	
		return (new Rectangle(xtree, ytree, 150, 400));
		
	}
}