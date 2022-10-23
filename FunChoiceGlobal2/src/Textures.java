import java.awt.Font;    
import java.awt.Graphics;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;

public class Textures extends JPanel{

	String assetsPath;
	
	public Textures() {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\res\\assets\\";
	}

	public void addText(Graphics g, String text, int x ,int y, int size) {
		g.setFont(new Font("default", Font.BOLD, size));
		g.drawString(text, x, y);
	}
	
}

