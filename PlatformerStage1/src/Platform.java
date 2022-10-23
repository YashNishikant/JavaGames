import java.awt.Color;   
import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform {

	public double width;
	public double height;
	private double y;
	private double x;
	
	public Platform(int x, int y) {
		width = 1000;
		height = 500;
		setXPos(200);
		setYPos(800);
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