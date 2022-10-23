import java.util.Scanner;

public class Main {

	Scanner scan;
	Grid board;

	public Main() {
		board = new Grid();
		scan = new Scanner(System.in);
		
		while (!board.gameEnd()) {
			
			if(board.checkWin()) {
				System.out.println("YOU WIN! \nYou managed to avoid all mines!!! gg");
				board.setGameEnd(true);
				break;
			}
			
			
			System.out.println("Check or Flag? (C or F)");
			String cf = scan.nextLine();
			
			if(cf.equalsIgnoreCase("")) {
				cf = scan.nextLine();
			}
			
			if(cf.equalsIgnoreCase("C")) {
				System.out.println("Row: ");
				int ans = scan.nextInt();
				System.out.println("Col: ");
				int ans2 = scan.nextInt();

				board.checkMine(ans, ans2);
				board.clearSpaces(ans, ans2);
				board.drawBoard();
			}
			if(cf.equalsIgnoreCase("Z")) {
				board.cheat();
			}
			else if(cf.equalsIgnoreCase("F")) {
				System.out.println("Flag Row: ");
				int ansF = scan.nextInt();
				System.out.println("Flag Col: ");
				int ans2F = scan.nextInt();
				board.setFlaggedSpace(ansF, ans2F, true);
				board.drawBoard();
			}
			
		}
	}

	public static void main(String[] args) {
		Main m = new Main();
	}
	
}
