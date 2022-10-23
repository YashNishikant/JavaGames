package newworld;

import java.awt.Color;   

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class world {

	public class panelSetup extends JPanel implements ActionListener, KeyListener {

		boolean right2 = true;
		boolean right = true;

		int x = 210, speedX = 0, y = 885, speedY = 0, a = 100, b = 700, c = 5, d = 25, e = 1100, f = 885;
		
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
				
			
			JFrame frame= new JFrame();	
			frame.getContentPane().add(new worldPanel());
			frame.setSize(300, 300);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);		
			
			
			
			g.setColor(Color.black);
			
			g.fillRect(x + 5, y + 16, 5, 5);
			
			g.setColor(Color.black);
			
			g.fillRect(x + 20, y + 16, 5, 5);
			
			g.setColor(Color.gray);
			
			g.fillRect(x + 5, y + 61, 5, 25);
			
			g.setColor(Color.gray);
			
			g.fillRect(x + 19, y + 61, 5, 25);
			
			//left arm
			g.setColor(Color.gray);
			
			g.fillRect(x + 26, y - 30 + 60, c, d);
			
			//right arm
			g.setColor(Color.gray);
			
			g.fillRect(x - 2, y - 30 + 60, 5, 25);
			
			
			
			if (a == 100) {
				
			right = true;
			}
			
			if (b == 700) {
				
			right2 = true;
			}
			
			if (right = true) {
				
			a = a + 1;
			}
			
			if (right2 = true) {
				
			b = b + 1;
			}
			
			g.setColor(Color.white);
			g.fillRect(a, 100, 140, 30);
			
			if(a == 2000) {
				
			a = -900;	
			}
		
			
			g.fillRect(b, 70, 90, 20);
			
			if(b == 3000) {
				
			b = -900;	
			}
		
			if(x > e - 30)
		
			x = e - 30;	
			
		}
		

		public void actionPerformed(ActionEvent e) {
			
			if (x < 0) 
			{
			speedX = 0;	
			x = 0;
			
			}
			
			
			x = x + speedX;

			if (y > 885) 
			
			{
			
			speedY = 0;	
			y = 885;
			
			}
			
			if (y < 0) 
			
			{
			
			speedY = 0;	
			y = 0;
			
			}
			
			y = y + speedY;
			
			repaint();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			int i = e.getKeyCode();

			if (i == KeyEvent.VK_W) {

				speedY = -2;
			}
		
			
			if (i == KeyEvent.VK_D) {

				speedX = 2;
			}

			if (i == KeyEvent.VK_A) {

				speedX = -2;
			}
		
			if (i == KeyEvent.VK_S) {

				speedY = 2;
			}
		
			if (i == KeyEvent.VK_V) {

			c = 25;
			d = 5;
						
			}
		
		}
		
		@Override
		public void keyTyped(KeyEvent e) {}
		
		@Override
		public void keyReleased(KeyEvent e) {
		
		speedX = 0;
		speedY = 0;
		
		d = 25;
		c = 5;
		
		}
	}
	
}
