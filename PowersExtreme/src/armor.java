

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Textures;

public class armor extends Textures{

	public boolean fire = false;
	public boolean track = false;
	public boolean canfly = false;
	public boolean ignore = false;
	public boolean blast = false;
	public boolean normal = false;
	public boolean activatefire = true;
	public boolean fireonground = false;
	public boolean confirmgroundfire = false;
	public boolean flyIMG = false;
	public boolean turbo = false;
	public boolean ableToTurbo = false;
	public boolean flyIMG_LEFT = false;
	public boolean turbo_LEFT = false;
	public boolean ableToTurbo_LEFT = false;
	public boolean driftleft;
	public boolean driftright;
	public boolean crafted;

	public double armorPosX;
	public int armorPosY;
	public double armorspeed;
	public int wHitBox;
	public int hHitBox;

	public armor() {

		fire = false;
		track = false;
		canfly = false;
		ignore = false;

		wHitBox = 38;
		hHitBox = 75;

		armorPosX = 50;
		armorPosY = 870;

		armorspeed = 0;
		flyIMG = false;
		turbo = false;
		ableToTurbo = false;

	}

	public void batteryDrained(Graphics g) {
		g.setColor(Color.RED);
		g.setFont(new Font("default", Font.BOLD, 25));
		g.drawString("!", (int) armorPosX, armorPosY);
	}
	
	public void uncrafted() {
		if (!crafted) {
			//armorPosX = 0;
			//armorPosY = -100;
		}
	}

	public void Images(Graphics g) {
		
		if (!track) {
			normal = true;
			fire = false;
		}
		
		if (normal) {

			addImage(g, "//Armor//tankNOfire.png", armorPosX, armorPosY);

		}

		if (fire) {

			addImage(g, "//Armor//TankArmor.png", armorPosX, armorPosY);

		}

		if (blast) {

			addImage(g, "//Armor//tankBlast.png", armorPosX, armorPosY);

		}

		if (confirmgroundfire) {

			addImage(g, "//Armor//tankBlastGround.png", armorPosX, armorPosY);

		}

		// fly
		if (turbo) {

			addImage(g, "//Armor//TURBO_RIGHT.png", armorPosX, armorPosY);

		}
		if (turbo_LEFT) {

			addImage(g, "//Armor//TURBO_LEFT.png", armorPosX, armorPosY);

		}
	}
	
	public void tracking() {

		if (armorPosY >= 870 && fireonground == false) {
			fire = false;
			activatefire = true;
			canfly = false;
			normal = true;
			blast = false;
			confirmgroundfire = false;
		}

		if (armorPosY >= 870 && fireonground) {
			confirmgroundfire = true;
		}

		if (armorPosY < 865 && activatefire) {
			if (!turbo && !turbo_LEFT && !flyIMG && !flyIMG_LEFT)
				fire = true;
			canfly = true;
		}
	}

	public void move() {
		armorPosX = armorPosX + armorspeed;
	}

	public Rectangle bounds() {

		return (new Rectangle((int)armorPosX + 4, armorPosY, wHitBox, hHitBox));

	}

}