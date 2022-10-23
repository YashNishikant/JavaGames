
public class Grid {

	private Square[][] squaregrid;
	private int minesPlaced;
	private int random;
	private boolean gameEnd = false;
	private String message = "Welcome to Minesweeper!";

	public Grid() {
		intro();
		squaregrid = new Square[9][9];
		for (int i = 0; i < squaregrid.length; i++) {
			for (int j = 0; j < squaregrid[0].length; j++) {

				Square s = new Square(i, j, "-");
				random = (int) (Math.random() * 10) + 1;
				if (random == 1 && minesPlaced < 10) {
					s.setMine(true);
					minesPlaced++;
				}
				squaregrid[i][j] = s;
			}
		}
		checkMinePlacement();
		setNumbers();
		drawBoard();
	}

	public void cheat() {
		for (int i = 0; i < squaregrid.length; i++) {
			for (int j = 0; j < squaregrid[0].length; j++) {
				
				squaregrid[i][j].setCheck(true);
				
				}	
			}
	}
	
	public void checkMinePlacement() {
		while (minesPlaced < 10) {

			for (int i = 0; i < squaregrid.length; i++) {
				for (int j = 0; j < squaregrid[0].length; j++) {
					random = (int) (Math.random() * 15) + 1;
					if (random == 1 && minesPlaced < 10) {
						squaregrid[i][j].setMine(true);
						minesPlaced++;
					}
				}
			}
		}
	}

	public void drawBoard() {
		if (!gameEnd) {
			System.out.println("\n\n\n");
			System.out.print("   ");
			for (int i = 0; i < 9; i++) {
				System.out.print(" " + i + " ");
			}
			System.out.println();
			for (int i = 0; i < squaregrid.length; i++) {
				System.out.print(" " + i + " ");
				for (int j = 0; j < squaregrid[0].length; j++) {
					System.out.print(squaregrid[i][j].getDisplay());
				}
				System.out.println();
			}
		}
	}

	public void xray() {

		System.out.println("\n\n\n");

		for (int i = 0; i < squaregrid.length; i++) {
			for (int j = 0; j < squaregrid[0].length; j++) {
				if (!squaregrid[i][j].isMine()) {
					System.out.print(" _ ");
				}
				if (squaregrid[i][j].isMine()) {
					System.out.print(" X ");
				}
			}
			System.out.println();
		}
	}

	public void setNumbers() {
		for (int i = 0; i < squaregrid.length; i++) {
			for (int j = 0; j < squaregrid[0].length; j++) {

				if (squaregrid[i][j].isMine()) {

					if (i + 1 <= 8) {
						squaregrid[i + 1][j].incrementNum();
					}
					if (i - 1 >= 0) {
						squaregrid[i - 1][j].incrementNum();
					}
					if (j + 1 <= 8) {
						squaregrid[i][j + 1].incrementNum();
					}
					if (j - 1 >= 0) {
						squaregrid[i][j - 1].incrementNum();
					}

					if (i + 1 <= 8 && j + 1 <= 8) {
						squaregrid[i + 1][j + 1].incrementNum();
					}
					if (i - 1 >= 0 && j - 1 >= 0) {
						squaregrid[i - 1][j - 1].incrementNum();
					}
					if (j + 1 <= 8 && i - 1 >= 0) {
						squaregrid[i - 1][j + 1].incrementNum();
					}
					if (j - 1 >= 0 && i + 1 <= 8) {
						squaregrid[i + 1][j - 1].incrementNum();
					}
				}
			}
		}
	}

	public void gameOver(int r, int c) {
		gameEnd = true;
		System.out.println("YOU HIT A MINE AT [" + r + ", " + c + "]! YOU LOSE");
		xray();
	}

	public boolean gameEnd() {
		return gameEnd;
	}

	public void setGameEnd(boolean x) {
		gameEnd = x;
	}

	public boolean checkWin() {
		for (int i = 0; i < squaregrid.length; i++) {
			for (int j = 0; j < squaregrid[0].length; j++) {
				if (!squaregrid[i][j].isChecked() && !squaregrid[i][j].isMine()) {
					return false;
				}
			}
		}
		return true;
	}

	public void checkMine(int x, int y) {
		if (squaregrid[x][y].isMine()) {
			squaregrid[x][y].setmanualcheck(true);
			gameOver(x, y);
			return;
		}
	}

	public void setFlaggedSpace(int x, int y, boolean set) {
		if (!squaregrid[x][y].isChecked()) {
			squaregrid[x][y].toggleFlagged();
		}
	}

	public void clearSpaces(int x, int y) {
		if (x < 0 || y < 0 || x > 8 || y > 8) {
			return;
		}
		if (squaregrid[x][y].isFlagged()) {
			squaregrid[x][y].toggleFlagged();
		}
		if (squaregrid[x][y].getNum() > 0) {
			squaregrid[x][y].setCheck(true);
			return;
		}
		if (squaregrid[x][y].isChecked()) {
			return;
		}
		if (squaregrid[x][y].isMine()) {
			return;
		}
		squaregrid[x][y].setCheck(true);

		clearSpaces(x + 1, y);
		clearSpaces(x - 1, y);
		clearSpaces(x, y + 1);
		clearSpaces(x, y - 1);
		clearSpaces(x + 1, y + 1);
		clearSpaces(x - 1, y - 1);
		clearSpaces(x + 1, y - 1);
		clearSpaces(x - 1, y + 1);
	}

	public void intro() {
		for (int i = 0; i < message.length(); i++) {
			System.out.print(message.substring(i, i + 1));
			delay();
		}
	}

	public void delay() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}