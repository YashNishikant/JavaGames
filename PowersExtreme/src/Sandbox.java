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

public class Sandbox extends JPanel implements KeyListener, ActionListener {

	int spacing = 0;
	double jumpStrength;
	boolean disableleft;
	boolean disableright;
	int cameraSpeed = 1;

	Platform block = new Platform(0, 1000);
	Player user = new Player(25, 50);

	String assetsPath;

	Timer t = new Timer(5, this);

	public Sandbox() {

		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		t.start();
	}

	public void actionPerformed(ActionEvent e) {

		block.Update();
		Collision();
		cameraRePos();
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color c = new Color(224, 255, 255);
		Color c2 = new Color(255, 255, 255);
		g.setColor(c);
		g.fillRect(0, 0, 2000, 2000);
		block.draw(g);
		user.draw(g);
	}

	public void cameraRePos() {
		if ((int) user.yPos() > user.constantY) {
			block.setYPos(block.yPos() - cameraSpeed);
			user.setYPos(user.yPos() - cameraSpeed);
		}
		if ((int) user.yPos() < user.constantY) {
				block.setYPos(block.yPos() + cameraSpeed);
				user.setYPos(user.yPos() + cameraSpeed);
		}
	}

	public void movePlayerL() {

	}

	public void movePlayerR() {

	}

	public void stopVertical() {
		block.FreezeVertical();
	}

	public void playerjump() {
		block.Jump();
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
		}
		if (i == KeyEvent.VK_A) {
		}
	}

	public void Collision() {

		Rectangle player = user.bounds();

		user.setGrounded(false);

		Rectangle rec = block.bounds();

		if (player.intersects(rec)) {
			if ((user.yPos() + user.playerHeight() / 5) < block.yPos()) {
				user.setYPos(block.yPos() - user.playerHeight() + 1);
				stopVertical();
				user.setGrounded(true);
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
		Sandbox sPanel = new Sandbox();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(800, 800);

		frame.setSize(preferredSize);
		contentpane.add(sPanel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
