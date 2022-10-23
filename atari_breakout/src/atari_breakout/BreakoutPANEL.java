package atari_breakout;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class BreakoutPANEL {

	static GraphicsConfiguration gc;

	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame(gc);
		
		Container contentpane = frame.getContentPane();
		breakoutGAME bPanel = new breakoutGAME(true);
		
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);
		
		//newPanel.setSize(preferredSize);
		frame.setSize(preferredSize);
		contentpane.add(bPanel);
	
		frame.setVisible(true);
		
		while(true)
		{
			bPanel.repaint();
			TimeUnit.MILLISECONDS.sleep(1);
		}
	}
	
}
