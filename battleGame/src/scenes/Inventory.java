package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import engine.Textures;

public class Inventory extends Textures {

	public int X;
	public int Y;
	public int Xboard;
	public int Yboard;
	public int spacing1;
	public boolean draw;
	public boolean drawselect;
	public boolean selected;

	public Inventory(int spacing) {
		X = spacing;
		Y = 100;
		
		Xboard = 350;
		Yboard = 80;
	}

	public void drawSlots(Graphics g) {
		if (draw) {
			addImage(g, "Inventory//inventory.png", X, Y + 20);
		}
	}

	public void selectDraw(Graphics g) {
		
		Color transparentGreen = new Color(0, 1, 0, 0.5f);
		
		if (drawselect) {
			g.setColor(new Color(1f,0f,0f,0.5f)); // change to xf for x% darker
			g.fillRect(X+30, Y+50, 91, 91);
		}
	
		if(selected) {
			g.setColor(transparentGreen);
			g.fillRect(X+30, Y+50, 91, 91);
		}
		
	}
	
	public Rectangle bounds() {
		return(new Rectangle(X+30, Y+50, 91, 91));
	}
	
}
