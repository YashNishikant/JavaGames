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
import java.awt.Toolkit;

public class sandbox extends JPanel implements KeyListener, ActionListener {

	int spacing = 0;
	double jumpStrength;
	boolean disableleft;
	boolean disableright;
	int cameraSpeed = 1;

	Platform[] block = new Platform[50];
	Player user = new Player(50, 50);

	String assetsPath;

	Timer t = new Timer (5, this);
	
	public sandbox() {

		int x = randomInt(1, 2);
		for (int i = 0; i < block.length; i++) {
			Platform block1 = new Platform(spacing, 1000);
			block[i] = block1;
			spacing += randomInt(800,900);
		}
		for (int i = 1; i < block.length; i++) {
			if (x == 1) {
				block[i].setYPos(block[i - 1].yPos() + randomInt(40, 150));
			} else {
				block[i].setYPos(block[i - 1].yPos() - randomInt(40, 150));
			}

			x = randomInt(1, 2);

		}

		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		t.start();
	}

	public void actionPerformed(ActionEvent e) { 
		for(int i = 0; i < block.length; i++) {
			block[i].Update();
		}
		cameraPosition();
		Collision();
		repaint();
	}

	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		Color c = new Color(224,255,255);
		Color c2 = new Color(255,255,255);
		g.setColor(c);
		g.fillRect(0, 0, 2000, 2000);
		drawBlocks(g);
		user.draw(g);
	}

	public void cameraPosition() {
		if ((int) user.yPos() > user.constantY) {
			for (int i = 0; i < block.length; i++) {
				block[i].setYPos(block[i].yPos() - cameraSpeed);
			}
			user.setYPos(user.yPos() - cameraSpeed);
		}
		if ((int) user.yPos() < user.constantY) {

			for (int i = 0; i < block.length; i++) {
				block[i].setYPos(block[i].yPos() + cameraSpeed);
			}
			user.setYPos(user.yPos() + cameraSpeed);
		}
	}

	public void drawBlocks(Graphics g) {

		for (int i = 0; i < block.length; i++) {
			if (block[i].xPos() > 0 - block[i].width || block[i].xPos() < 1920) {
				block[i].draw(g);
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
		
		if (i == KeyEvent.VK_SPACE && user.getGrounded()) {
			playerjump();
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
		
		user.setGrounded(false);
		
		for (int j = 0; j < block.length; j++) {
			Rectangle rec = block[j].bounds();

			if (player.intersects(rec)) {

				//TOP COLLISION
				if ((user.yPos() + user.playerHeight() / 5) < block[j].yPos()) {
					user.setYPos(block[j].yPos() - user.playerHeight() + 1);
					stopVertical();
					user.setGrounded(true);
				}
				// LEFT COLLISION
				if ((user.yPos() > block[j].yPos()) && (user.xPos() < block[j].xPos())) {
					stopHorizontal();
					block[j].setXPos(user.xPos() + user.playerWidth());
					disableright = true;
				}
				else {
					disableright = false;
				}
				// RIGHT COLLISION
				if ((user.yPos() > block[j].yPos()) && (user.xPos() > block[j].xPos())) {
					stopHorizontal();
					block[j].setXPos(user.xPos() - block[j].width);
					disableleft = true;
				}
				else {
					disableleft = false;
				}
			}
		}
	}
	 public int randomInt(int a, int b) {
		 int random = (int) ((Math.random() * (b - a + 1)) + a);
		return random;
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