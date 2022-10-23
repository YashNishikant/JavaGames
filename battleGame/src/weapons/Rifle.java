package weapons;

import java.awt.Graphics;

import engine.Textures;

public class Rifle extends Textures {

	public double X;
	public int Y;

	public boolean ready;
	public boolean fire;
	public boolean fireweapon;
	public int fireCount;
	public boolean gunClicked;
	public int delay;
	public boolean canFire;
	public boolean flame;

	public boolean gunR = false;
	public boolean gunL = false;

	public Rifle() {
		X = 0;
		Y = 0;
	}

	public void draw(Graphics g) {
		if (ready) {
			if (gunR) {
				addImage(g, "Weapons//AR.png", X, Y);
			}
			if (gunL) {
				addImage(g, "Weapons//AR_Flip.png", X - 90, Y);
			}
			else {
				gunR = true;
			}
		}

		if (fireweapon) {
			if(gunR)
			addImage(g, "//Weapons//fireWeapon.png", X + 50, Y);
		
			if(gunL)
			addImage(g, "//Weapons//fireWeaponFlip.png", X - 115, Y);
		}

	}
}
