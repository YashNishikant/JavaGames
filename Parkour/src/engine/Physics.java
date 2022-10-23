package engine;

import javax.swing.JPanel;

 public class Physics extends JPanel {

	double fallingFactor;
	boolean gravityActivate = true;
	boolean gravityInverseActivate = true;
	boolean forceUp;
	boolean hitground;
	double force;
	boolean setForce = true;
	double y;
	double x;
	double speedObject = 0;
	int random;
	boolean forceHorizontalL;
	boolean forceHorizontalR;
	boolean canJump;

	public double DOWNWARD_FORCE = 6;

	 public Physics() {
		fallingFactor = 0;
		DOWNWARD_FORCE /= 10;
	}

	 public double gravity(double gravity) {
		if (gravityActivate) {
			y += fallingFactor;
			fallingFactor += gravity;
		}
		return y;
	}

	 public double gravityInverse(double gravity) {
		if (gravityInverseActivate) {
			y -= fallingFactor;
			fallingFactor += gravity;
		}
		return y;
	}

	 public int randomInt(int a, int b) {
		random = (int) ((Math.random() * (b - a + 1)) + a);
		return random;
	}

	 public void FreezeVertical() {
		setForce = true;	
		fallingFactor = 0;
	}
	 public void FreezeHorizontal() {
		forceHorizontalL = false;	
		forceHorizontalR = false;
	}
	 public void forceLeft() {
		forceHorizontalL = true;	
		forceHorizontalR = false;
	}
	 public void forceRight() {
		forceHorizontalL = false;	
		forceHorizontalR = true;
	}
	 public double AffectedForceHorizontally(double speed) {
		if (forceHorizontalL) {
			speedObject = speed;
			x += speedObject;
		}

		if (forceHorizontalR) {
			speedObject = speed;
			x -= speedObject;
		}
		
		return x;
	}

	 public double AffectedForceVerticallyInverse(double ForceReplace) {

		if (forceUp) {
			if (setForce) {
				fallingFactor = 0;
				force = ForceReplace;
				setForce = false;
			}
			y += force;
			force -= DOWNWARD_FORCE;
			gravityInverseActivate = false;
		}
		if (force <= 0) {
			forceUp = false;
			gravityInverseActivate = true;
		}
		return y;
	}
	
	public boolean grounded() {
		return setForce;
	}
	 
	public void Jump() {
		forceUp = true;
		setForce = true;
	}
	
	public double xPos() {
		return x;
	}
	public double yPos() {
		return y;
	}
	public void setXPos(double newpos) {
		x = newpos;
	}
	public void setYPos(double newpos) {
		y = newpos;
	}
 }
