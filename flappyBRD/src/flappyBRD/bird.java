
package flappyBRD;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import java.awt.Rectangle;

@SuppressWarnings("serial")

public class bird extends JPanel implements ActionListener, KeyListener {

	int point = 0;
	int separateTube = 0;
	int tubeNumber = 0;

	boolean canNoLongerMove = false;
	boolean LOSTSCREEN = false;
	boolean STARTSCREEN = true;

	Timer time = new Timer(5, this);
	clouds cloud = new clouds();
	tube[] t = new tube[100];
	birdAnimal bird = new birdAnimal();

	public bird() {
		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

		for (int i = 0; i < t.length; i++) {
			tube t1 = new tube(1400, (int) (Math.random() * 500) + -1500, separateTube);
			t[i] = t1;
			separateTube += 800;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.CYAN);
		g.fillRect(0, 0, 2000, 2000);
		cloud.draw(g);

		for (int i = 0; i < t.length; i++) {
				t[i].draw(g);
		}

		Color darkerGreen = new Color(21, 181, 45);
		g.setColor(darkerGreen);
		g.fillRect(0, 900, 2000, 2000);

		bird.draw(g);

		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 40));
		g.drawString("" + point, 930, 150);

		if (LOSTSCREEN) {

			g.setColor(Color.YELLOW);
			g.fillRect(700, 300, 450, 250);
			g.setColor(Color.ORANGE);
			g.fillRect(720, 320, 410, 210);

			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 50));
			g.drawString("SCORE: " + point, 790, 400);

			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 25));
			g.drawString("Press P To Play Again", 790, 500);

		}

		if (STARTSCREEN) {

			for (int i = 0; i < t.length; i++) {
				t[i].speed = 0;
			}

			bird.speed = 0;

			g.setColor(Color.BLACK);
			g.fillRect(780, 300, 100, 100);
			g.setColor(Color.WHITE);
			g.fillRect(790, 310, 80, 80);

			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 55));
			g.drawString("W", 805, 370);
		}
	}

	public void hitFloorOrCeiling() {

		if (bird.birdY >= 865 || bird.birdY <= -5) {
			LOSTSCREEN = true;
			canNoLongerMove = true;
			for (int i = 0; i < t.length; i++) {
				t[i].speed = 0;
			}
			bird.speed = 0;
		}
	}

	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < t.length; i++) {
			t[i].move();
		}
		cloud.move();
		hitFloorOrCeiling();
		bird.move();
		Collision();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_W && !bird.jump && !canNoLongerMove) {
			bird.jump = true;
		}

		if (i == KeyEvent.VK_W && STARTSCREEN) {
			STARTSCREEN = false;
			for (int j = 0; j < t.length; j++) {
				t[j].speed = -8;
			}
			bird.jump = true;
		}

		if (i == KeyEvent.VK_P && LOSTSCREEN && canNoLongerMove) {
			bird.jump = true;
			LOSTSCREEN = false;
			canNoLongerMove = false;

			for (int j = 0; j < t.length; j++) {
				separateTube = 0;
				separateTube += 800;
				t[j].tubeX = separateTube + 1000;
				bird.birdY = 300;
				point = 0;
				t[j].speed = -8;
				t[j].boundaryY = 0;
				t[j].shuffle();

			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public void Collision() {
		Rectangle birdREC = bird.bounds();

		for (int i = 0; i < t.length; i++) {

			tubeNumber = i;

			Rectangle topREC = t[i].boundsTOP();
			Rectangle bottomREC = t[i].boundsBOTTOM();
			Rectangle GAP = t[i].GAP();

			if (birdREC.intersects(GAP)) {
				point++;
				t[tubeNumber].boundaryY = -1000000;
			}

			if (birdREC.intersects(topREC)) {
				for (int j = 0; j < t.length; j++) {
					t[j].speed = 0;
					t[j].collide = true;
				}
				bird.jump = false;
				canNoLongerMove = true;
				LOSTSCREEN = true;
			}

			if (birdREC.intersects(bottomREC)) {
				for (int j = 0; j < t.length; j++) {
					t[j].speed = 0;
					t[j].collide = true;
				}
				bird.jump = false;
				canNoLongerMove = true;
				LOSTSCREEN = true;
			}

		}
	}
}
