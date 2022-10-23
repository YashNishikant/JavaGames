package freeworld;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

import Item.Gem;
import Item.clicker;
import Structures.AirBlock;
import Structures.GrassBlock;
import Structures.JumpPlatform;
import Structures.Platform;
import Structures.StoneBlock;
import engine.engine;
import engine.mouseClicker;
import player.Player;

import java.awt.Rectangle;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class sandbox extends engine implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

	BufferedImage knight = addImageG2D("Player.png");
	BufferedImage sword = addImageG2D("Sword.png");
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	static double screensizeX = screenSize.getWidth();
	static double screensizeY = screenSize.getHeight();
	
	int lineSpace = 0;
	boolean fallingFactorReset;
	boolean onGround;
	int spacingX = 0;
	int spacingY = 0;
	boolean onground;
	double jumpStrength;
	boolean disableleft;
	boolean disableright;
	int cameraSpeed = 1;
	int coinnum;
	int randomColor = randomInt(1, 2);
	boolean flip;
	int rotate;
	boolean highlight;
	boolean breakblock = false;
	int rockLevel;
	
	int playerx;
	int playery;
	int blockIndex;
	boolean swing;

	mouseClicker click = new mouseClicker();
	ArrayList<Platform> blocks = new ArrayList<Platform>();

	Miscellaneous game = new Miscellaneous();
	Player user = new Player(50, 73);

	Timer time = new Timer(5, this);
	String assetsPath;
	ArrayList<JumpPlatform> ju = new ArrayList<JumpPlatform>();
	ArrayList<Gem> Gems = new ArrayList<Gem>();

	clicker Clicker = new clicker();

	JumpPlatform ju1 = new JumpPlatform(0, 0);
	Platform block1 = new Platform(10, 10);
	GrassBlock gb1;
	AirBlock air1;
	StoneBlock sb1;
	Gem g1;

	public sandbox() {

		String mapGen = 

						"################################################a" + 
						"################################################a" + 
						".##############################################.a" + 
						".##############################################.a" + 
						".##############################################.a" + 
						".##############################################.a" + 
						".##############################################.a" + 
						".##############################################.a" + 
						".----------#####################################a" + 
						".--##--##---#########...........................a" + 
						".--##--##----########...........................a" + 
						".--##--##-----#######...........................a" + 
						"................................................a$" +
						"................................................a" +
						"................................................a" +
						"................................................a" +
						"................................................a" +
						"................................................a" +
						"................................................a" +
						"................................................a" +
						"................................................a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"------------------------------------------------a" +
						"#################################################";
		
		lineSpace = mapGen.indexOf('a') - mapGen.indexOf('#') + 1;
		for (char ch : mapGen.toCharArray()) {
			if (ch == '#') {
				air1 = new AirBlock(spacingX, spacingY);
				blocks.add(air1);
				spacingX += block1.width;
			}
			if (ch == 'a') {
				air1 = new AirBlock(spacingX, spacingY);
				blocks.add(air1);
				spacingY += block1.height;
				spacingX = 0;
			}
			if (ch == '.') {
				gb1 = new GrassBlock(spacingX, spacingY);
				blocks.add(gb1);
				spacingX += block1.width;
			}

			if (ch == '-') {
				sb1 = new StoneBlock(spacingX, spacingY);
				blocks.add(sb1);
				spacingX += block1.width;
			}
		}

		for (int i = 0; i < blocks.size() - lineSpace; i++) {

			if (blocks.get(i).getSolid() && (blocks.get(i + lineSpace).getSolid())) {
				blocks.get(i + lineSpace).setDirtTexture(true);
				blocks.get(i + lineSpace).stopCollisionTop(true);
			}
		}

		for (int i = 0; i < blocks.size() - 1; i++) {
			if (blocks.get(i).getSolid() && (blocks.get(i + 1).getSolid())) {
				blocks.get(i + 1).stopCollisionLeft(true);
				blocks.get(i).stopCollisionRight(true);
			}
		}

		spacingX = (int) ((blocks.get(0).width));

//		for (int i = 0; i < 20; i++) {
//			randomColor = randomInt(1, 2);
//			g1 = new Gem(100 + spacingX, 100);
//			spacingX += block1.width;
//			if (randomColor == 1) {
//				g1.setGem(true);
//			} else {
//				g1.setGem(false);
//			}
//			Gems.add(g1);
//
//		}

		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		moveforceHorizontal(user.speed);
		gameGravity();
		ItemGravity();
		Collision();
		contain();
		terrainCheck();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at = setTransformations(playerx, playery);
		game.drawBackground(g);
		drawGems(g);
		drawBlocks(g2d, g);
		user.draw(g);
		PlayerAnimations(g2d, knight, sword, at);
		highlight(g2d, g);
	}
	
	public void highlight(Graphics2D g2d, Graphics g) {
		if(highlight) {
			g.setColor(Color.YELLOW);
			g.fillRect((int)blocks.get(blockIndex).x, (int)blocks.get(blockIndex).y, (int)blocks.get(blockIndex).width, (int)blocks.get(blockIndex).height);
			g.setColor(Color.BLACK);
		}
	}
	
	public void gameGravity() {

		for(Platform b : blocks) {
			if(!onground)
			b.gravityInverse(-DOWNWARD_FORCE);
			b.applyForceVerticalDOWN(jumpStrength);
			
			if(fallingFactorReset) {
				b.fallingFactor = 0;
			}
		}
	
		for(Gem g : Gems) {

			g.gravity(-DOWNWARD_FORCE);
			g.applyForceVerticalDOWN(jumpStrength);
			
			if(fallingFactorReset) {
				g.fallingFactor = 0;
			}
		}
		
	}
	
	public void terrainCheck() {
		for (int i = 0; i < blocks.size() - lineSpace; i++) {
			
			if (blocks.get(i).getSolid() && (!blocks.get(i - lineSpace).getSolid())) {
				blocks.get(i).setDirtTexture(false);
				blocks.get(i).stopCollisionTop(false);
			}
			
			if (blocks.get(i).getSolid() && (!blocks.get(i + 1).getSolid())) {
				blocks.get(i).stopCollisionRight(false);
			}
			
			if (blocks.get(i).getSolid() && (!blocks.get(i - 1).getSolid())) {
				blocks.get(i).stopCollisionLeft(false);
			}
			
			if (blocks.get(i).getSolid() && (!blocks.get(i + lineSpace).getSolid())) {
				blocks.get(i).stopCollisionBottom(false);
			}
			
			if (blocks.get(i).getSolid() && (blocks.get(i + lineSpace).getSolid())) {
				blocks.get(i + lineSpace).setDirtTexture(true);
				blocks.get(i + lineSpace).stopCollisionTop(true);
			}
		}

		for (int i = 0; i < blocks.size() - 1; i++) {
			if (blocks.get(i).getSolid() && (blocks.get(i + 1).getSolid())) {
				blocks.get(i + 1).stopCollisionLeft(true);
				blocks.get(i).stopCollisionRight(true);
			}
		}
	}
	
	public void contain() {
		
		if(user.x > 800) {
			user.x-=5;
			
			for(Platform b: blocks) {
				b.x-=user.speed;
			}
			for(Gem g: Gems) {
				g.x-=user.speed;
			}
		}
		if(user.x < 800) {
			for(Platform b: blocks) {
				b.x+=user.speed;
			}
			for(Gem g: Gems) {
				g.x+=user.speed;
			}
			user.x+=5;
		}
	
		if(user.y > 400) {
			user.y-=5;
			
			for(Platform b: blocks) {
				b.y-=user.speed;
			}
			for(Gem g: Gems) {
				g.y-=user.speed;
			}
		}
		if(user.y < 400) {
			for(Platform b: blocks) {
				b.y+=user.speed;
			}
			for(Gem g: Gems) {
				g.y+=user.speed;
			}
			user.y+=5;
		}
		
	}
	
	public void PlayerAnimations(Graphics2D g2d, BufferedImage a, BufferedImage b, AffineTransform at) {
		if (flip) {
			user.FlipImage(g2d, a, (int) user.x + 50, (int) user.y, (int) user.width, (int) user.height);
			if (!swing) {
				g2d.drawImage(b, (int) (user.x + user.width) - 15, (int) (user.y) - 15, 25, 75, null);
			}
			playerx = (int) (user.x) + 40;
			playery = (int) (user.y);

		} else {
			g2d.drawImage(a, (int) (user.x), (int) (user.y), (int) user.width, (int) user.height, null);
			if (!swing) {
				g2d.drawImage(b, (int) (user.x) - 15, (int) (user.y) - 15, 25, 75, null);
			}
			playerx = (int) (user.x) - 10;
			playery = (int) (user.y);
		}
		if (swing && !flip) {

			at.rotate(Math.toRadians(-rotate), 25 / 1.8, 75 / 1.8);
			g2d.drawImage(b, at, null);
			rotate += 10;

			if (rotate >= 100) {
				rotate = 0;
				swing = false;
			}
		}

		if (swing && flip) {

			at.rotate(Math.toRadians(-rotate), 25 / 1.8, 75 / 1.8);
			g2d.drawImage(b, at, null);
			rotate -= 10;

			if (rotate <= -100) {
				rotate = 0;
				swing = false;
			}
		}
	}

	public void drawGems(Graphics g) {
		for (Gem g1 : Gems) {
			g1.draw(g);
		}
	}

	public void ItemGravity() {
		for (int i = 0; i < ju.size(); i++) {
			ju.get(i).gravity(DOWNWARD_FORCE);
		}
		for (Gem g : Gems) {
			g.gravity(DOWNWARD_FORCE);
			g.applyForceVerticalDOWN(jumpStrength);
		}
	}

	public void drawBlocks(Graphics2D g2d, Graphics g) {
		for (int i = 0; i < blocks.size(); i++) {
			if ((blocks.get(i).x > 0 - blocks.get(i).width || blocks.get(i).x < 1920) && blocks.get(i).getSolid()) {
				blocks.get(i).draw(g2d, g);
			}
		}
	}

	public void moveforceHorizontal(double speed) {
		user.applyForceHorizontal(speed);
		for (int i = 0; i < blocks.size(); i++) {
			blocks.get(i).applyForceHorizontal(user.speed);
		}
		for (int i = 0; i < Gems.size(); i++) {
			Gems.get(i).applyForceHorizontal(user.speed);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_R) {
			user.x = 100;
			user.y = 100;
		}

		if (i == KeyEvent.VK_SPACE && onground) {
			jumpStrength = 10;
			for(Platform b : blocks) {
			b.forceUp = true;
			b.setForce = true;
			}
		}

		if (i == KeyEvent.VK_A && !disableleft) {
			for (int j = 0; j < blocks.size(); j++) {
				blocks.get(j).forceHorizontalL = true;
			}
			for (int j = 0; j < Gems.size(); j++) {
				Gems.get(j).forceHorizontalL = true;
			}
			flip = false;
		}

		if (i == KeyEvent.VK_D && !disableright) {
			for (int j = 0; j < blocks.size(); j++) {
				blocks.get(j).forceHorizontalR = true;
			}
			for (int j = 0; j < Gems.size(); j++) {
				Gems.get(j).forceHorizontalR = true;
			}
			flip = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();
		if (i == KeyEvent.VK_D) {
			for (int j = 0; j < blocks.size(); j++) {
				blocks.get(j).forceHorizontalR = false;
			}
			for (int j = 0; j < Gems.size(); j++) {
				Gems.get(j).forceHorizontalR = false;
			}
		}
		if (i == KeyEvent.VK_A) {
			for (int j = 0; j < blocks.size(); j++) {
				blocks.get(j).forceHorizontalL = false;
			}
		
			for (int j = 0; j < Gems.size(); j++) {
				Gems.get(j).forceHorizontalL = false;
			}
		}
	}

	public void Collision() {
		Rectangle clicking = Clicker.bounds();
		Rectangle player = user.bounds();
		for (int i = 0; i < ju.size(); i++) {
			Rectangle jumpPlatform = ju.get(i).bounds();

			if (player.intersects(jumpPlatform)) {
				user.fallingFactor = 0;
			}
		}

		for (int j = 0; j < blocks.size(); j++) {
			blocks.get(j).setForce = false;
		}
		for (int i = 0; i < ju.size(); i++) {
			ju.get(i).setForce = false;
		}
		onground = false;
		fallingFactorReset = false;
		highlight = false;
		for (int j = 0; j < blocks.size(); j++) {

			Rectangle recTop = blocks.get(j).bounds1();
			Rectangle recLeft = blocks.get(j).bounds2();
			Rectangle recRight = blocks.get(j).bounds3();
			Rectangle recBottom = blocks.get(j).bounds4();
			Rectangle rec = blocks.get(j).bounds();
			
			if(clicking.intersects(rec)) {
				blocks.get(j).setHighlight(true);
				blockIndex = j;
				if(blocks.get(j).getSolid())
					highlight = true;
				
				if(breakblock) {
					blocks.get(j).stopCollisionLeft(true);
					blocks.get(j).stopCollisionRight(true);
					blocks.get(j).stopCollisionTop(true);
					blocks.get(j).stopCollisionBottom(true);
					blocks.get(j).setSolid(false);
					
				}
				
			}
			else {
				blocks.get(j).setHighlight(false);
			}
			for (int k = 0; k < Gems.size(); k++) {
				Rectangle collectable = Gems.get(k).bounds();

				if (player.intersects(collectable)) {
					Gems.remove(k);
					coinnum++;
				}

				// TOP COLLISION COIN
				if (collectable.intersects(recTop)) {
					//Gems.get(k).y = blocks.get(j).y - Gems.get(k).getHeight() + 2;
					Gems.get(k).fallingFactor = 0;
					Gems.get(k).gravityActivate = false;
				}

			}

			// TOP COLLISION
			if (player.intersects(recTop)) {
				user.y = blocks.get(j).y - user.height + 1;
				fallingFactorReset = true;
				onground = true;
			}
			// LEFT COLLISION
			if (player.intersects(recLeft)) {
				user.x = blocks.get(j).x - user.width;
				disableright = true;
			} else {
				disableright = false;
			}

			// RIGHT COLLISION
			if (player.intersects(recRight)) {
				user.x = blocks.get(j).x + blocks.get(j).width;
				disableleft = true;
			} else {
				disableleft = false;
			}

			// BOTTOM COLLISION
			if (player.intersects(recBottom)) {
				user.y = blocks.get(j).y + blocks.get(j).height;
				user.stopJump();
			}

		}
	}

	public static void main(String[] args) {
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
		swing = true;

		breakblock = true;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// swing = false;
		breakblock = false;
	}
}
