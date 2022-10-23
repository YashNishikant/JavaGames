public class Tortoise implements Racer {
	
	private int position;
	private int speed;

	public Tortoise() {
		position = 0;
		speed = (int) (Math.random() * 3) + 2;
	}

	public int getPosition() {
		return position;
	}

	public void move() {
		position += speed;
		if (position > 100)
			position = 100;
	}
}
