import java.awt.Color;   
import java.awt.Graphics;
import java.awt.Rectangle;
import java.sql.Time;

public class Platform {

	Time t;
	
	public double width;
	public double height;
	private double y;
	private double fallingSpeed;
	private boolean gravityInverseActivate = true;
	private double gravity = 0.5;
	private boolean forceUp;
	private double force;
	private boolean AppyInitialForce = true;
	private double x;
	private boolean forceHorizontalL;
	private boolean forceHorizontalR;

	private int r;
	private int g;
	private int b;
	
	private double speed = 20;
	

	private double DOWNWARD_FORCE = 0.6;
	String assetsPath = System.getProperty("user.dir") + "\\src\\assets\\";
	
	public Platform(int x, int y, int w, int h, int r, int g, int b) {
		width = w;
		height = h;

		this.r = r;
		this.g = g;
		this.b = b;
		
		this.setXPos(500 + x);
		this.setYPos(y);

	}

	public void Update() {
		//Gravity
		if (gravityInverseActivate) {
			y -= fallingSpeed;
			fallingSpeed += gravity;
		}
		//Jumping
		if (forceUp) {
			//INITIAL FORCE
			if (AppyInitialForce) {
				fallingSpeed = 0;
				force = 15;
				AppyInitialForce = false;
			}
			//INITIAL FORCE
			
			//rest of the player's jump path
			y += force;
			force -= DOWNWARD_FORCE;
			gravityInverseActivate = false;
		}
		if (force <= 0) {
			forceUp = false;
			gravityInverseActivate = true;
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
		AppyInitialForce = true;	
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
	public void Jump() {
		forceUp = true;
		AppyInitialForce = true;
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
	
	public void draw(Graphics g2) {
		g2.setColor(new Color(r, g, b));
		g2.fillRect((int) xPos(), (int) yPos(), (int) (width), (int) (height));
	}
	
	public Rectangle bounds() {
		return (new Rectangle((int) xPos(), (int) yPos(), (int) (width), (int) (height)));
	}

	 public int randomInt(int a, int b) {
		
		int random = (int) ((Math.random() * (b - a + 1)) + a);
		return random;
	}
	
}