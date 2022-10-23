package weapons;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class battery {

	public int powercount = 0;
	public int powerlength = 190;

	public int decreaseFactor = 1000;

	public boolean isflyingforbattery = false;
	public boolean track = false;
	public boolean turboReducePowerMore;

	public battery() {

		powercount = 0;
		powerlength = 190;

	}

	public void batteryfunction() {

		if (track) {
			powercount++;
		}

		if ((powercount % decreaseFactor == 0) && powerlength > 0 && isflyingforbattery) {
			powerlength -= 1;

		}

	}
	
	public void draw(Graphics g) {

		// batteryDisplay
		g.setColor(Color.black);
		g.fillRect(1650, 20, 200, 30);

		// cap
		g.setColor(Color.BLACK);
		g.fillRect(1660, 25, 200, 20);

		g.setColor(Color.white);
		if (powerlength <= 38) {
			g.setColor(Color.red);
		}
		g.fillRect(1655, 25, powerlength, 20);

	}

}