import java.awt.Color;  
import java.awt.Graphics;

import java.awt.Rectangle;

public class Platform {

	public double width;
	public double height;
	private double y;
	private double fallingSpeed;
	private boolean gravityInverseActivate = true;
	private double gravity = 0.5;
	private double x;
	private boolean forceHorizontalL;
	private boolean forceHorizontalR;
	private double speed = 20;
	
	public Platform(int x, int y) {
		width = randomInt(150, 450);
		height = randomInt(150, 400);

		this.setXPos(500 + x);
		this.setYPos(y);

	}

	public void Update() {
		//Gravity
		if (gravityInverseActivate) {
			y -= fallingSpeed;
			fallingSpeed += gravity;
		}
		//Horizontal Motion
		if (forceHorizontalL) {
			x += speed;
		}

		if (forceHorizontalR) {
			x -= speed;
		}
		
	}
	
	public void FreezeHorizontal() {
		forceHorizontalL = false;	
		forceHorizontalR = false;
	}
	
	public void FreezeVertical() {
		fallingSpeed = 0;
	}

	 public void forceLeft() {
		forceHorizontalL = true;	
		forceHorizontalR = false;
	}
	 public void forceRight() {
		forceHorizontalL = false;	
		forceHorizontalR = true;
	}
	public double xPos() {
		return x;
	}
	public double yPos() {
		return y;
	}
	public void setXPos(double newpos) {
		x = newpos;
	}
	public void setYPos(double newpos) {
		y = newpos;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) xPos(), (int) yPos(), (int) (width), (int) (height));
		
	}

	public Rectangle bounds() {
		return (new Rectangle((int) xPos(), (int) yPos(), (int) (width), (int) (height)));
	}

	 public int randomInt(int a, int b) {
		
		int random = (int) ((Math.random() * (b - a + 1)) + a);
		return random;
	}
	
}