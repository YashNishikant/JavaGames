package freeworld;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import javax.swing.Timer;
import Item.Gem;
import Item.clicker;
import Structures.AirBlock;
import Structures.GrassBlock;
import Structures.JumpPlatform;
import Structures.Platform;
import Structures.StoneBlock;
import Structures.WoodFloor;
import engine.engine;
import engine.mouseClicker;
import Entity.Player;
import Entity.Zombie;

import java.awt.Rectangle;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class sandbox extends engine implements ActionListener, KeyListener, MouseListener, MouseMotionListener,
		ComponentListener, MouseWheelListener {

	static JFrame frame = new JFrame();
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double screensizeX = screenSize.getWidth();
	static double screensizeY = screenSize.getHeight();
	double windowSizeX = frame.getWidth();
	double windowSizeY = frame.getHeight();

	int mapX = 40;
	int mapY = 100;
	int dirtHeight = 20;
	int airHeight = 0;
	int terrainStartLvl = 10;
	double camSpeedDevMode = 0;
	boolean devMode = true;
	int randomOre = 0;
	
	mouseClicker click = new mouseClicker();
	Platform[][] blocks = new Platform[mapX * 2][mapY * 2];
	Miscellaneous game = new Miscellaneous();
	// ========================================================================
	Player user = new Player(800, 200, 50, 73);
	//Zombie z = new Zombie(400, 200, 50, 73);
	// ========================================================================
	Timer time = new Timer(5, this);
	String assetsPath;
	ArrayList<Gem> Gems = new ArrayList<Gem>();
	clicker Clicker = new clicker();
	JumpPlatform ju1 = new JumpPlatform(0, 0);
	Platform block1 = new Platform(10, 10);
	GrassBlock gb1;
	AirBlock air1;
	StoneBlock sb1;
	Gem g1;
	WoodFloor wf;

	public sandbox() {

		if (devMode) {
			user.x = -100000;
			user.y = -100000;
		}

		mapBuilding(mapX, mapY, 500, 400, 1);
		blockFiller();
		terrainCheck();

		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addMouseListener(this);
		addMouseMotionListener(this);
		addComponentListener(this);
		addMouseWheelListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		if (!devMode) {
			moveforceHorizontal_ACTION_PERFORMED_ONLY(user.speed);
		} else {
			moveforceHorizontal_ACTION_PERFORMED_ONLY(user.speed * 3);
		}
		if (!devMode) {
			gameGravity();
			cameraFollowPlayer();
		}
		ItemGravity();
		moveBlocksSideDevMode(camSpeedDevMode);
		Collision();
		misc();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		game.drawBackground(g2d, g);
		drawGems(g);
		drawBlocks(g2d, g);
		user.draw(g2d, g);
	}

	public void gameGravity() {

		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				if (!game.onground) {
					blocks[i][j].gravityInverse(-DOWNWARD_FORCE);
				}
				blocks[i][j].applyForceVerticalDOWN(game.jumpStrength);

				if (game.fallingFactorReset) {
					blocks[i][j].fallingFactor = 0;
				}
			}
		}
	}

