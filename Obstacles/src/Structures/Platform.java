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
	private boolean ore = false;
	private boolean renderTop = false; 
	private boolean renderLeft = false;
	private boolean renderRight = false;
	private boolean renderBottom = false;
	private boolean highlight = false;
	private String tag;
	public static int blockSize = 60;
	private boolean upHill;
	public Platform(double x, double y) {
		width = 40;
		height = 40;
		setTag("blank");
		this.x = x;
		this.y = y;

	}

	public void setSize(double x1) {
		width = x1;
		height = x1;
	}
	
	public void showColliders(Graphics g) {
		g.setColor(Color.RED);
		if (!getCTop()) {
			g.fillRect((int) x, (int) y - 5, (int) (width), (int) (10));
		}
		if (!getCLeft()) {
			g.fillRect((int) x - 5, (int) y, (int) (10), (int) (height));
		}
		if (!getCRight()) {
			g.fillRect((int) (x + width - 5), (int) y, (int) (10), (int) (height));
		}
		if (!getCBottom()) {
			g.fillRect((int) x, (int) (y + height), (int) (width), (int) (5));
		}
		g.setColor(Color.BLACK);
	}

	public boolean getOre() {
		return ore;
	}
	public void setOre(boolean x) {
		ore = x;
	}
	public void setHighlight(boolean x) {
		highlight = x;
	}

	public boolean getHighlight() {
		return highlight;
	}

	public void setTheBounds(boolean x) {
		stopCollisionRight(!x);
		stopCollisionLeft(!x);
		stopCollisionTop(!x);
		stopCollisionBottom(!x);
		setSolid(x);
	}
	
	public void draw(Graphics2D g2d, Graphics g) {

	}
	public void setUpHill(boolean x) {
		upHill = x;
	}
	public boolean getUpHill() {
		return upHill;
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
		return stopCollisionRight;
	}

	public boolean getCLeft() {
		return stopCollisionLeft;
	}

	public boolean getCTop() {
		return stopCollisionTop;
	}

	public boolean getCBottom() {
		return stopCollisionBottom;
	}

	public Rectangle bounds() {
		return (new Rectangle((int) x, (int) y, (int) (width), (int) (height)));
	}

	public boolean getChangeTexture() {
		return changeTexture;
	}

	public void setTag(String x) {
		tag = x;
	}
	public String getTag() {
		return tag;
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
