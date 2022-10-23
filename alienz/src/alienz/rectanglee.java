package alienz;

import java.awt.Rectangle;

public class rectanglee {

	int xShip = 60;
	int yShip = 300;
	int speedX;
	int speedY;

	public void rectanglee() {
		
		xShip = 60;
		yShip = 300;
		speedX = 0;
		speedY = 0;
		
	}
	
	public void move() {
		if (xShip < 0) {
			speedX = 0;
			xShip = 0;
		}

		if (yShip > 970) {
			speedX = 0;
			yShip = 970;
		}
	
		yShip = yShip + speedY;
		xShip = xShip + speedX;
		
		
	}

	public Rectangle bounds() {
		
		return (new Rectangle(xShip, yShip, 20, 20));
		
	}
	
}
