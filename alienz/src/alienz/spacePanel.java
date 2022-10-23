package alienz;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.concurrent.TimeUnit;

public class spacePanel{

	static GraphicsConfiguration gc;

	public static void main(String[] args) throws InterruptedException {

		JFrame frame = new JFrame(gc);
		
		Container contentpane = frame.getContentPane();
		space sPanel = new space();
		
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);
		
		//newPanel.setSize(preferredSize);
		frame.setSize(preferredSize);
		contentpane.add((JPanel)sPanel);
	
		frame.setVisible(true);
		
		while(true)
		{
			((JPanel) sPanel ).repaint();
			TimeUnit.MILLISECONDS.sleep(1);
		}
	}
}