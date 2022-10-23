package freeworld;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import engine.Textures;

public class controls extends Textures {

	public boolean setAmmo = true;
	int ammo;
	int rifleammo;
	int enemyAmmoL;
	int enemyAmmoR;
	int hitcount;
	int greenblue;
	int grenadeAmmo;
	boolean darkenSky;
	int knockbackRNG;
	int shieldHP;
	public boolean beginRain = false;
	public boolean showCity;
	public boolean night;
	public int cooldown;
	public boolean cooldownOver;
	public double farthestChunk = Double.MIN_VALUE;
	public double closestChunk = Double.MAX_VALUE;
	String assetsPath;

	public controls(int x) {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		hitcount = 0;
		ammo = 99;
		enemyAmmoL = 9;
		enemyAmmoR = 9;
		greenblue = 250;
		grenadeAmmo = 20;
		shieldHP = 190;
	}

	public void naturaldrawings(Graphics g) {
		
		if(showCity) {
			
		}
		
		if (!beginRain && !night) {
			addImage(g, "//Backgrounds//Sky.png", 0, 0);
		}
		else if(!night){
			addImage(g, "//Backgrounds//RainSky.png", 0, 0);
		}
	
		if(night) {
			addImage(g, "//Backgrounds//NightSky.png", 0, 0);
		}
		
	}
	public void drawArmor(Graphics g) {

		g.setColor(Color.BLACK);
		g.fillRect(20, 182, 22, 22);
		g.setColor(Color.WHITE);
		g.fillRect(22, 184, 18, 18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("F Ready Blaster", 24, 200);

		g.setColor(Color.BLACK);
		g.fillRect(20, 232, 22, 22);
		g.setColor(Color.WHITE);
		g.fillRect(22, 234, 18, 18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("R Fire Bullets", 24, 250);

		g.setColor(Color.BLACK);
		g.fillRect(20, 132, 22, 22);
		g.setColor(Color.WHITE);
		g.fillRect(22, 134, 18, 18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("X Fire Explosives", 24, 150);

		g.setColor(Color.BLACK);
		g.fillRect(20, 282, 22, 22);
		g.setColor(Color.WHITE);
		g.fillRect(22, 284, 18, 18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("C Exit Suit", 24, 300);

		g.setColor(Color.BLACK);
		g.fillRect(20, 332, 22, 22);
		g.setColor(Color.WHITE);
		g.fillRect(22, 334, 18, 18);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("G Unready Blaster", 24, 350);

		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 25));
		g.drawString("Ammo: " + ammo, 20, 50);

//		g.setColor(Color.BLACK);
//		g.setFont(new Font("default", Font.BOLD, 25));
//		g.drawString("Explosive Ammo: " + grenadeAmmo, 20, 80);

		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 15));
		g.drawString("Battery", 1650, 15);

		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 15));
		g.drawString("Shield HP", 1650, 115);

		// battery
		g.setColor(Color.black);
		g.fillRect(1650, 120, 200, 30);
		// cap
		g.setColor(Color.BLACK);
		g.fillRect(1660, 125, 200, 20);

		g.setColor(Color.GREEN);
		g.fillRect(1655, 125, shieldHP, 20);

	}

}