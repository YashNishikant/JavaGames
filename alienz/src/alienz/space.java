package alienz;

import java.awt.Color; 
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Rectangle;
import java.awt.Font;

public class space extends JPanel implements ActionListener, KeyListener {

	Timer time = new Timer(5, this);
	rectanglee ship = new rectanglee();
	target block = new target();

	int score = 0;
	
	boolean collision = false;
	
	boolean pause = false;
	
	public space() {

		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setFont(new Font("default", Font.BOLD,24));
		g.drawString("Score: " + score, 10, 20);
		
		g.setFont(new Font("default", Font.BOLD,24));
		g.drawString("PRESS P TO PAUSE", 1600, 20);
		
		// block
		g.setColor(Color.red);
		g.fillRect(block.xBlock, block.yBlock, 20, 20);

		// ship main
		g.setColor(Color.gray);
		g.fillRect(ship.xShip, ship.yShip, 20, 20);

			if(pause == true) {
			
			g.setColor(Color.white);
			g.fillRect(0, 0, 20000, 20000);
			
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD,75));
			g.drawString("GAME PAUSED", 660, 300);
			
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD,45));
			g.drawString("Press R to resume", 750, 400);
			
		}
			
	}

	public void actionPerformed(ActionEvent e) {
		
		if (collision == true) {

			block.xBlock = (int) (Math.random() * 1800) + 30;
			block.yBlock = (int) (Math.random() * 900) + 1;

			score++;
			
			collision = false;

		}

		if(ship.xShip < 0) {
			
		ship.speedX = 0;
		ship.xShip = 0;
			
		}
		
		if(ship.xShip > 1870) {
			
		ship.speedX = 0;
		ship.xShip = 1870;
				
		}
		
		if(ship.yShip < 0) {
			
		ship.speedY = 0;
		ship.yShip = 0;
				
		}		
		
		if(ship.yShip > 970) {
			
		ship.speedY = 0;
		ship.yShip = 970;
				
		}	
		
		ship.move();
		collision();
		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_P) {

			ship.speedY = 0;
			ship.speedX = 0;
			pause = true;
		}
		
		if (i == KeyEvent.VK_R) {

			ship.speedY = 0;
			ship.speedX = 0;
			pause = false;
		}
		
		if (i == KeyEvent.VK_W) {

			ship.speedY = -3;
			ship.speedX = 0;

		}

		if (i == KeyEvent.VK_S) {

			ship.speedY = 3;
			ship.speedX = 0;

		}

		if (i == KeyEvent.VK_A) {

			ship.speedX = -3;
			ship.speedY = 0;

		}

		if (i == KeyEvent.VK_D) {

			ship.speedX = 3;
			ship.speedY = 0;

		}
		
		}
		
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		
	}

	public void collision() {

		Rectangle shipRec = ship.bounds();
		Rectangle blockRec = block.bounds();

		if (shipRec.intersects(blockRec)) {

			collision = true;

		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		space sPanel = new space();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(sPanel);

		frame.setVisible(true);
	}

}
