package atari_breakout;

import java.awt.Rectangle;

public class ballBreakout {

	boolean right = true;
	boolean down = true;
	boolean rightangled = false;
	boolean leftangled = false;

	int angledSpeed = 1;
	
	boolean lose = false;
	
	//top test:
	//x 210
	//y 10
	//
	//bottom test:
	//x 210
	//y 700
	//
	//right test:
	//x 400
	//y 210
	//
	//left test:
	//x 10
	//y 210
	
	int ballX = (int)(Math.random()*1000) + 10;
	int ballY = 610;

	int ballSpeedX = 2;
	int ballSpeedY = 2;

	
	public void move() {
	
		// top
		if (ballY <= 0) {
			down = true;
		}
		// leftside
		if (ballX <= 0) {
			right = true;
		}
		// rightside
		if (ballX >= 1880) {
			right = false;
		}
		// bottom
		if (ballY >= 1000) {
			ballSpeedY = 0;
			ballSpeedX = 0;
		
			lose = true;
		
		}

		if (down == true) {			
			ballY += ballSpeedY;
		} 
		if(down == false){
			ballY -= ballSpeedY;
		}

		if (right == true) {
			ballX += ballSpeedX;
		} 
		if(right == false) {
			ballX -= ballSpeedX;

		}

		//angled
		if (rightangled == true) {	
			ballSpeedY = angledSpeed;
			
			ballX += ballSpeedX;
			ballY -= ballSpeedY;
		
			rightangled = false;
			
		} 
		
		//angled
		if (leftangled == true) {			
			ballSpeedY = angledSpeed;
			
			ballX -= ballSpeedX;
			ballY -= ballSpeedY;
			
			leftangled = false;
		} 
		
	}

	public Rectangle bounds() {

		return (new Rectangle(ballX, ballY, 30, 30));

	}

}
