package weapons;

import java.awt.Color;
import java.awt.Graphics;

public class RifleBullets extends bullet{
	
	double bulletTrail;
	public boolean Right = true;
	int bulletSpeedConstant;
	
	public RifleBullets(double x, double y) {
		super(x, y);
		
		bulletSpeedConstant = 80;
		damage = 20;
		bulletTrail = 5;
	}

	public void drawBullet(Graphics g) {
		if (bulletFire) {
			addImage(g, "//Bullet//riflebullet.png", bulletX, (int) bulletY + yoffset);
		
			g.setColor(Color.YELLOW);
			g.fillRect((int)bulletX + 1, (int) bulletY + 29, (int) bulletTrail, 2);
			if(Right && bulletTrail > -540) {
				bulletTrail-=30;
			}
			else if(bulletTrail < 540){
				bulletTrail+=30;
			}
		}	
	}
	
	public void trackbullet(double x) {
		
		if(Right && !bulletFire) {
			bulletSpeed = bulletSpeedConstant;
		}
		if(!Right && !bulletFire) {
			bulletSpeed = -bulletSpeedConstant;
		}
		if(bulletX > x + 2000) {
	
			bulletX = -200;
			bulletY = -500;
			bulletTrail = 0;
			bulletSpeed = 0;
			
		}
	}
	
}
