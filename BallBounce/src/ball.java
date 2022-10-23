import java.awt.Color;
import java.awt.Rectangle;

public class ball {

	int xCoordBall = 900;
	int yCoordBall = 480;

	int randomStartleft_right = 0;
	boolean down = true;
	boolean right = false;
	int speedBall;
	double speedBallY = 0;
	boolean initialVelocity = true;

	public ball() {
		xCoordBall = 900;
		yCoordBall = 380;
		speedBall = 5;
	}

	public void starting() {
		randomStartleft_right = (int) (Math.random() * 2) + 1;

		// left or right
		if (randomStartleft_right == 1) {
			right = true;
		}
		if (randomStartleft_right == 2) {
			right = false;
		}
	}

	public void move() {

		if (down) {
			yCoordBall += speedBallY;
			if (speedBallY < 9) {
				speedBallY += 0.1;
			}
		}
//____________________________________________
		else {
			yCoordBall += speedBallY;

			if (initialVelocity && yCoordBall >= 950) {
				speedBallY = -9;
				initialVelocity = false;
			}
			speedBallY += 0.1;

			if (speedBallY >= 9) {
				initialVelocity = true;
			}
		}
//_____________________________________________
		if (yCoordBall >= 950) {
			down = false;
		}
//____________________________________________		

		if (right == true) {
			xCoordBall += speedBall;
		} else {
			xCoordBall -= speedBall;
		}
	}

	public Rectangle bounds() {

		return (new Rectangle(xCoordBall, yCoordBall, 20, 20));

	}

}
