package Structures;

import java.awt.Graphics;
import java.awt.Rectangle;

public class AirBlock extends Platform{
	
	private boolean solid = false;
	
	public AirBlock(double x, double y) {
		
		super(x,y);
		
		
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g) {	
		
	}
	public void setDirtTexture(boolean x) {
		
	}
	public boolean getSolid() {
		return solid;
	}

	public Rectangle bounds1() {
		return (new Rectangle(0,0,0,0));
	}

	public Rectangle bounds2() {
		return (new Rectangle(0,0,0,0));
	}

	public Rectangle bounds3() {
		return (new Rectangle(0,0,0,0));
	}
	
	public Rectangle bounds4() {
		return (new Rectangle(0,0,0,0));
	}
}
