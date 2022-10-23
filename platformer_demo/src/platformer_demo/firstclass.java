package platformer_demo;

import java.awt.event.* ;   
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;

public class firstclass extends JPanel implements KeyListener, ActionListener {

	Timer time = new Timer(5, this);
	
	public firstclass() {
		setFocusable(true);
		addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
		time.start();
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.RED);
		g.fillOval(500,500, 100,100);
		

		g.setColor(Color.blue);
		g.fillRect(800,800, 100,100);
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Container contentpane = frame.getContentPane();
		firstclass sPanel = new firstclass();
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(800, 800);
		frame.setSize(preferredSize);
		contentpane.add(sPanel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
