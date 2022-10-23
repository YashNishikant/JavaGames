import java.util.ArrayList;

public class Race {
	
	private int raceNum = 100;
	
	public Race() {

		Racer t = new Tortoise();
		Racer h = new Hare();
		Racer TS = new TurboSnail();
		ArrayList<Racer> racers = new ArrayList<Racer>();
		racers.add(t);
		racers.add(h);
		racers.add(TS);

		boolean race = true;
		System.out.println("Race to " + raceNum + "!");
		divide();
		while (race) {
			for (Racer r : racers) {
				r.move();
				System.out.println(r.getClass().getName() + ": " + r.getPosition());
				r.getPosition();
				if (r.getPosition() == raceNum) {
					race = false;
				}
			}
			divide();
		}
		if (t.getPosition() == 100)
			System.out.println("THE TORTOISE WINS");
		if (h.getPosition() == 100)
			System.out.println("THE HARE WINS");
		if (TS.getPosition() == 100)
			System.out.println("THE TURBO SNAIL WINS");
	}

	public void divide() {
		System.out.println("________________________________");
	}
	
}
