
import java.awt.Color; 
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class sandbox extends JPanel implements ActionListener, KeyListener {
	int treeNumber = -1;
	int knockbackRNG = 0;
	int blockoffset = 0;
	int separate = 0;
	int separatetree = 0;
	int amount = 0;
	int userX;
	int userY;
	int blockIndex = 0;
	boolean destroyIcon = false;
	boolean start = true;
	boolean onBlock = false;
	boolean climb = false;
	boolean canmoveLeft = true;
	boolean canmoveRight = true;
	boolean breakblock = false;

	terrainGen[] world = new terrainGen[500];
	trees[] tree = new trees[500];
	clouds cloud = new clouds();
	Timer time = new Timer(5, this);
	hotbar hbar = new hotbar();
	human user = new human();
	blocks[] block = new blocks[1000];

	public sandbox() {

		userX = user.personX;
		userY = (int) (user.personY - 10000);

		for (int i = 0; i < world.length; i++) {
			terrainGen world1 = new terrainGen(((int) (Math.random() * 50) + 800), separate, -400);
			world[i] = world1;
			separate += 400;
		}
		for (int i = 0; i < tree.length; i++) {
			trees tree1 = new trees(separatetree, world[i].chunkY - 350);
			tree[i] = tree1;
			separatetree += 500;
		}
		for (int i = 0; i < block.length; i++) {
			blocks block1 = new blocks(userX, userY);
			block[i] = block1;
		}

		time.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Sky
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 2000, 2000);

		for (int i = 0; i < tree.length; i++) {
			ImageIcon i5 = new ImageIcon("C:\\Users\\yash0\\Pictures\\trees.png");
			i5.paintIcon(this, g, tree[i].xtree, tree[i].ytree);
		}

		for (int i = 0; i < block.length; i++) {
			if (block[i].place) {
				ImageIcon i7 = new ImageIcon("C:\\Users\\yash0\\Pictures\\wood.png");
				i7.paintIcon(this, g, block[i].blockX, block[i].blockY);

			}
		}

		for (int i = 0; i < world.length; i++) {
			world[i].naturaldrawings(g);
		}

		user.draw(g);
		ImageIcon i4 = new ImageIcon("C:\\Users\\yash0\\Pictures\\imageface.png");
		i4.paintIcon(this, g, user.personX, (int) (user.personY + 10));
		eIcon(g);
		cloud.draw(g);
		hbar.draw(g);
		ImageIcon i7 = new ImageIcon("C:\\Users\\yash0\\Pictures\\wood.png");
		i7.paintIcon(this, g, hbar.x + 18, hbar.y + 18);
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.BOLD, 25));
		g.drawString("" + amount, hbar.x + 60, hbar.y + 60);

	}

	public void eIcon(Graphics g) {
		if (destroyIcon) {
			ImageIcon i5 = new ImageIcon("C:\\Users\\yash0\\Pictures\\E_Icon.png");
			i5.paintIcon(this, g, user.personX - 30, (int) (user.personY - 10));
		}
	}

	public void setminmax() {
		if (!user.jump) {
			user.maxheight = (int) (user.personY - 200);

			for (int i = 0; i < world.length; i++) {
				user.minheight = world[i].chunkY;
			}
		}
	}

	public void blockUpdate() {

		block[amount].blockX = user.personX;
		block[amount].blockY = (int) (user.personY + 39);

	}

	public void actionPerformed(ActionEvent e) {

		user.move();
		for (int i = 0; i < world.length; i++) {
			world[i].move();
		}
		for (int i = 0; i < tree.length; i++) {
			tree[i].move();
		}
		for (int j = 0; j < block.length; j++) {
			block[j].move();
		}
		block[blockIndex].destroy();
		blockUpdate();
		setminmax();
		user.jump();
		cloud.move();
		Collision();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int i = e.getKeyCode();

		if (i == KeyEvent.VK_E && destroyIcon) {

			if (amount < 32) {
				tree[treeNumber].treedissapear = true;
				amount += 4;
			}
		}
		if (i == KeyEvent.VK_SPACE && breakblock) {

			block[blockIndex].blockbreak = true;
		}
		if (i == KeyEvent.VK_F && amount > 0) {
			block[amount].place = true;
			amount--;
		}

		if (i == KeyEvent.VK_A && canmoveLeft) {

			for (int j = 0; j < world.length; j++) {
				world[j].speed = 3;
			}
			for (int j = 0; j < tree.length; j++) {
				tree[j].speed = 3;
			}
			for (int j = 0; j < block.length; j++) {
				block[j].blockspeed = 3;
			}
		}

		if (i == KeyEvent.VK_D && canmoveRight) {

			for (int j = 0; j < world.length; j++) {
				world[j].speed = -3;
			}
			for (int j = 0; j < tree.length; j++) {
				tree[j].speed = -3;
			}
			for (int j = 0; j < block.length; j++) {
				block[j].blockspeed = -3;
			}
		}
		for (int j = 0; j < world.length; j++) {
			if (i == KeyEvent.VK_W && !user.jumping && user.personY >= world[j].chunkY-80) {
				user.jump = true;
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		for (int j = 0; j < world.length; j++) {
			world[j].speed = 0;
		}

		for (int j = 0; j < tree.length; j++) {
			tree[j].speed = 0;
		}

		for (int j = 0; j < block.length; j++) {
			block[j].blockspeed = 0;
		}

	}

	public void Collision() {
		Rectangle human = user.bounds();
		breakblock = false;
		for (int i = 0; i < block.length; i++) {

			Rectangle blocksREC = block[i].bounds();

			if (blocksREC.intersects(human)) {
				blockIndex = i;
				breakblock = true;
			}
			if (blocksREC.intersects(human) && block[i].place) {
				if (user.personY <= block[i].blockY) {
					user.personY = block[amount].blockY - 42;
					user.speedY = 0;
				}
			}

			if (blocksREC.intersects(human) && block[i].place) {
				if (user.personY > block[i].blockY) {
					for (int j = 0; j < world.length; j++) {
						world[j].speed = 0;
					}
					for (int j = 0; j < tree.length; j++) {
						tree[j].speed = 0;
					}
					for (int j = 0; j < block.length; j++) {
						block[j].blockspeed = 0;
					}

					if (user.personX < block[i].blockX) {

						for (int j = 0; j < world.length; j++) {
							world[j].chunkX += 5;
						}
						for (int j = 0; j < tree.length; j++) {
							tree[j].xtree += 5;
						}
						for (int j = 0; j < block.length; j++) {
							block[j].blockX += 5;
						}

					}

					if (user.personX > block[i].blockX) {

						for (int j = 0; j < world.length; j++) {
							world[j].chunkX -= 5;
						}
						for (int j = 0; j < tree.length; j++) {
							tree[j].xtree -= 5;
						}
						for (int j = 0; j < block.length; j++) {
							block[j].blockX -= 5;
						}

					}

				}
			}

			for (int j = 0; j < world.length; j++) {
				Rectangle worldtile = world[j].bounds();
				if (human.intersects(worldtile)) {
					if (user.personY <= world[j].chunkY) {
						if (!user.jump) {
							user.personY = world[j].chunkY - 80;
						}
						break;
					}
				}
			}
			destroyIcon = false;
			for (int h = 0; h < tree.length; h++) {
				Rectangle treeRec = tree[h].bounds();
				if (treeRec.intersects(human)) {
					treeNumber = h;
					destroyIcon = true;
					break;
				}
			}
		}
	}
}
