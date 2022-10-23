package freeworld;

import java.awt.Container; 
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import Structures.Platform;
import engine.Physics;
import engine.engine;
import engine.mouseClicker;
import player.Player;

import java.awt.Rectangle;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class sandbox extends engine implements ActionListener, KeyListener {

	int spacing = 0;
	double jumpStrength;
	boolean disableleft;
	boolean disableright;
	int cameraSpeed = 1;

	Physics Physics = new Physics();
	mouseClicker click = new mouseClicker();
	Platform[] block = new Platform[50];

	Miscellaneous game = new Miscellaneous();
	Player user = new Player(50, 100);

	Timer time = new Timer(5, this);
	String assetsPath;

	public sandbox() {

		int x = randomInt(1, 2);
		for (int i = 0; i < block.length; i++) {
			Platform block1 = new Platform(spacing, 1000);
			block[i] = block1;
			spacing += randomInt(900, 1100);
		}
		for (int i = 1; i < block.length; i++) {
			if (x == 1) {
				block[i].setYPos(block[i - 1].yPos() + randomInt(40, 150));
			} else {
				block[i].setYPos(block[i - 1].yPos() - randomInt(40, 150));
			}

			x = randomInt(1, 2);

		}

		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public void actionPerformed(ActionEvent e) { 
		
		for(int i = 0; i < block.length; i++) {
			block[i].Update();
		}
		cameraFollow();
		Collision();
		repaint();
	}

	public void paintComponent(Graphics g) { 
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		
		game.drawBackground(g);
		drawBlocks(g, g2d);
		user.draw(g);
	}

	public void cameraFollow() {
		if ((int) user.yPos() > user.yCoord) {
			for (int i = 0; i < block.length; i++) {
				block[i].setYPos(block[i].yPos() - cameraSpeed);
			}
			user.setYPos(user.yPos() - cameraSpeed);
		}
		if ((int) user.yPos() < user.yCoord) {

			for (int i = 0; i < block.length; i++) {
				block[i].setYPos(block[i].yPos() + cameraSpeed);
			}
			user.setYPos(user.yPos() + cameraSpeed);
		}
	}

	public void drawBlocks(Graphics g, Graphics2D g2d) {

		for (int i = 0; i < block.length; i++) {
			if (block[i].xPos() > 0 - block[i].width || block[i].xPos() < 1920) {
				block[i].draw(g, g2d);
			}
		}
	}

	public void movePlayerL() { 
		if (!disableleft) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceLeft();
			}
		}
	}

	public void movePlayerR() { 
		if (!disableleft) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceRight();
			}
		}
	}

	public void stopHorizontal() {
		for (int j = 0; j < block.length; j++) {
			block[j].FreezeHorizontal();
		}
	}

	public void stopVertical() {
		for (int j = 0; j < block.length; j++) {
			block[j].FreezeVertical();
		}
	}
	
	public void playerjump() {
		for (int j = 0; j < block.length; j++) {
			block[j].Jump();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) { 
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_SPACE) {
			if (block[0].grounded()) {
				playerjump();
				jumpStrength = 15;
			}
		}

		if (i == KeyEvent.VK_A && !disableleft) {
			movePlayerL();
			disableright = false;
		}

		if (i == KeyEvent.VK_D && !disableright) {
			movePlayerR();
			disableleft = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();
		if (i == KeyEvent.VK_D) {
			stopHorizontal();
		}
		if (i == KeyEvent.VK_A) {
			stopHorizontal();
		}
	}

	public void Collision() { 

		Rectangle player = user.bounds();

		for (int j = 0; j < block.length; j++) {
			Rectangle rec = block[j].bounds();

			if (player.intersects(rec)) {

				//TOP COLLISION
				if ((user.yPos() + user.objHeight() / 5) < block[j].yPos()) {
					user.setYPos(block[j].yPos() - user.objHeight() + 1);
					stopVertical();
				}
				// LEFT COLLISION
				if ((user.yPos() > block[j].yPos()) && (user.xPos() < block[j].xPos())) {
					stopHorizontal();
					block[j].setXPos(user.xPos() + user.objWidth());
					disableright = true;
				} else {
					disableright = false;
				}
				// RIGHT COLLISION
				if ((user.yPos() > block[j].yPos()) && (user.xPos() > block[j].xPos())) {
					stopHorizontal();
					block[j].setXPos(user.xPos() - block[j].width);
					disableleft = true;
				} else {
					disableleft = false;
				}
			}
		}
	}

	public static void main(String[] args) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		double screensizeX = screenSize.getWidth();
		double screensizeY = screenSize.getHeight();

		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		sandbox sPanel = new sandbox();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(screensizeX, screensizeY);

		frame.setSize(preferredSize);
		contentpane.add(sPanel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
