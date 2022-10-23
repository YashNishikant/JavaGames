


import java.awt.Color;
import java.awt.Graphics;

public class clouds {

	int cloud1start = 50, cloud2start = -800, cloud3start = -1000;

	public clouds() {

		cloud1start = 50;
		cloud2start = -800;
		cloud3start = -1000;

	}

	public void move() {

		cloud1start += 2;
		cloud2start += 2;

		if (cloud1start == 2000) {
			cloud1start = -90;
		}

		if (cloud2start == 2000) {
			cloud2start = -100;
		}

		if (cloud3start == 2000) {
			cloud3start = -1600;
		}

	}

	public void draw(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect(cloud1start, 60, 200, 40);
		g.setColor(Color.white);
		g.fillRect(cloud2start, 120, 200, 40);
		g.setColor(Color.white);
		g.fillRect(cloud3start, 80, 200, 40);
		
	}
	
}
