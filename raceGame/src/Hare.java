public class Hare implements Racer {
	private int position;

	public Hare() {
		position = 0;
	}

	public int getPosition() {
		return position;
	}

	public void move() {
		int t = (int) (Math.random() * 3);
		if (t == 0)
			position += 10;
		else
			System.out.print("Hare - Rests: ");
	}
}
