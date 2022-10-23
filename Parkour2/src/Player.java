
import java.awt.Color;  
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
	
	private double width;
	private double height;
	private double x;
	private double y;
	public int constantY = 400;
	private boolean grounded;
	
	
	public Player(double x1, double y1) {
		setXPos(700);
		setYPos(constantY);
		width = x1;
		height = y1;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillOval((int)xPos(), (int)yPos(), (int)(width), (int)(height));
	}
	
	public boolean getGrounded() {
		return grounded;
	}
	public void setGrounded(boolean x) {
		grounded = x;
	}
	public double playerWidth() {
		return width;
	}
	public double playerHeight() {
		return height;
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
	
	public Rectangle bounds() {
		return(new Rectangle((int)xPos(), (int)yPos(), (int)(width), (int)(height)));
	}
}
