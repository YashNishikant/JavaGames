package Item;

import java.awt.Graphics;
import java.awt.Rectangle;

public class clicker {

	private double x;
	private double y;
	private double w;
	private double h;
	
	public clicker() {
	
		x = 0;
		y = 0;
		w = 1;
		h = 1;
	}

	public void draw(Graphics g) {
		g.fillRect((int)x,(int)y,(int)w,(int)h);
	}
	
	public void setX(int x) {
		 this.x = x; 
	}     
	public void setY(int y) {
		this.y = y; 
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}

	public Rectangle bounds() {
		return(new Rectangle((int)x,(int)y,(int)w,(int)h));
	}
	
}
