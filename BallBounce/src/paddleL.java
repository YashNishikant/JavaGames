
import java.awt.Rectangle;

public class paddleL {

	int xPaddleL = 60; 
	int speed = 0; 
	int yPaddleL = 300;
	
	public void paddleL() {
		
	xPaddleL = 60; 
	speed = 0; 
	yPaddleL = 300;	
		
	}
	
	public void move() {
		
		if (yPaddleL <= 20) {
			speed = 0;
			yPaddleL = 21;
		}

		if (yPaddleL >= 810) {
			speed = 0;
			yPaddleL = 809;
		}

		
		yPaddleL = yPaddleL + speed;
		
	}
	
	public Rectangle bounds() {
		
		return (new Rectangle(xPaddleL, yPaddleL, 30, 150));
		
	}
}
