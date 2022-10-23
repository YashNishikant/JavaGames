import java.util.Scanner;

public class Main implements Game {

	Player p1;
	Player p2;
	Board gameboard;
	Scanner scan;
	boolean gameEnd = false;
	String message = "Welcome To Death Race!\n\n"
			+ "-This game is based off of Chutes and Ladders...\n"
			+ "-Here are the rules:\n"
			+ "-There are two players racing across a board. \n"
			+ "-The first player to reach the end wins! \n"
			+ "-But WATCH OUT! There are traps marked with an \"X\" on the board.\n"
			+ "-If a player lands on the \"X\" mark, they die, and the other player automatically wins.\n"
			+ "-It's a death race...";
	int boardX = 0;
	int boardY = 0;
	int setBoardX = 10;
	int setBoardY = 10;
	boolean player1Turn = false;
	
	public Main() {
		
		introduction();
		
		p1 = new Player("1", 10, 10, setBoardX);
		p2 = new Player("2", 10, 10, setBoardX);
		p1.setP1(true);
		p2.setP1(false);
		gameboard = new Board(setBoardX, setBoardY, p1.getDisplay(), p2.getDisplay());
		scan = new Scanner(System.in);
		
		dividor();
		displayDelayedText("\nThe game will now begin. Have fun!\n\n", 75);
		initializePos();
		printBoard();
		
		while (!gameEnd) {

			takeTurn(p1);
			takeTurn(p2);
			
			if(checkWinner() || (p1.getDeathInfo() || p2.getDeathInfo())) {
				gameEnd = true;
				
				if(p1.getDeathInfo()) {
					System.out.println("PLAYER 2 WINS DUE TO PLAYER 1's DEATH");
				}
				else if(p2.getDeathInfo()){
					dividor();
					printBoard();
					System.out.println("PLAYER 1 WINS DUE TO PLAYER 2's DEATH");
				}
			}
		}
	}
	
	public void introduction() {

		displayDelayedText(message, 5);
		System.out.println("\n");
	}

	public void printBoard() {
		gameboard.draw(p1,p2);
	}

	public void dividor() {
		System.out.println("==========================================================\n");
	}
	
	public void takeTurn(Player p) {
		
		System.out.println("Roll dice (Press Enter)");
		String ans = scan.nextLine();
		
			p.roll();
			p.move(p.getRoll(), 0);
			p.regulatePlayerPos();
			dividor();
			printBoard();
			p.displayMovement();
		
	}
	
	public boolean checkWinner() {
		
		if(p1.reachedEnd()) {
			System.out.println("PLAYER 1 REACHED THE END FIRST!");
			return true;
		}
		if(p2.reachedEnd()) {
			System.out.println("PLAYER 2 REACHED THE END FIRST!");
			return true;
		}
		
		return false;
	}

	public void displayDelayedText(String msg, int ms) {
		
		for(int i = 0; i < msg.length(); i++) {
			System.out.print(msg.charAt(i));
			delay(ms);
		}
		delay(100);
		
	}
	
	public void delay(int timeMS) {
		try {
			Thread.sleep(timeMS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void initializePos() {
		p1.setRow(boardX);
		p1.setCol(boardY);
		p2.setRow(boardX);
		p2.setCol(boardY);
	}
	
	public static void main(String[] args) {
		Main game = new Main();
	}
	
}
