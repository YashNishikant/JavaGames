package weapons;

import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Textures;

public class bullet extends Textures {

	public double bulletX = 0;
	public double bulletY = 0;
	public double damageForBoss = 5;
	public int yoffset = 24;
	public int bulletSpeed = 60;
	public int width = 10;
	public int height = 5;
	public int damage = 10;
	public boolean bulletFire = false;
	public boolean letdestroy = false;

	public bullet(double x, double y) {
		bulletX = x;
		bulletY = y;
	}

	public void fire() {
		bulletX = bulletX + bulletSpeed;
	}

	public void draw(Graphics g) {
		if (bulletFire) {
			addImage(g, "//Bullet//bullet.png", bulletX, (int) bulletY + yoffset);
		}
	}

	public Rectangle bounds() {
		return (new Rectangle((int) bulletX, (int) bulletY + yoffset, width + 15, height));
	}

	public void destroy() {

		if (letdestroy) {
			bulletSpeed = 0;
			bulletX = 9000;
			bulletY = -4000;
		}
	}

}
