
import java.awt.Graphics;

public class Map extends Textures{

	private int x;
	private int y;
	
	String assetsPath = System.getProperty("user.dir") + "\\src\\assets\\";
	
	public Map(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
