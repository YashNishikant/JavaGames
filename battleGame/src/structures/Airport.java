package structures;

import java.awt.Graphics;

import engine.Textures;

public class Airport extends Textures{

	public double X;
	public double Y;
	public double illusionSpeed;
	
	public Airport(int x) {
		X = x;
		Y = -36;
		illusionSpeed = 0;
	}
	
	public void move() {
		X += illusionSpeed;
	}
	
	public void draw(Graphics g) {
		addImage(g, "//Backgrounds//Airport.png", (int) X, (int) Y);
	}
	
}
