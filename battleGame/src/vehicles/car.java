package vehicles;

import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Textures;

public class car extends Textures {

	public double x;
	int topSpeed;
	double accelerationFactor;
	public double illusionspeed;
	public double carSpeed = 0;
	public boolean canExit;
	public boolean canEnter;
	public boolean enter;
	public boolean carLeft;
	public boolean carRight = true;
	public boolean decelerate;
	public boolean accelerate;
	public boolean decelerateNotIllusion;
	public boolean oneTime = true;
	public double yspeed;
	
	public car() {
		x = 450;
		y = 350;
		topSpeed = 30;
		accelerationFactor = 0.1;
	}

	public void move() {
		
		
		
		y += yspeed;
		
		if (!enter) {
			x += illusionspeed;
		}

		if (carRight) {
			if (enter) {
				if (!decelerate && accelerate) {
					if (carSpeed > -topSpeed) {
						carSpeed -= accelerationFactor;
					}
				}
			}

			if (decelerate) {
				if (carSpeed < 0) {
					carSpeed += accelerationFactor;

					if (carSpeed > -0.0001 && carSpeed < 0) {
						carSpeed = 0;
					}

				} else {
					decelerate = false;
				}
			}
		}

		if (carLeft) {
			if (enter) {
				if (!decelerate && accelerate) {
					if (carSpeed < topSpeed) {
						carSpeed += accelerationFactor;
					}
				}
			}

			if (decelerate) {
				if (carSpeed > 0) {
					carSpeed -= accelerationFactor;

					if (carSpeed < 0.0001) {
						carSpeed = 0;
					}

				} else {
					decelerate = false;
				}
			}
		}

		}

	public void draw(Graphics g) {
		if (carRight && !carLeft) {
			addImage(g, "//Vehicles//car.png", x, (int) y);
		}
		if (carLeft && !carRight) {
			addImage(g, "//Vehicles//carFlip.png", x, (int) y);
		}
	}

	public Rectangle bounds() {
		return (new Rectangle((int) x, (int)y, 150, 100));
	}

}
