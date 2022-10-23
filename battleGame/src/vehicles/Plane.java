package vehicles;

import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Textures;

public class Plane extends Textures {

	public double x;
	public double y;
	public double xwheels;
	public double ywheels;
	public double fallSpeed;
	public double accelerationFactor;
	public double topSpeed;
	public double ySpeed;
	public double riseSpeed;
	public boolean planePos = true;
	public double illusionspeed;
	public double planeSpeed = 0;
	public boolean canExit;
	public boolean canEnter;
	public boolean enter;
	public boolean decelerate;
	public boolean accelerate;
	public boolean wheels = true;
	public boolean planeLeft;
	public boolean planeRight;
	public boolean rise = true;
	public boolean fall;

	public Plane() {

		x = 0;
		y = 755;
		planeSpeed = 0;
		topSpeed = -100;
		riseSpeed = topSpeed / 50;
		fallSpeed = topSpeed - 10;
		ySpeed = 0;
		accelerationFactor = 0.05;
		ywheels = y;
	}

	public void move() {
		
		xwheels = x;

		if (y < 200) {
			y = 199.9;
		}

		if(ywheels < 170) {
			ywheels = 169.9;
		}
		
		if(ywheels > 755.1) {
			ywheels = 755.0;
		}
		
		if (y < 700) {
			wheels = false;
		}
		else {
			wheels = true;
		}
		
		if (!enter) {
			x += illusionspeed;
			xwheels += illusionspeed;
		}

		if (y >= 755.1) {
			y = 755.0;
		}

		y += ySpeed;
		ywheels += ySpeed;

		if (planeRight) {
			if (enter) {
				if (!decelerate && accelerate) {
					if (planeSpeed > topSpeed) {
						planeSpeed -= accelerationFactor;
					}
				}
			}

			if (decelerate) {
				if (planeSpeed < 0) {
					planeSpeed += accelerationFactor;

					if (planeSpeed > -0.0001 && planeSpeed < 0) {
						planeSpeed = 0;
					}

				} else {
					decelerate = false;
				}
			}
			if ((int) planeSpeed <= (int) riseSpeed && rise) {
				ySpeed = -0.2;
				fall = false;
			}

			if (fall) {
				ySpeed = 0.5;
				rise = false;
			}
		}

		if (planeLeft) {
			if (enter) {
				if (!decelerate && accelerate) {
					if (planeSpeed < topSpeed) {
						planeSpeed += accelerationFactor;
					}
				}
			}

			if (decelerate) {
				if (planeSpeed > 0) {
					planeSpeed -= accelerationFactor;

					if (planeSpeed < 0.0001) {
						planeSpeed = 0;
					}

				} else {
					decelerate = false;
				}
			}
		}
	}

	public void draw(Graphics g) {

		if (wheels) {
			addImage(g, "//Vehicles//planeWheels.png", xwheels, (int) ywheels);
		} else {
			if (ywheels > y - 30) {
				addImage(g, "//Vehicles//planeWheels.png", xwheels, (int) ywheels);
				ywheels -= 1;
			}
		}
		addImage(g, "//Vehicles//plane.png", x, (int) y);
	}

	public Rectangle bounds() {
		return (new Rectangle((int) x, (int) y, 450, 200));
	}

}
