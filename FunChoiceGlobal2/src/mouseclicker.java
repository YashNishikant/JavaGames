import java.awt.Rectangle;
import java.awt.Graphics;

public class mouseclicker extends Textures{

	int x = 0;
	int y = 0;
	int w;
	int h;
	
	public mouseclicker(int w, int h) {
		this.w = w;
		this.h = h;
	}
	
	public void draw(Graphics g) {
		g.fillRect(x, y, w, h);
	}
	
	public Rectangle bounds() {
		return (new Rectangle(x,y,w,h));
	}
	
}
