package freeworld;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
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
import java.util.ArrayList;

import javax.swing.Timer;

import Structures.Block;
import Structures.Map;
import engine.Physics;
import engine.engine;
import player.Bullet;
import player.Player;

import java.awt.Rectangle;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class sandbox extends engine implements ActionListener, MouseMotionListener, KeyListener, MouseListener {

	int xmouse;
	int ymouse;
	int spacing = 0;
	double slopeX;
	double slopeY;
	int shootDelay = 0;
	int cameramoveNum = 0;
	int cameraDelay = 0;
	boolean intersectsVertTop = false;

	ArrayList<Bullet> bullets = new ArrayList<Bullet>();

	Physics Physics = new Physics();
	Miscellaneous game = new Miscellaneous();
	Player player = new Player(100, 100);
	Map map = new Map();
	Block[] block = new Block[2000];

	Timer time = new Timer(5, this);
	String assetsPath;

	String assetspath;

	public sandbox() {

		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";

		for (int i = 0; i < 50; i++) {
			Bullet bullet1 = new Bullet();
			bullets.add(bullet1);
		}

		for (int i = 0; i < block.length; i++) {
			Block block1 = new Block();
			block[i] = block1;

			block[i].x = randomInt(-18000, 18000);
			block[i].y = randomInt(-10000, 10000);
		}

		time.start();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public void actionPerformed(ActionEvent e) {
		keepWorldMoving();
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).move();
		}
		camerashake();
		contain();
		slopeCalc();
		Collision();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		map.drawBackground(g);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).draw(g);
		}
		addText(g, "Bullets: " + (bullets.size() - game.bulletNum), 100, 100, 50);
		player.draw(g);
	}

	public void slopeCalc() {

		slopeX = xmouse - (player.x + player.width / 2);
		slopeY = ymouse - (player.y + player.height / 2);
		if ((game.bulletNum < bullets.size() - 1) && (bullets.get(game.bulletNum).click)) {
			game.bulletNum++;
		}
		if (bullets.size() > 0) {
			bullets.get(game.bulletNum).additionalSpeedX = slopeX / 10;
			bullets.get(game.bulletNum).additionalSpeedY = slopeY / 10;
		}
	}

	public void camerashake() {
//		cameramoveNum++;
//		cameraDelay++;
//		if(cameramoveNum % 5 == 0) {
//			cameraShiftX(5);
//		}
//		
//		if(cameramoveNum % 5 == 0) {
//			cameraShiftX(-5);
//		}
	}
	
	public void cameraShiftX(int x1) {
		player.x += x1;
		map.x += x1;
	}

	public void cameraShiftY(int y1) {
		player.y += y1;
		map.y += y1;
	}

	public void contain() {
		if (player.y > 500) {
			cameraShiftY(-2);
		}

		for (int i = 0; i < bullets.size(); i++) {
			if (!bullets.get(i).fire) {
				bullets.get(i).x = player.x + player.width / 2;
				bullets.get(i).y = player.y + player.height / 2;
			}
		}
	}

	public void blocksDraw(Graphics g) {
		for (int i = 0; i < block.length; i++) {
			block[i].draw(g);
		}
	}

	public void keepWorldMoving() {
		movePlayerHorizontal(player.speedx);
		movePlayerVertical(player.speedy);
	}

	public void movePlayerHorizontal(double speed) {
		map.applyForceHorizontal(speed);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).applyForceHorizontal(speed);
		}
	}

	public void movePlayerVertical(double speed2) {
		map.applyForceVertical(speed2);
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).applyForceVertical(speed2);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_W && !intersectsVertTop) {
			for (int j = 0; j < bullets.size(); j++) {
				bullets.get(j).forceVertical = true;
			}
			map.forceVertical = true;
			player.speedy = player.speedY;
		}
		if (i == KeyEvent.VK_A) {
			for (int j = 0; j < bullets.size(); j++) {
				bullets.get(j).forceHorizontal = true;
			}
			map.forceHorizontal = true;
			player.speedx = player.speedX;
			player.flip = true;
		}
		if (i == KeyEvent.VK_S) {
			intersectsVertTop = false;
			for (int j = 0; j < bullets.size(); j++) {
				bullets.get(j).forceVertical = true;
			}
			map.forceVertical = true;
			player.speedy = -player.speedY;
		}

		if (i == KeyEvent.VK_D) {
			for (int j = 0; j < bullets.size(); j++) {
				bullets.get(j).forceHorizontal = true;
			}
			map.forceHorizontal = true;
			player.speedx = -player.speedX;

			player.flip = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_W) {
			for (int j = 0; j < bullets.size(); j++) {
				bullets.get(j).forceVertical = false;
			}
			map.forceVertical = false;
		}
		if (i == KeyEvent.VK_S) {
			for (int j = 0; j < bullets.size(); j++) {
				bullets.get(j).forceVertical = false;
			}
			map.forceVertical = false;
		}
		if (i == KeyEvent.VK_A) {
			for (int j = 0; j < bullets.size(); j++) {
				bullets.get(j).forceHorizontal = false;
			}
			map.forceHorizontal = false;
		}
		if (i == KeyEvent.VK_D) {
			for (int j = 0; j < block.length; j++) {
				block[j].forceHorizontal = false;
			}
			for (int j = 0; j < bullets.size(); j++) {
				bullets.get(j).forceHorizontal = false;
			}
			map.forceHorizontal = false;
		}
	}

	public void Collision() {

		Rectangle playerRec = player.bounds();
		Rectangle a = map.spawnBorderVertTop();
		Rectangle b = map.mapHorizontalTop1();
		Rectangle c = map.mapHorizontalTop2();
		Rectangle d = map.mapHorizontalTop3();
		Rectangle e = map.mapHorizontalTop4();

		if (playerRec.intersects(a) || playerRec.intersects(b) || playerRec.intersects(c) || playerRec.intersects(d)
				|| playerRec.intersects(e)) {
			player.speedy = 0;
			player.y += 2;
			intersectsVertTop = true;
		} else {
			intersectsVertTop = false;
		}
	}

	public void mouseDragged(MouseEvent e) {

		shootDelay++;
		if (shootDelay == 90000000) {
			shootDelay = 0;
		}
		if (shootDelay % 20 == 0) {
			bullets.get(game.bulletNum).fire = true;
			bullets.get(game.bulletNum).click = true;
		}
	}

	public void mouseMoved(MouseEvent e) {

		xmouse = e.getX() - 10;
		ymouse = e.getY() - 10;

		slopeX = xmouse - (player.x + player.width / 2);
		slopeY = ymouse - (player.y + player.height / 2);

	}

	public void mouseClicked(MouseEvent arg0) {
		bullets.get(game.bulletNum).fire = true;
		bullets.get(game.bulletNum).click = true;
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

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
