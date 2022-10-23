package engine;

import javax.swing.JPanel;

public class Physics extends JPanel {

	public double fallingFactor;
	public boolean gravityActivate = true;
	public boolean forceUp;
	public boolean hitground;
	public double force;
	public boolean setForce = true;
	public double y;
	int random;
	public double DOWNWARD_FORCE = 0.5;
	
	public Physics() {
		fallingFactor = 0;
	}

	public double gravity(double gravity) {
		if (gravityActivate) {
			y += fallingFactor;
			fallingFactor += gravity;
		}
		return y;
	}

	public int randomInt(int a, int b) {
		
		random = (int) ((Math.random()*(b-a+1)) + a);
		return random;
		
	}
	
	public double applyForceVertical(double ForceReplace) {
		if (forceUp) {
			if(setForce) {
				force = -ForceReplace;
				setForce = false;
			}
			y += force;
			force += DOWNWARD_FORCE;
			gravityActivate = false;
		}
		
		if(force > 0) {
			forceUp = false;
			gravityActivate = true;
		}
		
		return y;
	}
}
