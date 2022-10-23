package Structures;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class AirBlock extends Platform {

	private double width;
	private double height;
	
	public AirBlock(double x, double y) {
		super(x, y);
		
		setSolid(false);
		setTag("air");
		this.x = x;
		this.y = y;
	
		setTheBounds(false);
		
	}
	
	public void setTheBounds(boolean x) {
		stopCollisionRight(!x);
		stopCollisionLeft(!x);
		stopCollisionTop(!x);
		stopCollisionBottom(!x);
		setSolid(x);
	}
	
	public Rectangle bounds1() {
		if (!getCTop()) {
			return (new Rectangle((int) x + 10, (int) y - 1, (int) (width) - 20, (int) (height / 30)));
		} else {
			return (new Rectangle(0, 0, 0, 0));
		}
	}

	public Rectangle bounds2() {
		if (!getCLeft()) {
			return (new Rectangle((int) x - 1, (int) y + 10, (int) (width / 30), (int) (height) - 20));
		} else {
			return (new Rectangle(0, 0, 0, 0));
		}
	}

	public Rectangle bounds3() {
		if (!getCRight()) {
			return (new Rectangle((int) (x + width), (int) y + 10, (int) (width / 30), (int) (height) - 20));
		} else {
			return (new Rectangle(0, 0, 0, 0));
		}
	}

	public Rectangle bounds4() {
		if (!getCBottom()) {
			return (new Rectangle((int) x + 10, (int) (y + height), (int) (width) - 20, (int) (height / 30)));
		} else {
			return (new Rectangle(0, 0, 0, 0));
		}
	}
}
