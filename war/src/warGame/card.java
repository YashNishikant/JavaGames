package warGame;

public class card {

	private int value;
	private String suit;
	
	private String Ace =  "Ace of ";
	private String Jack =  "Jack of ";
	private String Queen =  "Queen of ";
	private String King =  "King of ";

	public card(int v, String s) {

		value = v + 1;
		suit = s;
	}

	public String toString1() {
		return value + " of " + suit;
	}

	public int getValue() {

		return value;
	}
	
	public int currentValue() {
		if(value > 10) {
			return 10;
		}
		
		if(value == 1) {
			return 11;
		}
		return value;
	}

	public String getSuit() {
		return suit;
	}

	public String toString() {
		switch (value) {
		case 1:
			return Ace + suit;
		case 11:
			return Jack + suit;
		case 12:
			return Queen + suit;
		case 13:
			return King + suit;
		}
		return value + " of " + suit;
	}
}
