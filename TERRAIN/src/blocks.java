import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class blocks {

	int blockspeed;
	int blockX;
	int blockY;
	int block_W_L;
	int s = 0;
	int bx1 = 0;
	int by1 = 0;
	int bx = 0;
	int by = 0;

	boolean place = false;
	boolean blockbreak = false;

	public blocks(int x, int y) {

		bx1 = x + 300;
		by1 = y + 300;

		bx = x;
		by = y;

		blockX = x;
		blockY = y;
		block_W_L = 40;
		blockspeed = 0;
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(blockX, blockY, block_W_L, block_W_L);
	}

	public void move() {

		blockX = blockX + blockspeed;

	}

	public void destroy() {
		if (blockbreak) {
			blockY = -100;
			blockX = -100;
		}
	}

	public Rectangle bounds() {

		return (new Rectangle(blockX, blockY, block_W_L, block_W_L));

	}

}
