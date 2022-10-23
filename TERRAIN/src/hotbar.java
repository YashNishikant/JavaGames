import java.awt.Color;
import java.awt.Graphics;
public class hotbar {

	int x;
	int y;
	int y2;
	int LWBOX = 80;
	int LWSMALLBOX = 60;
	public hotbar() {
		
		x = 700;
		y = 920;
		y2 = y + 10;
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(x, y, LWBOX, LWBOX);
		g.setColor(Color.GRAY);
		g.fillRect(x+10, y2, LWSMALLBOX, LWSMALLBOX);
		
		g.setColor(Color.BLACK);
		g.fillRect(x+70, y, LWBOX, LWBOX);
		g.setColor(Color.GRAY);
		g.fillRect(x+80, y2, LWSMALLBOX, LWSMALLBOX);
		
		g.setColor(Color.BLACK);
		g.fillRect(x+135, y, LWBOX, LWBOX);
		g.setColor(Color.GRAY);
		g.fillRect(x+145, y2, LWSMALLBOX, LWSMALLBOX);
		
		g.setColor(Color.BLACK);
		g.fillRect(x+200, y, LWBOX, LWBOX);
		g.setColor(Color.GRAY);
		g.fillRect(x+210, y2, LWSMALLBOX, LWSMALLBOX);
		
		g.setColor(Color.BLACK);
		g.fillRect(x+265, y, LWBOX, LWBOX);
		g.setColor(Color.GRAY);
		g.fillRect(x+275, y2, LWSMALLBOX, LWSMALLBOX);
		
		g.setColor(Color.BLACK);
		g.fillRect(x+330, y, LWBOX, LWBOX);
		g.setColor(Color.GRAY);
		g.fillRect(x+340, y2, LWSMALLBOX, LWSMALLBOX);
	}
}
