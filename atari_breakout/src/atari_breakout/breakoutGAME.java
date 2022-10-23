package atari_breakout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Rectangle;

public class breakoutGAME extends JPanel implements ActionListener, MouseMotionListener, MouseListener {

	paddleClass paddle = new paddleClass();
	paddleL pL = new paddleL();
	paddleR pR = new paddleR();
	ballBreakout ball = new ballBreakout();
	scoreboard board = new scoreboard();
	playbutton p = new playbutton();
	clicker c = new clicker();

	Timer time = new Timer(5, this);

	boolean generateblocksOnce = true;
	
	boolean onetimeboolean = true;
	boolean startscreen = true;

	int yCoordLVL1 = 200;
	int yCoordLVL2 = 300;
	int yCoordLVL3 = 400;
	int paddleYLoc = 970;

	int blockwidth = 60;
	int blocklength = 60;

	int mouseX;

	int xmouse;
	int ymouse;

	boolean mouseMove = false;
	boolean mouseMoveForButton = false;

	boolean colorchange = false;

	boolean pHit = false;
	boolean pLHit = false;
	boolean pRHit = false;

	boolean blockIsHit = false;

	boolean collisionlock = true;

	boolean right = true;
	boolean left = true;
	boolean bottom = true;
	boolean top = true;

	int speedofBallX = 2;
	int speedofBallY = 2;

	int run = 0;
	
	// Array
	block blockarrayX[] = new block[8];
	block blockarrayY[] = new block[8];
	// Array
	int location = 0;

	public breakoutGAME(boolean b) {
		time.start();
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		addMouseMotionListener(this);
		addMouseListener(this);

		if (b)
		{
			mapgen();
		}
	}
		
	public void mapgen() {	
		for (int i = 0; i < blockarrayX.length; i++) {
	
			location += 200;
			blockarrayX[i] = new block(location, 200, blockwidth, blocklength);
			
			block b1 = blockarrayX[i];

			System.out.println(b1.print());
			
		}	
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		for(int i = 0; i < blockarrayX.length; i++) {
			
			block b = blockarrayX[i];
		
			g.setColor(Color.BLACK);
			g.fillRect(b.xBlock, b.yBlock, b.blockWidth, b.blockHeight);
		}
	
		// scoreboard----------------------------------------

		g.setColor(Color.BLACK);
		g.fillRect(20, board.boardY - 30, 10, 90);

		g.fillRect(160, board.boardY - 30, 10, 90);

		g.setColor(Color.BLUE);
		g.fillRect(0, board.boardY, 200, 90);

		g.setColor(Color.CYAN);
		g.fillRect(10, board.boardY + 10, 180, 70);

		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 25));
		g.drawString("Score: " + board.score, 20, board.boardY + 50);

//ROW 1

