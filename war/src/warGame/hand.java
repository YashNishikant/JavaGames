package warGame;

import java.util.ArrayList;

public class hand {

	private ArrayList<card> cards;

	public hand() {

		cards = new ArrayList<card>();
		getCards();
	}

	public String toString() {
		String temp = "";
		for (int i = 0; i < cards.size(); i++) {
			temp += cards.get(i).toString() + "\t";
		}
		return temp;
	}

	public ArrayList<card> getCards() {
		return cards;
	}

	public int getMaxCards(int max) {
		return max;
	}

	public void addCard(card c) {
		cards.add(c);
	}

	public int currentValue() {

		int val = 0;
		if(!emptyDeck())
			val = cards.get(0).getValue();

		if (val > 10) {
			return 10;
		}

		if (val == 1) {
			return 11;
		}
		return val;
	}

	public boolean emptyDeck() {
		if (cards.size() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	public int cardNumber() {
		return cards.size();
	}
	
	public void copyCards(ArrayList<card>arrayPile) {
		for (int i = 0; i < arrayPile.size(); i++) {
			cards.add(arrayPile.get(i));
		}
	}
	
	public void shuffle() {
		for(int i = 0; i < 500; i++) {
			int temp = (int)(Math.random()*cards.size());
			cards.add(cards.remove(temp));
		}
	}
	
	public card dealHand() {
		if (!emptyDeck())
			return cards.remove(0);
		else {
			return null;
		}
	}

	public void clearCards() {
		cards.clear();
	}
	
}
