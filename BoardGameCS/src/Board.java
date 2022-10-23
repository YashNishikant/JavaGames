public class Board {

	private Space[][] board;
	private Space newspace;
	private String space;
	private int mapGen;
	private int rarity = 10;

	public Board(int y, int x, String p1, String p2) {
		board = new Space[x][y];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				newspace = new Space("");
				board[i][j] = newspace;
				
				mapGen = (int) (Math.random() * rarity) + 1;
				
				if (i == board.length - 1 && j == board[0].length - 1) {
					space = "E";
					board[i][j].setReplaceStr(space);
					board[i][j].setEmpty(false);
					board[i][j].setEnd(true);
				} else if (mapGen == 1 && i != 0 && j != 0) {
					space = "X";
					board[i][j].setReplaceStr(space);
					board[i][j].setEmpty(false);
				} else {
					space = " ";
					board[i][j].setReplaceStr(space);
					board[i][j].setEmpty(true);
				}

				if (space == " ") {
					board[i][j].setReplaceStr("");
					board[i][j].setChar(" ___ ");
				} else {
					board[i][j].setChar(" _" + space + "_ ");
				}
			}
		}
	}

	public void draw(Player p1, Player p2) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				mapGen = (int) (Math.random() * rarity) + 1;

				if (p1.getRow() == p2.getRow() && p1.getRow() == i && p1.getCol() == p2.getCol() && p1.getCol() == j) {
					board[i][j].setChar(" _" + p1.getDisplay() + p2.getDisplay() + "_ ");
				} else if (p1.getRow() == i && p1.getCol() == j) {
					board[i][j].setChar(" _" + p1.getDisplay() + "_ ");
				} else if (p2.getRow() == i && p2.getCol() == j) {
					board[i][j].setChar(" _" + p2.getDisplay() + "_ ");
				}
				if ((p1.getRow() != i || p1.getCol() != j) && (p2.getRow() != i || p2.getCol() != j) && board[i][j].isEmpty()) {
					board[i][j].setChar(" ___ ");
				}
				if ((p1.getRow() != i || p1.getCol() != j) && (p2.getRow() != i || p2.getCol() != j) && !board[i][j].isEmpty()) {
					board[i][j].setChar(" _" + board[i][j].getReplaceStr() + "_ ");
				}
				if(p1.getRow() == i && p1.getCol() == j && !board[i][j].isEmpty() && !board[i][j].getEnd() && !p1.reachedEnd() && !p2.reachedEnd()) {
					p1.death();
				}
				if(p2.getRow() == i && p2.getCol() == j && !board[i][j].isEmpty() && !board[i][j].getEnd() && !p1.reachedEnd() && !p2.reachedEnd()) {
					p2.death();
				}
				System.out.print(board[i][j].getChar());
			}
			System.out.println();
		}
	}

	public int getEndX() {
		return board.length - 1;
	}

	public int getEndY() {
		return board[0].length - 1;
	}
}
