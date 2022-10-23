package scenes;
import java.awt.Graphics; 

import engine.Textures;

public class BuildingScene extends Textures{

	public boolean entered;
	String assetsPath;
	
	
	public BuildingScene() {
		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
	}

	public void draw(Graphics g) {
		if (entered) {
			addImage(g, "//Backgrounds//buildinginterior.png", 0, 0);
		}
	}
}
