import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Rectangle;

public class BallPanel extends JPanel implements ActionListener, KeyListener {

	Timer time = new Timer(5, this);

	ball bounceBall = new ball();
	paddleR RPaddle = new paddleR();
	paddleL LPaddle = new paddleL();

	int scoreL;
	int scoreR;
	int roundNum = 1;
	int paddleSpeeds = 6;
	//int hitNum;
	int speedOfBall = bounceBall.speedBall;
	boolean speedUP;
	boolean gWin;
	boolean bWin;
	boolean goingUpL;
	boolean goingDownL;
	boolean goingUpR;
	boolean goingDownR;
	boolean startDirection;

	boolean startScreenPingPong;
	boolean replay;

	Dimension wh = getToolkit(). getScreenSize();
	Dimension screenHeight = screenSize.height;
	Dimension screenWidth = screenSize.width;
	
	public BallPanel() {

		startScreenPingPong = true;

		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		System.out.println(screenHeight);
		screenHeight = getToolkit(). getScreenSize();
		// Round
		g.setFont(new Font("default", Font.BOLD, 24));
		g.drawString("Round: " + roundNum, 930, 20);

		// Score left
		g.setFont(new Font("default", Font.BOLD, 24));
		g.drawString("TEAM BLUE: " + scoreL, 10, 20);

		// Score right
		g.setFont(new Font("default", Font.BOLD, 24));
		g.drawString("TEAM GREEN: " + scoreR, 1700, 20);

		// hits tracker
		// g.setFont(new Font("default", Font.BOLD,24));
		// g.drawString("Total hits: " + hitNum, 10, 1010);

		// ball
		g.setColor(Color.BLACK);
		g.fillOval(bounceBall.xCoordBall, bounceBall.yCoordBall, 50, 50);

		// innerBall
		g.setColor(Color.red);
		g.fillOval(bounceBall.xCoordBall + 3, bounceBall.yCoordBall + 3, 45, 45);

		// paddle on the left
		g.setColor(Color.blue);
		g.fillRect(LPaddle.xPaddleL, LPaddle.yPaddleL, 30, 150);

		// paddle on the right
		g.setColor(Color.green);
		g.fillRect(RPaddle.xPaddleR + 30, RPaddle.yPaddleR, 30, 150);

		if (startScreenPingPong == true) {

			roundNum = 1;
			// hitNum = 0;
			gWin = false;
			bWin = false;

			g.setColor(Color.white);
			g.fillRect(0, 0, 20000, 20000);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 75));
			g.drawString("PING PONG", 700, 300);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 45));
			g.drawString("Press P to play", 750, 400);

			bounceBall.speedBall = 0;
			bounceBall.speedBallY = 0;

		}

		else {
			bounceBall.speedBall = speedOfBall;
		}

		if (gWin == true) {

			g.setColor(Color.white);
			g.fillRect(0, 0, 20000, 20000);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 75));
			g.drawString("GREEN WINS", 700, 300);

			g.setColor(Color.green);
			g.fillRect(RPaddle.xPaddleR + 30, RPaddle.yPaddleR, 30, 150);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 45));
			g.drawString("Press R to go back to the main menu", 580, 400);

			scoreL = 0;
			scoreR = 0;

			if (replay == true) {
				gWin = false;
				bWin = false;
				startScreenPingPong = true;
			}

		}

		if (bWin == true) {

			g.setColor(Color.white);
			g.fillRect(0, 0, 20000, 20000);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 75));
			g.drawString("BLUE WINS", 700, 300);

			g.setColor(Color.blue);
			g.fillRect(LPaddle.xPaddleL, LPaddle.yPaddleL, 30, 150);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 45));
			g.drawString("Press R to go back to the main menu", 580, 400);

			scoreL = 0;
			scoreR = 0;

			if (replay == true) {
				bWin = false;
				gWin = false;
				startScreenPingPong = true;
			}
		}

	}

	void ultimateWinner() {
		if (scoreL == 3) {
			bWin = true;
		}
		if (scoreR == 3) {
			gWin = true;
		}
		if (scoreL == 3 && scoreR == 2) {
			bWin = true;
		}
		if (scoreR == 3 && scoreL == 2) {
			gWin = true;
		}
	}
	
	void start() {
		if (startDirection == true) {
			bounceBall.starting();
			startDirection = false;
		}
	}
	
	void roundWinnerReset() {
		if (bounceBall.xCoordBall >= 1890) {
			scoreL++;
			bounceBall.xCoordBall = 900;
			bounceBall.yCoordBall = 480;
			startDirection = true;
			// hitNum = 0;
			roundNum++;
		}
		if (bounceBall.xCoordBall <= -14) {
			scoreR++;
			bounceBall.xCoordBall = 900;
			bounceBall.yCoordBall = 480;
			startDirection = true;
			// hitNum = 0;
			roundNum++;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		start();
		bounceBall.move();
		RPaddle.move();
		LPaddle.move();
		Collision();
		ultimateWinner();
		roundWinnerReset();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_R) {
			replay = true;
		}

		if (i == KeyEvent.VK_P) {
			startScreenPingPong = false;
		}

		if (i == KeyEvent.VK_S) {
			LPaddle.speed = paddleSpeeds;
			goingUpL = false;
		}

		if (i == KeyEvent.VK_W) {
			LPaddle.speed = -paddleSpeeds;
			goingUpL = true;
		}

		if (i == KeyEvent.VK_DOWN) {
			RPaddle.speed2 = paddleSpeeds;
			goingDownR = true;
			goingUpR = false;
		}

		if (i == KeyEvent.VK_UP) {
			RPaddle.speed2 = -paddleSpeeds;
			goingDownR = false;
			goingUpR = true;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		LPaddle.speed = 0;
		RPaddle.speed2 = 0;

	}

	public BallPanel(int startX, int startY) {

		bounceBall.xCoordBall = startX;
		bounceBall.yCoordBall = startY;

	}

	public void Collision() {

		Rectangle ballRec = bounceBall.bounds();
		Rectangle paddleLeft = LPaddle.bounds();
		Rectangle paddleRight = RPaddle.bounds();

		if (ballRec.intersects(paddleLeft)) {

			bounceBall.right = true;
			if (goingUpL == true)
				bounceBall.down = false;
			if (goingUpL == false)
				bounceBall.down = true;
			bounceBall.yCoordBall += 1;

		}

		if (ballRec.intersects(paddleRight)) {

			bounceBall.right = false;
			if (goingUpR == true)
				bounceBall.down = false;
			if (goingDownR == true)
				bounceBall.down = true;
			bounceBall.yCoordBall += 1;

		}

	}
}
