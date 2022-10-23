
public class Player {

	private String marker;
	private int row;
	private int col;
	private int diceInt = 0;
	private int endX = 0, endY = 0;
	private int rowSize;
	private boolean dead;
	private boolean p1;
	
	public Player(String indicator, int endCoordX, int endCoordY, int rowSize) {
		marker = indicator;
		endX = endCoordX;
		endY = endCoordY;
		this.rowSize = rowSize;
	}
	public void setP1(boolean x) {
		p1 = x;
	}
	public void playerDisplay() {
		System.out.println(marker);	
	}
	public void regulatePlayerPos() {
		if(getCol() > rowSize-1) {
			setRow(getRow() + 1);
			col = getCol() - (rowSize);
		}
	}
	public String getDisplay() {
		return marker;	
	}
	public void setMarker(String x) {
		marker = x;
	}
	public void setRow(int x) {
		row = x;
	}
	public void setCol(int x) {
		col = x;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}
	public void death() {
		setMarker("*DEAD*");
		dead = true;
	}
	public boolean getDeathInfo() {
		return dead;
	}
	public void move(int y, int x) {
		setRow(getRow()+x);
		setCol(getCol()+y);
		
	}
	public void roll() {
		diceInt = (int)(Math.random()*6)+1;
	}
	public int getRoll() {
		return diceInt;
	}
	public void displayMovement() {
		if(p1) {
			System.out.println("Player 1 moved " + getRoll() + " spaces!");
		}
		else {
			System.out.println("Player 2 moved " + getRoll() + " spaces!");
		}
	}
	public boolean reachedEnd() {
		
		if(getRow() >= endX) {
			return true;
		}
		if(getCol() >= endY) {
			return true;
		}
		return false;
	}
}
