package playerNpc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Textures;

public class BattleBoss extends Textures{
	public int Y;
	public int X;
	int Health;
	public double speed;
	public double speedY;
	int wHitBox;
	public int fireBulletTick;
	int hHitBox;
	int healthbarbox;
	int healthBar;
	public double healthcount;
	public int speedaddition;
	int turnaround;
	int knockbackstr;
	int lengthwidth = 3000;
	public boolean destroyerFireLock = true;
	public boolean knockback;
	public boolean alive = true;
	public boolean dropLock = true;

	public boolean attackRight;
	public boolean attackLeft;

	public boolean goUp;
	public boolean goDown;

	public int attackRangeVertical = 30;
	
	public int bossSpeed;
	
	public BattleBoss(int x, int y) {

		Y = y;
		X = x;
		Health = 100;
		speed = 0;
		wHitBox = 38;
		hHitBox = 85;
		healthbarbox = X - 1;
		healthBar = X;
		healthcount = 40;
		speedaddition = 0;
		knockbackstr = 6;
		bossSpeed = 2;

	}

	public void Behavior() {

		turnaround = (int) (Math.random() * 405) + 1;

		if (knockback) {
			X += knockbackstr;
			healthbarbox += knockbackstr;
			healthBar += knockbackstr;
		}

		if (healthcount <= 0) {
			alive = false;
		} else {
			alive = true;
		}

		if (alive) {

			if (turnaround == 50) {
				speedaddition = -1;
			}

			if (turnaround == 60) {
				speedaddition = 1;
			}

		} else {
			speedaddition = 0;
		}
	}
	
	public void drawHealth(Graphics g) {
		if (alive) {

			// healthbar
			g.setColor(Color.black);
			g.fillRect(healthbarbox - 10, Y + 1, 42, 6);
			g.setColor(Color.magenta);
			g.fillRect(healthBar - 10, Y + 3, (int) healthcount, 2);

		}
	}

	public void move() {

		X = (int) (X + speed + speedaddition);
		Y = (int) (Y + speedY);
		healthbarbox = (int) (healthbarbox + speed + speedaddition);
		healthBar = (int) (healthBar + speed + speedaddition);
		if (alive) {
			if (Y >= 872) {
				Y = 871;
			}
		}	
	}

	public void draw(Graphics g) {
		if (alive) {
			addImage(g, "//Destroyer//Destroyer.png", X - 12, (int) (Y + 10));

		} else {
			addImage(g, "//Destroyer//DestroyerDead.png", X - 12, (int) (Y + 10));
		}
	}
	
	public Rectangle bounds() {
		return (new Rectangle(X - 9, Y + 10, wHitBox, hHitBox));
	}

	public Rectangle detection() {
		return (new Rectangle(X - (lengthwidth / 2), Y - (lengthwidth / 2), lengthwidth, lengthwidth));
	}
}