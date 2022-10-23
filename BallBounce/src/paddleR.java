
import java.awt.Rectangle;

public class paddleR {

	int xPaddleR = 1800; 
	int speed2 = 0;
	int yPaddleR = 300;
	
	public void paddleR() {
		
	xPaddleR = 1800; 
	speed2 = 0;
	yPaddleR = 300;	
		
	}
	
	public void move() {
		if (yPaddleR <= 20) {
			speed2 = 0;
			yPaddleR = 21;
		}

		if (yPaddleR >= 810) {
			speed2 = 0;
			yPaddleR = 809;
		}

		if (xPaddleR <= 1700) {
			speed2 = 0;
			yPaddleR = 1699;
		}
		
		yPaddleR = yPaddleR + speed2;	
		}
	
	public Rectangle bounds() {
		
		return (new Rectangle(xPaddleR, yPaddleR, 30, 150));
		
	}
	
}
