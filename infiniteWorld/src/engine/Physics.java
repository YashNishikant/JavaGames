package engine;

import javax.swing.JPanel;

public class Physics extends JPanel {

	public double y;
	public double x;
	public double speedObjectV = 0;
	public double speedObjectH = 0;
	int random;
	public boolean forceHorizontal;
	public boolean forceVertical;

	public Physics() {

	}

	public int randomInt(int a, int b) {

		random = (int) ((Math.random() * (b - a + 1)) + a);
		return random;

	}

	public double applyForceVertical(double speed) {
		if (forceVertical) {

			if (speedObjectV < speed) {
				speedObjectV += 0.5;
			}
			if (speedObjectV > speed) {
				speedObjectV -= 0.5;
			}

			y += speedObjectV;
		} else {
			if ((int) speedObjectV != 0) {

				y += speedObjectV;

				speedObjectV = 0;
				
			}
		}
		return y;
	}

	public double applyForceHorizontal(double speed) {
		
		if (forceHorizontal) {

			if (speedObjectH < speed) {
				speedObjectH += 0.5;
			}
			if (speedObjectH > speed) {
				speedObjectH -= 0.5;
			}

			x += speedObjectH;

		} else {
			if ((int) speedObjectH != 0) {

				x += speedObjectH;

				speedObjectH = 0;
				
			}
		}
		return x;
	}

}
