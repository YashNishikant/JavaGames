package Environment;

import java.awt.Graphics;

import engine.Textures;

public class clouds extends Textures{

	public int cloud1start = -50;
	public int cloud2start = -800;
	public int cloud3start = -1000;
	public int cloud4start = -1200;
	public int cloud1Y, cloud2Y, cloud3Y, cloud4Y;
	
	public int cloudSpeed;
	public int returningArea;
	public clouds() {

		cloud1start = -50;
		cloud2start = -800;
		cloud3start = -1600;
		cloud4start = -2100;
		
		cloud1Y = 80;
		cloud2Y = 120;
		cloud3Y = 100;
		cloud4Y = 150;

		cloudSpeed = 1*2;
		returningArea = -100;
	}

	public void draw(Graphics g) {
		addImage(g, "//Backgrounds//Cloud.png", cloud1start, cloud1Y);
		addImage(g, "//Backgrounds//Cloud.png", cloud2start, cloud2Y);
		addImage(g, "//Backgrounds//Cloud.png", cloud3start, cloud3Y);
		addImage(g, "//Backgrounds//Cloud.png", cloud4start, cloud4Y);
	}
	
	public void move() {

		cloud1start += cloudSpeed;
		cloud2start += cloudSpeed;
		cloud3start += cloudSpeed;
		cloud4start += cloudSpeed;

		if (cloud1start == 2500) {
			cloud1start = returningArea ;
		}

		if (cloud2start == 2500) {
			cloud2start = returningArea ;
		}

		if (cloud3start == 2500) {
			cloud3start = returningArea ;
		}

		if (cloud4start == 2500) {
			cloud4start = returningArea ;
		}
		
	}	
}