//ROW 2
		/*
		 * blockhit(g, block7, yCoordLVL2); blockhit(g, block8, yCoordLVL2); blockhit(g,
		 * block9, yCoordLVL2); blockhit(g, block10, yCoordLVL2); blockhit(g, block11,
		 * yCoordLVL2); blockhit(g, block12, yCoordLVL2);
		 * 
		 * //ROW3
		 * 
		 * blockhit(g, block13, yCoordLVL3); blockhit(g, block14, yCoordLVL3);
		 * blockhit(g, block15, yCoordLVL3); blockhit(g, block16, yCoordLVL3);
		 * blockhit(g, block17, yCoordLVL3); blockhit(g, block18, yCoordLVL3);
		 */
		// paddle right----------------------------------------

		g.setColor(Color.BLUE);
		g.fillRect(pR.paddleXR, paddleYLoc, 75, 20);

		// paddle left----------------------------------------

		g.setColor(Color.BLUE);
		g.fillRect(pL.paddleXL, paddleYLoc, 75, 20);

		// paddle mid----------------------------------------

		g.setColor(Color.BLUE);
		g.fillRect(paddle.paddleX, paddleYLoc, 150, 20);

		// ball----------------------------------------
		g.setColor(Color.GRAY);
		g.fillOval(ball.ballX, ball.ballY, 30, 30);

		if (mouseMove) {

			paddle.paddleX = mouseX;
			pR.paddleXR = mouseX + 150;
			pL.paddleXL = mouseX - 75;

		}

		if (board.score == 900) {

			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 20000, 20000);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 75));
			g.drawString("YOU WIN!", 720, 300);

			ball.ballSpeedX = 0;
			ball.ballSpeedY = 0;

		}

		if (ball.lose == true) {

			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 20000, 20000);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 75));
			g.drawString("You lost", 700, 300);

		}

		if (startscreen == true) {

			board.score = 0;

			g.setColor(Color.white);
			g.fillRect(0, 0, 20000, 20000);
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 75));
			g.drawString("BREAKOUT", 700, 300);

			if (colorchange)
				g.setColor(Color.cyan);
			if (colorchange == false)
				g.setColor(Color.green);

			g.fillOval(p.x, p.y, 80, 80);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString("Play", 900, 400);

			ball.ballSpeedY = 0;

			g.setColor(Color.black);
			g.fillOval(c.xclick + 10, c.yclick + 20, 30, 30);

			board.bringDown = false;
		} else {

			board.bringDown = true;

			if (onetimeboolean) {

				ball.ballSpeedY = speedofBallY;
				onetimeboolean = false;

			}
		}

	}

	public void blockhit(Graphics g, block b, int yCoordLVL) {

		if (b.hitBlock) {

			b.disappear();

		} else {
			g.setColor(Color.RED);
			g.fillRect(b.xBlock, yCoordLVL, 60, 60);

		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		board.scoreboardmove();

		paddle.move();
		pL.moveL();
		pR.moveR();
		ball.move();
		Collision();
		repaint();

	}
	
	public boolean BlockComparisonsRight(block b, boolean blocklock) {
		if (ball.ballX >= b.xBlock) {
			if (ball.ballX <= b.xBlock + blockwidth) {
				if (ball.ballY >= b.yBlock) {
					if (ball.ballY <= b.yBlock + blocklength) {
						top = false;
						if (ball.right == false) {
							if (right == true) {
								b.hitBlock = true;
								ball.right = true;
								blocklock = false;
								top = true;
								board.score += 50;
								System.out.println("right");
							}
						}
					}
				}
			}
		}
		return blocklock;
	}

	public boolean BlockComparisonsBottom(block b, boolean blocklock) {
		if (ball.ballY >= b.yBlock) {
			if (ball.ballY <= b.yBlock + blocklength) {
				if (ball.ballX >= b.xBlock) {
					if (ball.ballX <= b.xBlock + blockwidth) {
						left = false;
						right = false;
						if (ball.down == false) {
							b.hitBlock = true;
							ball.down = true;
							blocklock = false;
							left = true;
							right = true;
							board.score += 50;
							System.out.println("bottom");
						}
					}
				}
			}
		}
		return blocklock;
	}

	public boolean BlockComparisonsTop(block b, boolean blocklock) {
		if (ball.ballY >= b.yBlock) {
			if (ball.ballY <= b.yBlock + blocklength) {
				if (ball.ballX >= b.xBlock) {
					if (ball.ballX <= b.xBlock + blockwidth) {
						if (top == false) {
							if (ball.down == true) {
								b.hitBlock = true;
								ball.down = false;
								blocklock = false;
								board.score += 50;
								System.out.println("top");
							}
						}
					}
				}
			}
		}
		return blocklock;
	}

	public boolean BlockComparisonsLeft(block b, boolean blocklock) {
		if (ball.ballX <= b.xBlock) {
			if (ball.ballX <= (b.xBlock + blockwidth)) {
				if (ball.ballY >= b.yBlock) {
					if (ball.ballY <= b.yBlock + blocklength) {
						if (left == true) {
							if (ball.right == true) {
								b.hitBlock = true;
								ball.right = false;
								blocklock = false;
								board.score += 50;
								System.out.println("left");
							}
						}
					}
				}
			}
		}
		return blocklock;
	}
	 
	public void Collision() {

		Rectangle ballRec = ball.bounds();
		Rectangle paddleRec = paddle.bounds();
		Rectangle RECpL = pL.bounds();
		Rectangle RECpR = pR.bounds();
		Rectangle playREC = p.bounds();
		Rectangle click = c.bounds();
		Rectangle bounds1;
		
		if(run >=1) {
		for(int i = 0; i < blockarrayX.length; i++) {
			System.out.println(i);
			block block1 = ((block)blockarrayX[i]);
			bounds1 = block1.bounds();
		}
		
		for(int i = 0; i < blockarrayX.length; i++) {
			rectanglebound(ballRec, collisionlock, blockarrayX[i]);
		}
		
		}
		if (playREC.intersects(click)) {

			colorchange = true;
		} else {

			colorchange = false;

		}
		if (ballRec.intersects(RECpL)) {

			ball.down = false;
			ball.leftangled = true;
			ball.right = false;

			pLHit = true;
		}

		if (ballRec.intersects(RECpR)) {

			ball.down = false;
			ball.rightangled = true;
			ball.right = true;

			pRHit = true;
		}

		if (ballRec.intersects(paddleRec)) {

			ball.ballSpeedY = speedofBallY;

			ball.down = false;
			pHit = true;
		}

	}
	
	public void rectanglebound(Rectangle ball, boolean CollisionLock, block b) {

		Rectangle r = b.bounds();

		// RIGHT HIT
		if (ball.intersects(r) && CollisionLock == true) {

			CollisionLock = BlockComparisonsRight(b, CollisionLock);
		}

		// BOTTOM HIT
		if (ball.intersects(r) && CollisionLock == true) {

			CollisionLock = BlockComparisonsBottom(b, CollisionLock);
		}

		// TOP HIT
		if (ball.intersects(r) && CollisionLock == true) {

			CollisionLock = BlockComparisonsTop(b, CollisionLock);
		}

		// LEFT HIT
		if (ball.intersects(r) && CollisionLock == true) {

			CollisionLock = BlockComparisonsLeft(b, CollisionLock);
		}
		run++;
	}
	
	public static void main(String[] args) {

		breakoutGAME mouse = new breakoutGAME(false);

		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		breakoutGAME bPanel = new breakoutGAME(true);

		Dimension preferredSize = new Dimension();
		// preferredSize.setSize(1920, 1280);

		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(bPanel);

		frame.setVisible(true);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX() - 10;

		xmouse = e.getX() - 10;
		ymouse = e.getY() - 10;

		e.consume();

	}

	@Override
	public void mouseMoved(MouseEvent e) {

			xmouse = e.getX() - 10;
			ymouse = e.getY() - 10;
			
			c.xclick = xmouse;
			c.yclick = ymouse;

		e.consume();

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (colorchange) {

			startscreen = false;

		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
