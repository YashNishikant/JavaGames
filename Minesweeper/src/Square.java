
public class Square {

	private int row;
	private int col;
	private int adjMines;
	private boolean checked;
	private boolean flagged;
	private boolean mine;
	private String display;
	private boolean manuallyCheckedMine;

	public Square(int r, int c, String s) {
		row = r;
		col = c;
		display = s;
		flagged = false;
		checked = false;
		adjMines = 0;
	}

	public String testing() {

		if (mine) {
			display = "X";
			return " " + display + " ";
		}
		if (adjMines == 0 && !checked) {
			display = "_";
			return " " + display + " ";
		} else if (adjMines == 0 && checked) {
			display = " ";
			return " " + display + " ";
		}

		return " " + adjMines + " ";
	}

	public String getDisplay() {

		if (mine && checked && manuallyCheckedMine) {
			display = "X";
			return " " + display + " ";
		}
		if (mine && checked && !manuallyCheckedMine && !flagged) {
			display = "_";
			return " " + display + " ";
		}
		if (mine && !checked && !flagged) {
			display = "_";
			return " " + display + " ";
		}
		if (!checked && !flagged) {
			display = "_";
			return " " + display + " ";
		}
		if (flagged) {
			display = "F";
			return " " + display + " ";
		}
		if (adjMines == 0 && checked) {
			display = " ";
			return " " + display + " ";
		}

		return " " + adjMines + " ";
	}

	public boolean isFlagged() {
		return flagged;
	}

	public void toggleFlagged() {
		flagged = !flagged;
	}

	public void setCheck(boolean x) {
		checked = x;
	}

	public int getAdjMines() {
		return adjMines;
	}

	public void setAdjMines(int x) {
		adjMines = x;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isMine() {
		return mine;
	}

	public void incrementNum() {
		adjMines++;
	}

	public int getNum() {
		return adjMines;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setMine(boolean x) {
		mine = x;
	}

	public void setmanualcheck(boolean x) {
		manuallyCheckedMine = x;
	}
}
