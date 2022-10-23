
public class TurboSnail implements Racer {

	private int position;
	private int speed;
	private int boost;

	public TurboSnail() {
		boost = (int) (Math.random() * 15) + 1;
	}

	public int getPosition() {
		return position;
	}

	public void move() {
		boost = (int) (Math.random() * 15) + 1;
		if (boost == 1) {
			position += 20;
			System.out.print("Turbo Snail - BOOSTED: ");
		} else {
			position += 2;
		}
	}
}
