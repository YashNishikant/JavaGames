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

	Platform[] block = new Platform[1];
	Player user = new Player(50, 50);
	Timer t = new Timer (5, this);
	
	public sandbox() {

		int x = randomInt(1, 2);
		for (int i = 0; i < block.length; i++) {
			Platform block1 = new Platform(150, 10);
			block[i] = block1;
		}
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		t.start();
	}

	public void actionPerformed(ActionEvent e) { 
		repaint();
	}

	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		Color c = new Color(224,255,255);
		g.setColor(c);
		g.fillRect(0, 0, 2000, 2000);
		drawBlocks(g);
		user.draw(g);
	}

	public void drawBlocks(Graphics g) {
		for (int i = 0; i < block.length; i++) {
				block[i].draw(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) { 
		int i = e.getKeyCode();

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int i = e.getKeyCode();

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