//	public void blockDissapear() {
//		blocks.get(blockIndex).stopCollisionLeft(true);
//		blocks.get(blockIndex).stopCollisionRight(true);
//		blocks.get(blockIndex).stopCollisionTop(true);
//		blocks.get(blockIndex).stopCollisionBottom(true);
//		blocks.get(blockIndex).setSolid(false);
//	}

	public void terrainCheck() {

		for (int i = 1; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {

				if (blocks[i][j].getSolid() && blocks[i - 1][j].getSolid()) {
					blocks[i][j].setDirtTexture(true);
					blocks[i][j].stopCollisionTop(true);
				}
				if (j >= 1) {
					if (blocks[i][j].getSolid() && blocks[i][j - 1].getSolid()) {
						blocks[i][j].stopCollisionLeft(true);
					}
				}
				if (j < mapY - 1) {
					if (blocks[i][j].getSolid() && blocks[i][j + 1].getSolid()) {
						blocks[i][j].stopCollisionRight(true);
					}
				}
			}
		}

//			if (blocks.get(i).getSolid()) {
//				if ((!blocks.get(i - map.getLineSpace()).getSolid())) {
//					blocks.get(i).setDirtTexture(false);
//					blocks.get(i).stopCollisionTop(false);
//				}
//
//				if ((!blocks.get(i + 1).getSolid())) {
//					blocks.get(i).stopCollisionRight(false);
//				}
//
//				if ((!blocks.get(i - 1).getSolid())) {
//					blocks.get(i).stopCollisionLeft(false);
//				}
//
//				if ((!blocks.get(i + map.getLineSpace()).getSolid())) {
//					blocks.get(i).stopCollisionBottom(false);
//				}
//
//				if ((blocks.get(i + map.getLineSpace()).getSolid())) {
//					blocks.get(i + map.getLineSpace()).setDirtTexture(true);
//					blocks.get(i + map.getLineSpace()).stopCollisionTop(true);
//				}
//			}
//		}
//		for (int i = 0; i < blocks.size() - 1; i++) {
//			if (blocks.get(i).getSolid() && (blocks.get(i + 1).getSolid())) {
//				blocks.get(i + 1).stopCollisionLeft(true);
//				blocks.get(i).stopCollisionRight(true);
//			}
	}

	public void blockFiller() {
		for (int i = 1; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				while (blocks[i][j].getTag().equals("air") && blocks[i - 1][j].getTag().equals("grass")) {
					gb1 = new GrassBlock(blocks[i][j].x, blocks[i][j].y);
					blocks[i][j] = gb1;
				}
			}
		}
	}

	public void moveBlocksSideDevMode(double x) {
		for (int j = 0; j < mapX; j++) {
			for (int k = 0; k < mapY; k++) {

				blocks[j][k].y += x;

			}
		}
	}

	public void cameraFollowPlayer() {
		if (user.x > 800) {
			user.x -= 5;

			for (int i = 0; i < mapX; i++) {
				for (int j = 0; j < mapY; j++) {
					blocks[i][j].x -= user.speed;

					if (user.x < 800) {

						blocks[i][j].x += user.speed;

						user.x += 5;
					}

					if (user.y > 400) {
						user.y -= 5;

						blocks[i][j].y -= user.speed;

					}
					if (user.y < 400) {
						blocks[i][j].y += user.speed;

						user.y += 5;
					}
				}
			}
		}
	}

	public void misc() {

		
	}

	public void drawGems(Graphics g) {
		for (Gem g1 : Gems) {
			g1.draw(g);
		}
	}

	public void ItemGravity() {

		for (Gem g : Gems) {
			g.gravity(DOWNWARD_FORCE);
			g.applyForceVerticalDOWN(game.jumpStrength);
		}
	}

	public void drawBlocks(Graphics2D g2d, Graphics g) {
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				if ((blocks[i][j].x > -block1.width && blocks[i][j].x < 1920)
						&& (blocks[i][j].y < screensizeY && blocks[i][j].y > -block1.height)) {

					if ((blocks[i][j].x > 0 - blocks[i][j].width || blocks[i][j].x < 1920) && blocks[i][j].getSolid()) {
						blocks[i][j].draw(g2d, g);
					}
				}
			}
		}
	}

	public void moveforceHorizontal_ACTION_PERFORMED_ONLY(double speed) {
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				blocks[i][j].applyForceHorizontal(speed);
			}
		}
	}

	public void movePlayerLeft(double speed) {
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				blocks[i][j].forceHorizontalR = true;
			}
			for (int j = 0; j < Gems.size(); j++) {
				Gems.get(j).forceHorizontalR = true;
			}
			user.setFlip(false);
		}
	}

	public void movePlayerRight(double speed) {
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				blocks[i][j].forceHorizontalL = true;
			}
			for (int j = 0; j < Gems.size(); j++) {
				Gems.get(j).forceHorizontalL = true;
			}
			user.setFlip(true);
		}
	}

	public void stopHorizontalR() {
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				blocks[i][j].forceHorizontalR = false;
			}
			for (int j = 0; j < Gems.size(); j++) {
				Gems.get(j).forceHorizontalR = false;
			}
		}
	}

	public void stopHorizontalL() {
		for (int i = 0; i < mapX; i++) {
			for (int j = 0; j < mapY; j++) {
				blocks[i][j].forceHorizontalL = false;
			}
			for (int j = 0; j < Gems.size(); j++) {
				Gems.get(j).forceHorizontalL = false;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_SPACE && game.onground) {
			game.jumpStrength = 10;
			for (int j = 0; j < mapX; j++) {
				for (int k = 0; k < mapY; k++) {
					blocks[j][k].forceUp = true;
					blocks[j][k].setForce = true;
				}
			}
		}

		if (i == KeyEvent.VK_A && !game.disableleft) {
			movePlayerLeft(user.speed);
		}
		if (i == KeyEvent.VK_D && !game.disableright) {
			movePlayerRight(user.speed);
		}

		if (devMode) {
			if (i == KeyEvent.VK_S) {
				camSpeedDevMode = 2 * -user.speed;
			}
			if (i == KeyEvent.VK_W) {
				camSpeedDevMode = 2 * user.speed;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();
		if (i == KeyEvent.VK_D) {
			stopHorizontalL();
		}
		if (i == KeyEvent.VK_A) {
			stopHorizontalR();
		}

		if (devMode) {
			if (i == KeyEvent.VK_S) {
				camSpeedDevMode = 0;
			}
			if (i == KeyEvent.VK_W) {
				camSpeedDevMode = 0;
			}
		}

	}

	public void gemSpawner(int start, int spacing, int amount) {
		game.spacingX = start;
		for (int i = 0; i < amount; i++) {
			g1 = new Gem(user.x + game.spacingX, user.y);
			Gems.add(g1);
			game.spacingX += spacing;

			if (game.randomColor == 1) {
				Gems.get(i).setGem(true);
			} else {
				Gems.get(i).setGem(false);
			}
			game.randomColor = randomInt(1, 2);
		}
	}

	public void mapBuilding(int x1, int y1, int startingx, int startingy, int terrainRoughness) {
		game.spacingX = startingx;
		game.spacingY = startingy;

		for (int i = 0; i < x1; i++) {
			for (int j = 0; j < y1; j++) {
				airHeight = randomInt(terrainStartLvl, terrainStartLvl + terrainRoughness);
				
				if (i < airHeight) {
					air1 = new AirBlock(game.spacingX, game.spacingY);
					blocks[i][j] = air1;
					game.spacingX += block1.width;
				} else if (i < dirtHeight) {
					gb1 = new GrassBlock(game.spacingX, game.spacingY);
					blocks[i][j] = gb1;
					game.spacingX += block1.width;
				} else {
					sb1 = new StoneBlock(game.spacingX, game.spacingY);
					blocks[i][j] = sb1;
					game.spacingX += block1.width;

				}
			
				if(i > 25 && randomOre == 1) {
					blocks[i][j].setOre(true);
				}
				
				randomOre = randomInt(1,50);
			}
			game.spacingX = startingx;
			game.spacingY += 40;
		}
	}

	public void Collision() {
		Rectangle clicking = Clicker.bounds();
		Rectangle player = user.bounds();

		for (int j = 0; j < mapX; j++) {
			for (int k = 0; k < mapY; k++) {
				blocks[j][k].setForce = false;
			}
		}

		game.onground = false;
		game.fallingFactorReset = false;
		game.disableleft = false;
		game.disableright = false;

		for (int x = 0; x < mapX; x++) {
			for (int y = 0; y < mapY; y++) {
				Rectangle recTop = blocks[x][y].bounds1();
				Rectangle recLeft = blocks[x][y].bounds2();
				Rectangle recRight = blocks[x][y].bounds3();
				Rectangle recBottom = blocks[x][y].bounds4();
				Rectangle rec = blocks[x][y].bounds();

				for (int k = 0; k < Gems.size(); k++) {
					Rectangle collectable = Gems.get(k).bounds();

					if (player.intersects(collectable)) {
						Gems.remove(k);
						if (k > 0) {
							k--;
						}
					}

					// TOP COLLISION COIN
					if (collectable.intersects(recTop) && Gems.size() > 0) {
						Gems.get(k).fallingFactor = 0;
						Gems.get(k).gravityActivate = false;
						Gems.get(k).y = blocks[x][y].y - Gems.get(k).getHeight();
					}

				}


				// TOP COLLISION
				if (player.intersects(recTop)) {
					user.y = blocks[x][y].y - user.height + 1;
					game.fallingFactorReset = true;
					game.onground = true;
				}

				// LEFT COLLISION
				if (player.intersects(recLeft)) {
					user.x = blocks[x][y].x - user.width;
					game.disableright = true;
				}

				// RIGHT COLLISION
				if (player.intersects(recRight)) {
					user.x = blocks[x][y].x + blocks[x][y].width;
					game.disableleft = true;
				}

				// BOTTOM COLLISION
				if (player.intersects(recBottom)) {
					user.y = blocks[x][y].y + blocks[x][y].height;

					blocks[x][y].stopJump();
				}
			}
		}
	}

	public static void main(String[] args) {

		Container contentpane = frame.getContentPane();
		sandbox sPanel = new sandbox();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(screensizeX / 3, screensizeY / 2);

		frame.setSize(preferredSize);
		contentpane.add(sPanel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		Clicker.setX(e.getX());
		Clicker.setY(e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		user.setSwing(true);
		game.blockBreak = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		game.blockBreak = false;
	}

	@Override
	public void componentHidden(ComponentEvent e) {

	}

	@Override
	public void componentMoved(ComponentEvent e) {

	}

	@Override
	public void componentResized(ComponentEvent e) {
		windowSizeX = frame.getWidth();
		windowSizeY = frame.getHeight();
	}

	@Override
	public void componentShown(ComponentEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}
}
