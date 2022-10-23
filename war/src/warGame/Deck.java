package warGame;

import java.util.ArrayList;

public class Deck {
	private ArrayList<card> cards;

	public Deck() {

		cards = new ArrayList<card>();

		for (int i = 0; i < 52; i++) {
			if (i / 13 == 0)
				cards.add(new card(i % 13, "Hearts"));
			else if (i / 13 == 1)
				cards.add(new card(i % 13, "Diamonds"));
			else if (i / 13 == 2)
				cards.add(new card(i % 13, "Clubs"));
			else {
				cards.add(new card(i % 13, "Spades"));
			}	
		}
	}

	public card deal() {
		return cards.remove(0);
	}

	public void shuffle() {
		for(int i = 0; i < 500; i++) {
			int temp = (int)(Math.random()*cards.size());
			cards.add(cards.remove(temp));
		}
	}

	public ArrayList<card> getCards() {
		return cards;
	}

	public String toString() {
		return cards.toString();
	}

}
