import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Main extends JPanel implements ActionListener, KeyListener {

	int y;
	int y2;
	
	public Main() {

		Timer time = new Timer(5, this);
		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(10, 10 + y, 50, 150);
		
		g.setColor(Color.BLUE);
		g.fillRect(1860, 10 + y2, 50, 150);
		
		g.setColor(Color.RED);
		g.fillOval(500, 500, 50, 50);
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();
		
		if(i == KeyEvent.VK_W) {
			y -= 50;
		}
		if(i == KeyEvent.VK_S) {
			y += 50;
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public static void main(String[] args) {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		Main Panel = new Main();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(800, 600);

		frame.setSize(preferredSize);
		contentpane.add(Panel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
