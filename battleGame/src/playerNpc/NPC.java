package playerNpc;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import engine.Textures;

public class NPC extends Textures {

	int replaceFace = 12;
	public int npcY;
	public double npcX;
	public int npcHealth;
	public double speed;
	public int wHitBox;
	public int hHitBox;
	public int healthcount;
	public int speedY;
	public int speedaddition;
	public int turnaround;
	public int knockbackstr;
	public int animationSpeed = 9;
	public int animationLimit = 5;
	public int animationNumberR = 1;
	public int animationDelayR;

	public int animationNumberL = 1;
	public int animationDelayL;

	public int runninganimationR = 1;
	public int runninganimationDelayR;
	
	public boolean playerPos = true;
	public boolean playerPos2 = true;
	public boolean animateLeft;
	public boolean animateRight;
	public boolean oneTimeJump;

	String assetsPath;

	public boolean knockbackR = false;
	public boolean knockbackL = false;
	public boolean alive = true;
	public boolean dropLock = true;
	public boolean turnleft;
	public boolean turnright;

	public int randomDirection;

	public int turnCounter;
	
	public NPC(int x, int speed1) {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		npcY = 870;
		npcX = x;
		npcHealth = 100;
		speed = 0;
		wHitBox = 38;
		hHitBox = 70;
		healthcount = 40;
		speedaddition = 0;
		knockbackstr = 6;
	}

	public void npcBehavior() {
		
		turnCounter++;
		if(turnCounter == 100000000) {
			turnCounter = 0;
		}
		
		if(turnCounter % 200 == 0) {
			turnaround = (int) (Math.random() * 1001) + 1;
		}
		if (knockbackR) {
			npcX += knockbackstr;
		}
		if (knockbackL) {
			npcX -= knockbackstr;
		}
		
		if (healthcount <= 0) {
			alive = false;
		} else {
			alive = true;
		}

		if (alive) {

			if (turnaround < 200) {
				turnleft = true;
				turnright = false;
				if(turnleft) {
					speedaddition = -2;
				}
			}

			if (turnaround > 800) {
				turnright = true;
				turnleft = false;
			
				if(turnright) {
					speedaddition = 2;
				}
				
			}

		} else {
			speedaddition = 0;
		}
	}

	public void drawNPC(Graphics g) {

		randomDirection = (int) (Math.random() * 1);

		if (alive) {

			if (turnright) {
				//addImage(g, "//Player//PlayerRightARMS.png", (int) (npcX - 3), (int) npcY + 30);
				addImage(g, "//Player//PlayerFaceRight.png", (int) (npcX + 1), (int) npcY + replaceFace);
			} else {
				//addImage(g, "//Player//PlayerRightARMS.png", (int) (npcX - 5), (int) npcY + 30);
				addImage(g, "//Player//PlayerFaceLeft.png", (int) npcX, (int) npcY + replaceFace);
			}

			if (!turnright && !turnleft) {
				if (randomDirection == 1) {
					addImage(g, "PlayerRight.png", (int) npcX, (int) npcY + 12);
					addImage(g, "//Player//PlayerFaceRight.png", (int) (npcX + 1), (int) npcY + replaceFace);
				}
				if (randomDirection == 0) {
					addImage(g, "//Player//PlayerLeft.png", (int) npcX, (int) npcY + 12);
					addImage(g, "//Player//PlayerFaceLeft.png", (int) npcX, (int) npcY + replaceFace);
				}
			}

			if (turnright) {
				animateRight = true;
				animateLeft = false;

				animationNumberR = animation(g, animationDelayR, animationNumberR, animateRight, "PlayerRight",
						(int) npcX, (int) npcY + 12, animationLimit, animationSpeed);
				animationDelayR++;
				
			}

			if (turnleft) {
				animateRight = false;
				animateLeft = true;

				animationNumberL = animation(g, animationDelayL, animationNumberL, animateLeft, "PlayerLeft",
						(int) npcX, (int) npcY + 12, animationLimit, animationSpeed);
				animationDelayL++;
			}
		}
	}

	public void dead(Graphics g) {
		addImage(g, "//Icons//skull.png", (int) npcX, npcY + 10);

	}

	public void drawNPCHealth(Graphics g) {
		if (alive) {
			// healthbar
			g.setColor(Color.black);
			g.fillRect((int) (npcX - 6), npcY + 1, 42, 6);
			g.setColor(Color.green);
			g.fillRect((int) (npcX - 5), npcY + 3, healthcount, 2);
		}
	}

	public void move() {
		npcX = (int) (npcX + speed + speedaddition);
		npcY = npcY + speedY;
	}

	public Rectangle bounds() {

		return (new Rectangle((int) (npcX - 9), npcY + 10, wHitBox, hHitBox));

	}

}
