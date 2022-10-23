package Environment;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Ground extends land {
	
	public double floorSpeed;
	public int YGround;
	public int width = 2000;
	public int widthTemp = 320;
	public Ground(double x1) {
		super(x1);
		XGround = x1;
		YGround = 948;
		floorSpeed = 0;
	}

	public void draw(Graphics g) {
		addImage(g, "Backgrounds//Ground.png", (int) XGround, YGround);
		addImage(g, "Backgrounds//Ground.png", (int) XGround + widthTemp, YGround);
		addImage(g, "Backgrounds//Ground.png", (int) XGround + widthTemp*2, YGround);
		addImage(g, "Backgrounds//Ground.png", (int) XGround + widthTemp*3, YGround);
		addImage(g, "Backgrounds//Ground.png", (int) XGround + widthTemp*4, YGround);
		addImage(g, "Backgrounds//Ground.png", (int) XGround + widthTemp*5, YGround);
		XGround += floorSpeed;
	}

	public Rectangle bounds() {

		return (new Rectangle((int) XGround, YGround, width, 124));

	}
}
