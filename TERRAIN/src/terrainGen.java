import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class terrainGen {

	int chunkX;
	int chunkY;
	int separate;
	int speed;
	int treeCount;
	
	public terrainGen(int x, int y, int z) {
		chunkX = z;
		chunkY = x;	
		separate = y;
		speed = 0;
		treeCount = 0;
	}
	
	public void naturaldrawings(Graphics g) {
		// Ground
		g.setColor(Color.green);
		g.fillRect(chunkX + separate + 15, chunkY, 400, 1000);	
	}
	
	public void move() {
		
		chunkX = chunkX + speed;
		
	}
	
	public Rectangle bounds() {

		return (new Rectangle(chunkX + separate, chunkY, 400, 1000));

	}
	
}
