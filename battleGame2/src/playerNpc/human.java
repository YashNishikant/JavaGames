package playerNpc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import engine.Textures;

public class human extends Textures {

	int replaceFace = 12;

	public int walkingspeed = 9;
	
	public boolean isMoving;
	public double speedY;
	public double personX;
	public double maxheight;
	public int minheight;
	public int hHitBox;
	public int wHitBox;
	public boolean allowJ = true;

	public int healthbarbox;
	public int health;
	
	public int healthcount;
	public int healthcountPlaceHolder;

	public boolean jump;
	public boolean rise = true;
	public boolean insideSuit;
	public boolean nobattery;
	public boolean onground;
	public boolean hitgrass;
	public boolean onetimeJump = true;
	public boolean jumping;
	public boolean death;
	public boolean holdingWeapon;

	String assetsPath;

	public boolean weaponLeft = false;
	public boolean weaponRight = false;
	
	public boolean animateLeft;
	public boolean animateRight;
	public boolean turnRight = true;
	public boolean turnLeft;

	public int dropSpeed = 10;
	
	public int height = 78;
	
	public human() {

		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		personX = (1920/2);
		y = 470;
		maxheight = y - 100;
		healthcount = 800;
		minheight = 870;
		hHitBox = 70;
		wHitBox = 38;
		healthcountPlaceHolder = healthcount;
		animationSpeed = 9;
	}

	public void move() {
		if (death) {
			if (y <= 915) {
				speedY = 3;
				gravityActivate = false;
			} else {
				y += speedY;
			}
		}

	}

	public void drawHealth(Graphics g, double personX2, double personX3, int x) {

		g.setColor(Color.black);
		g.fillRect(30, 30, 800, 40);
		if (healthcount > 800 * 0.2) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(40, 45, healthcount, 30);

		if(healthcountPlaceHolder < healthcount && healthcount > 0) {
			healthcount-=dropSpeed;
		}
		
	}

	public void draw(Graphics g) {
		
		if (!death) {
			
			if (turnRight && !animateRight) {
				addImage(g, "//Player//PlayerRight.png", personX, (int) y + 12);
				addImage(g, "//Player//PlayerFaceRight.png", (personX + 2), (int) y + replaceFace);
			}
			if (turnLeft && !animateLeft) {
				addImage(g, "//Player//PlayerLeft.png", personX, (int) y + 12);
				addImage(g, "//Player//PlayerFaceLeft.png", (personX), (int) y + replaceFace);
			}
			// ANIMATION
			if (isMoving) {
				animationLimit = 5;

				animationNumberR = animation(g, animationDelayR, animationNumberR, animateRight, "PlayerRight", personX,
						(int) y + 12, animationLimit, animationSpeed);

				animationDelayR++;
				animationNumberL = animation(g, animationDelayL, animationNumberL, animateLeft, "PlayerLeft", personX,
						(int) y + 12, animationLimit, animationSpeed);
				animationDelayL++;
			}

			else {
				if (turnRight) {
					addImage(g, "//Player//PlayerRight.png", personX, (int) y + 12);
					addImage(g, "//Player//PlayerFaceRight.png", (personX + 2), (int) y + replaceFace);
				}

				if (turnLeft) {
					addImage(g, "//Player//PlayerLeft.png", (int) personX, (int) y + 12);
					addImage(g, "//Player//PlayerFaceLeft.png", (int) personX, (int) y + replaceFace);
				}
			}

			// ANIMATION

			if (turnRight) {
				if (!holdingWeapon) {
					//addImage(g, "//Player//PlayerRightARMS.png", (int) (personX - 3), (int) y + 30);
					addImage(g, "//Player//PlayerFaceRight.png", (int) (personX + 2), (int) y + replaceFace);
				} else {
					addImage(g, "//Player//PlayerArmsWeapon.png", (int) (personX - 3), (int) y + 30);
					addImage(g, "//Player//PlayerFaceRight.png", (int) (personX + 2), (int) y + replaceFace);		
				}
			}

			if (turnLeft) {
				if (!holdingWeapon) {
					//addImage(g, "//Player//PlayerLeftARMS.png", (int) (personX - 6), (int) y + 30);
					addImage(g, "//Player//PlayerFaceLeft.png", (int) (personX), (int) y + replaceFace);
				} else {
					addImage(g, "//Player//PlayerArmsWeaponFlip.png", (int) (personX - 19), (int) y + 30);
					addImage(g, "//Player//PlayerFaceLeft.png", (int) (personX), (int) y + replaceFace);		
				}
			}
			
		} else {
			addImage(g, "//Icons//skull.png", (int) personX, (int) (y + 10));
		}

	}

	public Rectangle bounds() {

		return (new Rectangle((int) (personX - 10), (int) (y + 10), wHitBox, hHitBox));

	}

}
