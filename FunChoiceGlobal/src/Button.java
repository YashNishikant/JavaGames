
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Button extends Textures {

	int x;
	int y;
	boolean canclick = false;
	boolean clicked = false;
	int wh = 20;
	
	String name;
	String capital;
	String language;
	String description;
	String description2;
	
	public Button(String name, String c, String l, int x, int y, String d, String d2) {
		this.x = x;
		this.y = y;
		this.name = name;
		capital = c;
		language = l;
		description = d;
		description2 = d2; 
	}

	public void draw(Graphics g) {
		if (!canclick) {
			g.setColor(Color.GRAY);
			g.fillRect(x-3, y-3, wh+6, wh+6);
			
			g.setColor(Color.BLACK);
			g.fillRect(x, y, wh, wh);
		} else {
			g.setColor(Color.GRAY);
			g.fillRect(x-3, y-3, wh+6, wh+6);
			
			g.setColor(Color.GREEN);
			g.fillRect(x, y, wh, wh);
		}
	}


	public Rectangle bounds() {
		return (new Rectangle(x, y, 30, 30));
	}

}
