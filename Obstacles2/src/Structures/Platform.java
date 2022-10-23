package Structures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import engine.engine;

public class Platform extends engine {

	public double width;
	public double height;
	private boolean changeTexture;
	private boolean solid = true;
	private boolean stopCollisionTop;
	private boolean stopCollisionLeft;
	private boolean stopCollisionRight;
	private boolean stopCollisionBottom;
	private boolean renderTop = false;
	private boolean renderLeft = false;
	private boolean renderRight = false;
	private boolean renderBottom = false;
	private boolean highlight = false;

	public Platform(double x, double y) {
		width = 60;
		height = 60;

		this.x = x;
		this.y = y;

	}

	public void showColliders(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, (int) (width), (int) (height));
		if (renderTop) {
			g.fillRect((int) x, (int) y - 5, (int) (width), (int) (10));
		}
		if (renderLeft) {
			g.fillRect((int) x - 5, (int) y, (int) (10), (int) (height));
		}
		if (renderRight) {
			g.fillRect((int) (x + width-5), (int) y, (int) (10), (int) (height));
		}
		g.fillRect((int) x, (int) (y + height), (int) (width), (int) (5));
	}
	
	public void setHighlight(boolean x) {
		highlight = x;
	}
	public boolean getHighlight() {
		return highlight;
	}
	public void draw(Graphics2D g2d, Graphics g) {

	}

	public void setDirtTexture(boolean x) {
		changeTexture = x;
	}

	public boolean getDirtTexture() {
		return changeTexture;
	}
	public boolean getSolid() {
		return solid;
	}
	public void setSolid(boolean x) {
		solid = x;
	}
	public void stopCollisionTop(boolean x) {
		stopCollisionTop = x;
	}
	public void stopCollisionRight(boolean x) {
		stopCollisionRight = x;
	}
	public void stopCollisionLeft(boolean x) {
		stopCollisionLeft = x;
	}
	public void stopCollisionBottom(boolean x) {
		stopCollisionBottom = x;
	}
	
	public boolean getCRight() {
		return stopCollisionTop;
	}
	public boolean getCLeft() {
		return stopCollisionRight;
	}
	public boolean getCTop() {
		return stopCollisionLeft;
	}
	public boolean getCBottom() {
		return stopCollisionBottom;
	}
	
	public Rectangle bounds() {
		return (new Rectangle((int) x, (int) y, (int) (width), (int) (height)));
	}
	
	public Rectangle bounds1() {
		if (!stopCollisionTop) {
			renderTop = true;
			return (new Rectangle((int) x + 10, (int) y - 1, (int) (width) - 20, (int) (height / 30)));
		} else {
			renderTop = false;
			return (new Rectangle(0, 0, 0, 0));
		}
	}

	public Rectangle bounds2() {
		if (!stopCollisionLeft) {
			renderLeft = true;
			return (new Rectangle((int) x - 1, (int) y + 10, (int) (width / 30), (int) (height) - 20));
		} else {
			renderLeft = false;
			return (new Rectangle(0, 0, 0, 0));
		}
	}

	public Rectangle bounds3() {
		if (!stopCollisionRight) {
			renderRight = true;
			return (new Rectangle((int) (x + width), (int) y + 10, (int) (width / 30), (int) (height) - 20));
		} else {
			renderRight = false;
			return (new Rectangle(0, 0, 0, 0));
		}
	}

	public boolean getChangeTexture() {
		return changeTexture;
	}

	public Rectangle bounds4() {
		if (!stopCollisionBottom) {
			renderBottom = true;
			return (new Rectangle((int) x + 10, (int) (y + height), (int) (width) - 20, (int) (height / 30)));
		} else {
			renderBottom = false;
			return (new Rectangle(0, 0, 0, 0));
		}
	}
}
