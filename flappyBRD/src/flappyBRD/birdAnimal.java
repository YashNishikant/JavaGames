package flappyBRD;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class birdAnimal {

	int birdY;
	int terminalVel;
	double speed;
	boolean jump;
	boolean onetimeJump;
	int birdX;
	int x;
	
	
	public birdAnimal() {
		terminalVel = 6;
		birdY = 300;
		speed = 0;
		jump = false;
		onetimeJump = true;
		birdX = 400;
		x = 3;
	}

	public void draw(Graphics g) {

		//g.setColor(Color.RED);
		//g.fillRect(400, birdY, 40, 40);

		Color darkyellow = new Color(255,205,0);
        Color red = new Color(242,56,0);
        Color brightyellow = new Color(255,255,0);

        g.setColor(Color.YELLOW);
        g.fillRect(birdX + 6*x, birdY + x,3*x,x);
        g.fillRect(birdX + 4*x, birdY + 2*x,4*x,x);
        g.fillRect(birdX + 5*x, birdY + 3*x,3*x,x);
        g.fillRect(birdX + 6*x, birdY + 4*x,2*x,x);
        g.fillRect(birdX + 7*x, birdY + 5*x,2*x,x);
        g.fillRect(birdX + 7*x, birdY + 6*x,3*x,x);
        g.fillRect(birdX + 6*x, birdY + 7*x,3*x,x);

        g.setColor(darkyellow);
        g.fillRect(birdX + 5*x, birdY + 8*x,3*x,x);
        g.fillRect(birdX + 3*x, birdY + 9*x,6*x,x);
        g.fillRect(birdX + 5*x, birdY + 10*x,5*x,x);

        g.setColor(Color.BLACK);
        g.fillRect(birdX + 6*x, birdY + 0,6*x,x);
        g.fillRect(birdX + 4*x, birdY + x, 2*x,x);
        g.fillRect(birdX + 3*x, birdY + 2*x,x,x);
        g.fillRect(birdX + x, birdY + 3*x,4*x,x);
        g.fillRect(birdX + 0, birdY + 4*x,x,3*x);
        g.fillRect(birdX + 5*x, birdY + 4*x,x,x);
        g.fillRect(birdX + 6*x, birdY + 5*x,x,2*x);
        g.fillRect(birdX + 5*x, birdY + 7*x,x,x);
        g.fillRect(birdX + x, birdY + 7*x,x,x);
        g.fillRect(birdX + 2*x, birdY + 8*x,3*x,x);
        g.fillRect(birdX + 2*x, birdY + 9*x,x,x);
        g.fillRect(birdX + 3*x, birdY + 10*x,2*x,x);
        g.fillRect(birdX + 5*x, birdY + 11*x,5*x,x);
        g.fillRect(birdX + 9*x, birdY + x,x,x);
        g.fillRect(birdX + 8*x, birdY + 2*x,x,3*x);
        g.fillRect(birdX + 9*x, birdY + 5*x,x,x);
        g.fillRect(birdX + 10*x, birdY + 6*x,6*x,x);
        g.fillRect(birdX + 12*x, birdY + x,x,x);
        g.fillRect(birdX + 13*x, birdY + 2*x,x,x);
        g.fillRect(birdX + 14*x, birdY + 3*x,x,3*x);
        g.fillRect(birdX + 16*x, birdY + 7*x,x,x);
        g.fillRect(birdX + 10*x, birdY + 8*x,6*x,x);
        g.fillRect(birdX + 15*x, birdY + 9*x,x,x);
        g.fillRect(birdX + 10*x, birdY + 10*x,5*x,x);
        g.fillRect(birdX + 8*x, birdY + 8*x,x,x);
        g.fillRect(birdX + 9*x, birdY + 7*x,x,x);
        g.fillRect(birdX + 9*x, birdY + 9*x,x,x);
        g.fillRect(birdX + 12*x, birdY + 3*x,x,2*x);

        g.setColor(red);
        g.fillRect(birdX + 10*x, birdY + 7*x,6*x,x);
        g.fillRect(birdX + 10*x, birdY + 9*x,5*x,x);
        g.fillRect(birdX + 9*x, birdY + 8*x,x,x);

        g.setColor(Color.WHITE);
        g.fillRect(birdX + x, birdY + 4*x,4*x,2*x);
        g.fillRect(birdX + 5*x, birdY + 5*x,x,x);
        g.fillRect(birdX + 2*x, birdY + 6*x,3*x,x);
        g.fillRect(birdX + 10*x, birdY + x,2*x,x);
        g.fillRect(birdX + 9*x, birdY + 2*x,4*x,x);
        g.fillRect(birdX + 9*x, birdY + 3*x,3*x,2*x);
        g.fillRect(birdX + 13*x, birdY + 3*x,x,3*x);
        g.fillRect(birdX + 10*x, birdY + 5*x,3*x,x);

        g.setColor(brightyellow);
        g.fillRect(birdX + x, birdY + 6*x,x,x);
        g.fillRect(birdX + 2*x, birdY + 7*x,3*x,x);
    	g.fillRect(birdX + 5*x, birdY + 6*x,x,x);
		
	}

	public void move() {

		if (!jump) {
			birdY += speed;

			if (speed < 16) {
				speed += 1;
			}
		}

		if (jump) {
			if (onetimeJump) {
				speed = -15;
				onetimeJump = false;
			}

			birdY += speed;
			if (speed < 0) {
				speed += 1;
			} else {
				jump = false;
				onetimeJump = true;
			}
		}
	}

	public Rectangle bounds() {

		return (new Rectangle(400, birdY, 40, 40));

	}

}
