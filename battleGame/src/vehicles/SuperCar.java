package vehicles;

import java.awt.Graphics;

public class SuperCar extends car {

	public SuperCar() {
		x = 750.0;
		y = 350;
		topSpeed = 120;
		accelerationFactor = 0.5;
	}
	
	public void draw(Graphics g) {
		if (carRight) {
			addImage(g, "Vehicles/supercar.png", x, (int) y);
		} else {
			addImage(g, "Vehicles/supercarFlipped.png", x, (int) y);
		}
	}

}
