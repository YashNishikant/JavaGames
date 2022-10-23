import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class human {

	double speedY;
	int personX;
	double personY;
	int maxheight;
	int minheight;
	int hHitBox;
	int wHitBox;

	boolean jump = false;
	boolean rise = true;
	boolean fall = false;
	boolean jumping;
	boolean onetimeJump;
	
	public human() {

		speedY = 0;
		personX = 810;
		personY = 600;
		maxheight = 600;
		minheight = 870;
		hHitBox = 70;
		wHitBox = 38;
	}

	public void move() {

		personY = personY + speedY;
	}

	public void jump() {
		
		if (!jump) {
			personY += speedY;

			jumping = false;
			
			if (speedY < 4) {
				speedY += 0.02;
			}
		}

		if (jump) {
			
			if (onetimeJump) {
				speedY = -3;
				onetimeJump = false;
				jumping = true;
			}
			personY += speedY;
			if (speedY < 3) {//terminal vel
				speedY += 0.02;
			} else {
				jump = false;
				onetimeJump = true;
				jumping = false;
			}
		}
	}
	public void draw(Graphics g) {

		// User
		g.setColor(Color.red);
		g.fillRect(personX, (int)(personY + 30), 20, 30);

		// legs
		g.setColor(Color.DARK_GRAY);
		g.fillRect(personX + 12, (int)(personY + 61), 7, 19);
		g.fillRect(personX + 1, (int)(personY + 61), 7, 19);

		// arms
		g.setColor(Color.GRAY);
		g.fillRect(personX + 21, (int)(personY + 30), 7, 25);
		g.fillRect(personX - 8, (int)(personY + 30), 7, 25);

	}

	public Rectangle bounds() {

		return (new Rectangle(personX - 10, (int)(personY + 10), wHitBox, hHitBox));

	}

}
