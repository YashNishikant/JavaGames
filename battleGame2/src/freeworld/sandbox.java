package freeworld;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;

import weapons.armor;
import Environment.Ground;
import Environment.clouds;
import Environment.land;
import Environment.rain;
import Environment.sun;
import engine.Textures;
import engine.mouseClicker;
import playerNpc.BattleBoss;
import playerNpc.NPC;
import playerNpc.human;
import structures.buildings;
import weapons.DestroyerBullets;
import weapons.Shield;
import weapons.battery;
import weapons.bullet;
import java.awt.Rectangle;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class sandbox extends Textures implements ActionListener, MouseMotionListener, KeyListener, MouseListener {

	int buildspacing = (int) (Math.random() * -100000) + 100000;
	int landSpacing = -1500;
	double landSpacingFloor = 320;
	int limitXleft = 1900;
	int limitXright = -10;
	int spacing = 450;
	double farthestChunk;
	int grounds = 5;
	double brightness = 1;

	buildings[] towers = new buildings[10];
	Ground floor = new Ground(0);
	BattleBoss destroyer = new BattleBoss(100, 100);
	NPC[] player = new NPC[0];
	armor iron = new armor();
	land[] landscape = new land[10];
	Ground floor1;
	mouseClicker click = new mouseClicker();
	sun Sun = new sun();
	rain[] raindrop = new rain[20];
	clouds cloud = new clouds();
	human user = new human();
	DestroyerBullets[] enemyBulletLeft = new DestroyerBullets[10];
	DestroyerBullets[] enemyBulletRight = new DestroyerBullets[10];
	bullet[] bullets = new bullet[10];
	controls gui = new controls(0);
	Shield shield = new Shield(iron.armorPosX + 100, iron.armorPosY);
	battery power = new battery();
	Timer time = new Timer(1, this);
	String assetsPath;
	int upwardForce = 8;
	double gravity = user.DOWNWARD_FORCE;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public sandbox() {

		assetsPath = System.getProperty("user.dir");
		assetsPath += "\\src\\assets\\";
		for (int i = 0; i < grounds; i++) {
			Ground floor1 = new Ground(landSpacingFloor);
			landSpacingFloor += 320;
			floor.add(floor1);
		}

		for (int i = 0; i < raindrop.length; i++) {
			rain raindrop1 = new rain();
			raindrop[i] = raindrop1;
		}

		for (int i = 0; i < player.length; i++) {
			NPC player1 = new NPC((int) (Math.random() * 4000) + 200, 1);
			player[i] = player1;
		}

		for (int i = 0; i < bullets.length; i++) {
			bullet b1 = new bullet(iron.armorPosX + 19, iron.armorPosY - 5);
			bullets[i] = b1;
		}

		for (int i = 0; i < towers.length; i++) {
			buildings building = new buildings(buildspacing);
			buildspacing += 600;
			towers[i] = building;
		}

		for (int i = 0; i < enemyBulletLeft.length; i++) {
			DestroyerBullets enemyBullet1 = new DestroyerBullets(destroyer.X, destroyer.Y + 10);
			enemyBulletLeft[i] = enemyBullet1;
		}

		for (int i = 0; i < enemyBulletRight.length; i++) {
			DestroyerBullets enemyBullet2 = new DestroyerBullets(destroyer.X, destroyer.Y + 10);
			enemyBulletRight[i] = enemyBullet2;
		}

		for (int i = 0; i < landscape.length; i++) {
			land land1 = new land(landSpacing);
			landSpacing += 800;
			landscape[i] = land1;
		}

		time.start();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public void actionPerformed(ActionEvent e) {
		if (!iron.track)
			user.gravity(DOWNWARD_FORCE);

		user.applyForceVertical(upwardForce);
		Sun.sunPath();
		beginRain();
		movingObjects();
		refreshBullets();
		moveTowardsPlayer();
		weather();
		user.move();
		knockbackRNG();
		deadnpc();
		iron.move();
		batterydecrease();
		miscRandomBugFix();
		destroyer.move();
		destroyer.Behavior();
		iron.tracking();
		appropriateImage();
		trackSystem();
		power.batteryfunction();
		cloud.move();
		Collision();
		fireTick();
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		gui.naturaldrawings(g);
		Sun.draw(g);
		drawWorld(g);
		floor.draw(g);
		cloud.draw(g);
		drawNPC(g);
		drawShield(g);
		destroyer.draw(g);
		destroyer.drawHealth(g);
		drawEnemyBullets(g);
		flyWithShield(g);
		iron.Images(g);
		drawRainDrops(g);
		drawUser(g);
		darkenworld(g);
		batteryAndBullets(g);
	}

	public void miscRandomBugFix() {

		for (int i = 0; i < landscape.length; i++) {

			if (landscape[i].X < -1000) {
				landscape[i].X = 1800;
			}

			if (landscape[i].X > 1920) {
				landscape[i].X = -850;
			}

		}
		
		if (iron.track) {
			user.gravityActivate = false;
		}
		if (iron.track) {
			user.holdingWeapon = false;
		}
		for (int i = 0; i < bullets.length; i++) {
			if (!bullets[i].bulletFire) {
				bullets[i].bulletX = iron.armorPosX + 19;
				bullets[i].bulletY = iron.armorPosY - 5;
			}

			shield.x = iron.armorPosX + shield.distanceaway;
			shield.y = iron.armorPosY;
		}

		for (int i = 0; i < enemyBulletLeft.length; i++) {
			if (!enemyBulletLeft[i].bulletFire) {
				enemyBulletLeft[i].bulletX = destroyer.X;
				enemyBulletLeft[i].bulletY = destroyer.Y + 10;
			}
		}

		for (int i = 0; i < enemyBulletRight.length; i++) {
			if (!enemyBulletRight[i].bulletFire) {
				enemyBulletRight[i].bulletX = destroyer.X;
				enemyBulletRight[i].bulletY = destroyer.Y + 10;
			}
		}
		
	}

	void movePlayer(double x) {
		if ((int) x == 0) {
			user.isMoving = false;
		} else {
			user.isMoving = true;
		}

		if (!user.death) {
			for (int j = 0; j < player.length; j++) {
				player[j].speed = x;
			}
			for (int j = 0; j < towers.length; j++) {
				towers[j].speed = x;
			}
			for (int j = 0; j < landscape.length; j++) {
				landscape[j].backgroundspeed = x / 2;
			}
			destroyer.speed = x;
			iron.armorspeed = x;
		}
	}



	public void darkenworld(Graphics g) {

		g.setColor(new Color(0, 0, 0, Sun.light)); // change to xf for x% darker
		g.fillRect(0, 0, 10000, 10000);

		if (Sun.xsun > 1900 && Sun.light < 0.6) {
			Sun.light += 0.001;
			gui.night = true;
		}

		if (Sun.xsun > -100 && Sun.xsun < 1900 && Sun.light > 0.0) {
			Sun.light -= 0.001;
			gui.night = false;
		}

	}

	public void drawWorld(Graphics g) {
		for (int i = 0; i < landscape.length; i++) {
			landscape[i].draw(g);
		}
		for (int i = 0; i < towers.length; i++) {
			if (towers[i].bX > limitXright - 180 && towers[i].bX < limitXleft + 180) {
				towers[i].draw(g);
			}
		}
	}

	public void drawShield(Graphics g) {
		if (shield.activateShield) {
			shield.draw(g);
		}
	}

	public void drawNPC(Graphics g) {
		for (int i = 0; i < player.length; i++) {
			if (player[i].alive && player[i].npcX > limitXright && player[i].npcX < limitXleft) {
				player[i].drawNPC(g);
				player[i].drawNPCHealth(g);
			} else {
				if (player[i].npcX > limitXright && player[i].npcX < limitXleft) {
					player[i].dead(g);
				}
			}
		}
	}

	public void drawUser(Graphics g) {
		if (!iron.track) {
			user.drawHealth(g, user.personX, user.personX, 5);
		} else{
			user.drawHealth(g, user.personX, user.personX, 10);
		}

		if (!iron.track) {
			user.draw(g);
		}
	}

	public void drawRainDrops(Graphics g) {
		for (int i = 0; i < raindrop.length; i++) {
			if (raindrop[i].beginRain) {
				raindrop[i].draw(g);
			}
		}
	}

	void flyWithShield(Graphics g) {

		if (iron.flyIMG && !shield.activateShield) {

			addImage(g, "//Armor//TankFlyRight.png", iron.armorPosX, iron.armorPosY);

		} else if (user.y <= 865 && shield.activateShield) {
			addImage(g, "//Armor//TankArmor.png", iron.armorPosX, iron.armorPosY);
		}

		if (iron.flyIMG_LEFT && !shield.activateShield) {

			addImage(g, "//Armor//TankFlyLeft.png", iron.armorPosX, iron.armorPosY);

		} else if (user.y <= 865 && shield.activateShield) {

			addImage(g, "//Armor//TankArmor.png", iron.armorPosX, iron.armorPosY);

		}

	}


	public void batteryAndBullets(Graphics g) {
		for (int i = 0; i < bullets.length; i++) {
			if (!iron.flyIMG && !iron.turbo && !iron.ableToTurbo_LEFT && !iron.flyIMG_LEFT) {
				bullets[i].fire();
				bullets[i].draw(g);
			}
		}

		if (iron.track) {

			gui.drawArmor(g);

			if (power.powerlength <= 0) {
				user.nobattery = true;

				iron.batteryDrained(g);

			}
			power.draw(g);
		}
	}

	public void drawEnemyBullets(Graphics g) {
		for (int i = 0; i < enemyBulletLeft.length; i++) {
			enemyBulletLeft[i].fire();
			enemyBulletLeft[i].bulletSpeed = -30;

			if (enemyBulletLeft[i].bulletFire) {

				addImage(g, "//Bullet//DestroyerBulletsLEFT.png", enemyBulletLeft[i].bulletX,
						(int) enemyBulletLeft[i].bulletY + enemyBulletLeft[i].yoffset);
			}
		}

		for (int i = 0; i < enemyBulletRight.length; i++) {
			enemyBulletRight[i].fire();
			enemyBulletRight[i].bulletSpeed = 30;
			if (enemyBulletRight[i].bulletFire) {

				addImage(g, "//Bullet//DestroyerBulletsRIGHT.png", enemyBulletRight[i].bulletX,
						(int) enemyBulletRight[i].bulletY + enemyBulletRight[i].yoffset);
			}
		}
	}

	public void trackSystem() {

		if (iron.track) {
			iron.armorPosX = (int) (user.personX - 12);
			iron.armorPosY = (int) (user.y + 8);
		}
	}

	public void batterydecrease() {
		if (user.insideSuit && iron.armorPosY <= 869) {
			power.isflyingforbattery = true;
		} else {
			power.isflyingforbattery = false;
		}
	}

	public void knockbackRNG() {

		gui.knockbackRNG++;
		for (int i = 0; i < player.length; i++) {
			if (gui.knockbackRNG % 10 == 0) {
				player[i].knockbackR = false;
			}
			if (gui.knockbackRNG % 10 == 0) {
				player[i].knockbackL = false;
			}
		}

		if (gui.knockbackRNG % 10 == 0) {
			destroyer.knockback = false;
		}
	}

	public void deadnpc() {
		for (int i = 0; i < player.length; i++) {
			if (!player[i].alive) {
				if (player[i].npcY <= 915) {
					player[i].speedY = 2;
					player[i].speedaddition = 0;
				} else {
					player[i].speedY = 0;

				}
			}
		}

		if (!destroyer.alive) {
			if (destroyer.Y <= 870) {
				destroyer.speedY = 4;
			} else {
				destroyer.speedY = 0;
			}
		}

		if (user.death && iron.armorPosY <= 870) {
			user.speedY += 0.5;
		}

	}

	public void beginRain() {
		int randomRain;
		randomRain = (int) (Math.random() * 200000);

		if (randomRain == 10) {
			gui.darkenSky = true;
			for (int i = 0; i < raindrop.length; i++) {
				raindrop[i].beginRain = true;
				gui.beginRain = true;
			}
		}
		if (randomRain == 20) {
			gui.darkenSky = false;
			for (int i = 0; i < raindrop.length; i++) {
				raindrop[i].beginRain = false;
				gui.beginRain = false;
			}
		}

		for (int i = 0; i < raindrop.length; i++) {
			raindrop[i].rainFall();
		}

	}

	void appropriateImage() {
		if (user.y >= 870) {
			iron.flyIMG = false;
			iron.flyIMG_LEFT = false;
			iron.turbo = false;
			iron.turbo_LEFT = false;
		}
	}

	void weather() {
		if (gui.greenblue > 150 && gui.darkenSky)
			gui.greenblue--;

		if (!gui.darkenSky && gui.greenblue < 255) {
			gui.greenblue++;
		}
	}

	void refreshBullets() {

		for (int j = 0; j < enemyBulletLeft.length; j++) {
			enemyBulletLeft[j].destroy();
		}
		for (int j = 0; j < enemyBulletRight.length; j++) {
			enemyBulletRight[j].destroy();
		}

		if (gui.shieldHP <= 0) {
			shield.activateShield = false;
		}

		if (gui.enemyAmmoR < 2) {
			gui.enemyAmmoR = 99;
			for (int i = 0; i < enemyBulletRight.length; i++) {
				DestroyerBullets enemyBullet2 = new DestroyerBullets(destroyer.X, destroyer.Y + 10);
				enemyBulletRight[i] = enemyBullet2;
			}
		}

		if (gui.enemyAmmoL < 2) {
			gui.enemyAmmoL = 99;
			for (int i = 0; i < enemyBulletLeft.length; i++) {
				DestroyerBullets enemyBullet1 = new DestroyerBullets(destroyer.X, destroyer.Y + 10);
				enemyBulletLeft[i] = enemyBullet1;
			}
		}
	}

	void movingObjects() {
		for (int j = 0; j < player.length; j++) {
			player[j].npcBehavior();
			player[j].move();
		}
		for (int j = 0; j < towers.length; j++) {
			towers[j].move();
		}

	}

	void moveTowardsPlayer() {

		if (destroyer.Y > (int) user.y) {
			destroyer.speedY = -1;
		} else {
			destroyer.speedY = 0;
		}
		if (destroyer.Y < (int) user.y) {
			destroyer.speedY = 1;
		} else {
			destroyer.speedY = 0;
		}
		if (destroyer.alive && !user.death) {
			if (destroyer.attackLeft) {
				if (destroyer.X > (int) user.personX + 200) {
					destroyer.speedaddition = -destroyer.bossSpeed;
				} else {
					destroyer.speedaddition = 0;
				}
				if (destroyer.goUp) {
					if (destroyer.Y != (int) user.y) {
						destroyer.speedY = -1;
					}
				}
				if (destroyer.Y == (int) user.y) {
					destroyer.speedY = 0;
					if (gui.enemyAmmoL > 0 && destroyer.destroyerFireLock) {
						enemyBulletLeft[gui.enemyAmmoL].bulletFire = true;
						gui.enemyAmmoL--;
						destroyer.destroyerFireLock = false;
					}
				}

				if (destroyer.goDown) {

					if (destroyer.Y <= (int) user.y && destroyer.Y >= (int) user.y - destroyer.attackRangeVertical) {
						if (gui.enemyAmmoL > 0 && destroyer.destroyerFireLock) {
							enemyBulletLeft[gui.enemyAmmoL].bulletFire = true;
							gui.enemyAmmoL--;
							destroyer.destroyerFireLock = false;
						}
					}

					if (destroyer.Y >= (int) user.y + destroyer.attackRangeVertical && destroyer.Y <= user.y) {
						if (gui.enemyAmmoL > 0 && destroyer.destroyerFireLock) {
							enemyBulletLeft[gui.enemyAmmoL].bulletFire = true;
							gui.enemyAmmoL--;
							destroyer.destroyerFireLock = false;
						}
					}
				}
			}

			if (destroyer.attackRight) {
				if (destroyer.X < (int) user.personX - 200) {
					destroyer.speedaddition = destroyer.bossSpeed;
				} else {
					destroyer.speedaddition = 0;
				}

				if (destroyer.goUp) {
					if (destroyer.Y != (int) user.y) {
						destroyer.speedY = -1;
					} else {
						destroyer.speedY = 0;
						if (gui.enemyAmmoR > 0 && destroyer.destroyerFireLock) {
							enemyBulletRight[gui.enemyAmmoR].bulletFire = true;
							gui.enemyAmmoR--;
							destroyer.destroyerFireLock = false;
						}
					}
				}

				if (destroyer.goDown) {

					if (destroyer.Y <= (int) user.y && destroyer.Y >= (int) user.y - destroyer.attackRangeVertical) {
						if (gui.enemyAmmoR > 0 && destroyer.destroyerFireLock) {
							enemyBulletRight[gui.enemyAmmoR].bulletFire = true;
							gui.enemyAmmoR--;
							destroyer.destroyerFireLock = false;
						}
					}

					if (destroyer.Y >= (int) user.y + destroyer.attackRangeVertical && destroyer.Y <= user.y) {
						if (gui.enemyAmmoL > 0 && destroyer.destroyerFireLock) {
							enemyBulletRight[gui.enemyAmmoR].bulletFire = true;
							gui.enemyAmmoR--;
							destroyer.destroyerFireLock = false;
						}
					}
				}
			}
		}
		if (!destroyer.goDown && !destroyer.goUp) {
			destroyer.speedY = 0;
		}

		if (user.healthcount <= 0) {
			user.death = true;
		}

	}

	void fireTick() {
		destroyer.fireBulletTick++;
		if (destroyer.fireBulletTick % 50 == 0) {
			destroyer.destroyerFireLock = true;
		} else {
			destroyer.destroyerFireLock = false;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int i = e.getKeyCode();

		if (i == KeyEvent.VK_B && gui.shieldHP > 0) {
			shield.activateShield = !shield.activateShield;
			iron.blast = false;
		}

		if (i == KeyEvent.VK_R && !shield.activateShield) {
			if (iron.blast || (iron.confirmgroundfire && iron.fireonground)) {
				if (gui.ammo > 0) {
					gui.ammo--;
					bullets[gui.ammo].bulletFire = true;
				}
			}
		}

		if (i == KeyEvent.VK_F && !iron.ableToTurbo && !iron.ableToTurbo_LEFT && !shield.activateShield) {

			if (iron.track) {

				iron.blast = !iron.blast;
				iron.normal = !iron.normal;
				iron.fire = !iron.fire;
				iron.activatefire = !iron.activatefire;
			}
			if (iron.track && iron.armorPosY >= 870) {

				iron.blast = false;
				iron.normal = false;
				iron.fire = false;
				iron.activatefire = false;
				iron.fireonground = true;
				iron.confirmgroundfire = true;
			}
		}

		if (i == KeyEvent.VK_G && !iron.flyIMG && !iron.flyIMG_LEFT) {

			iron.blast = false;
			iron.fire = true;
			iron.normal = false;
			iron.confirmgroundfire = false;
			iron.fireonground = false;
		}

		if (i == KeyEvent.VK_V) {

			if (iron.armorPosX != user.personX - 12) {
				if (iron.armorPosX >= user.personX) {
					iron.armorspeed = -10;
				}

				if (iron.armorPosX <= user.personX) {
					iron.armorspeed = 10;
				}
			}
			power.track = true;
		}

		if (i == KeyEvent.VK_C && iron.track) {
			if (user.y >= 870) {
				iron.track = false;
				power.track = false;
				iron.armorPosX = user.personX - 70;
			}
			user.insideSuit = false;
		}

		if (i == KeyEvent.VK_W && !user.nobattery) {
			if (iron.track && !iron.confirmgroundfire && !iron.fireonground) {
				user.speedY = -5;

				if (user.y >= 870) {
					power.isflyingforbattery = false;
				}
			}
			if (!iron.track) {
				user.forceUp = true;
			}
		}

		
		if (i == KeyEvent.VK_S && iron.track == true && !(iron.armorPosY >= 970)) {
			user.speedY = 5;
		}

		if (i == KeyEvent.VK_A && !user.death) {
		
			user.turnLeft = true;
			user.turnRight = false;
			user.animateLeft = true;
			user.animateRight = false;

			iron.blast = false;

			if (!shield.activateShield) {
				iron.ableToTurbo_LEFT = true;
			}
			// ground
			if (iron.track == true && iron.armorPosY >= 870) {
				movePlayer(12);
			}
			if (!iron.track) {
				movePlayer(user.walkingspeed);
			}

			// air
			if (iron.track == true && iron.armorPosY < 870) {

				iron.flyIMG = false;
				iron.flyIMG_LEFT = true;
				iron.fire = false;
				iron.canfly = false;
				iron.ignore = false;
				iron.blast = false;
				iron.normal = false;
				iron.fireonground = false;
				iron.confirmgroundfire = false;

				movePlayer(12);
			}
		}

			if (i == KeyEvent.VK_D && !user.death) {
				user.turnLeft = false;
				user.turnRight = true;
				user.animateRight = true;
				user.animateLeft = false;
				iron.blast = false;

				if (!shield.activateShield) {
					iron.ableToTurbo = true;
				}
				// ground
				if (iron.track == true && iron.armorPosY >= 870) {
					movePlayer(-12);
				}
				if (!iron.track) {
					movePlayer(-user.walkingspeed);
				}
				// air
				if (iron.track == true && iron.armorPosY < 870) {

					iron.flyIMG = true;
					iron.flyIMG_LEFT = false;
					iron.fire = false;
					iron.canfly = false;
					iron.ignore = false;
					iron.blast = false;
					iron.normal = false;
					iron.fireonground = false;
					iron.confirmgroundfire = false;

					movePlayer(-12);
				}
			}

		if (i == KeyEvent.VK_T && iron.ableToTurbo && iron.track) {

			iron.turbo = true;
			iron.flyIMG = false;

			power.turboReducePowerMore = true;

			movePlayer(-82);
		}

		if (i == KeyEvent.VK_Q && iron.ableToTurbo_LEFT && iron.track) {
			iron.turbo_LEFT = true;
			iron.flyIMG_LEFT = false;

			power.turboReducePowerMore = true;

			movePlayer(82);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int i = e.getKeyCode();

		iron.turbo = false;
		iron.turbo_LEFT = false;

		if (i == KeyEvent.VK_A && user.holdingWeapon) {
			user.animateRight = false;
		}

		if (i == KeyEvent.VK_Q && iron.ableToTurbo_LEFT) {
			iron.flyIMG_LEFT = true;
			power.turboReducePowerMore = false;
		}

		if (i == KeyEvent.VK_T && iron.ableToTurbo) {
			iron.flyIMG = true;
			power.turboReducePowerMore = false;
		}
		if (i == KeyEvent.VK_D) {
			iron.flyIMG = false;
			iron.ableToTurbo = false;
			user.animateRight = false;

			if (user.y <= 865) {
				iron.fire = true;
			}
		}

		if (i == KeyEvent.VK_A) {
			iron.flyIMG_LEFT = false;
			iron.ableToTurbo_LEFT = false;
			user.animateLeft = false;

			if (user.y <= 865) {
				iron.fire = true;
			}
		}

		if (i == KeyEvent.VK_D && iron.armorPosY >= 870) {
			movePlayer(0);
		}
		if (i == KeyEvent.VK_A && iron.armorPosY >= 870) {
			movePlayer(0);
		}
		if (!iron.track) {
			user.speedY = 0;
		}
	}

	public void Collision() {

		Rectangle suit = iron.bounds();
		Rectangle human = user.bounds();
		Rectangle boss = destroyer.bounds();
		Rectangle bossDetection = destroyer.detection();
		Rectangle ShieldRec = shield.bounds();


		for (int j = 0; j < enemyBulletLeft.length; j++) {
			Rectangle destroyerBulletLeft = enemyBulletLeft[j].bounds();
			if (destroyerBulletLeft.intersects(human) && enemyBulletLeft[j].bulletFire) {
				if (user.healthcount > 0) {
					user.healthcountPlaceHolder -= enemyBulletLeft[j].damage;
				}
				enemyBulletLeft[j].letdestroy = true;
			}

			if (destroyerBulletLeft.intersects(ShieldRec) && enemyBulletLeft[j].bulletFire) {
				enemyBulletLeft[j].letdestroy = true;
				if (gui.shieldHP > 0)
					gui.shieldHP -= 2;
			}

		}

		for (int j = 0; j < enemyBulletRight.length; j++) {
			Rectangle destroyerBulletRight = enemyBulletRight[j].bounds();
			if (destroyerBulletRight.intersects(human) && enemyBulletRight[j].bulletFire) {
				if (user.healthcount > 0) {
					user.healthcountPlaceHolder -= enemyBulletRight[j].damage;
				}
				enemyBulletRight[j].letdestroy = true;
			}
			if (destroyerBulletRight.intersects(ShieldRec) && enemyBulletRight[j].bulletFire) {
				enemyBulletRight[j].letdestroy = true;
				if (gui.shieldHP > 0)
					gui.shieldHP -= 2;
			}
		}

		if (suit.intersects(human)) {
			iron.track = true;
			user.insideSuit = true;
			power.track = true;
		}

		if (bossDetection.intersects(human)) {

			if (user.y > destroyer.Y) {
				destroyer.goDown = true;
				destroyer.goUp = false;
			}

			if (user.y < destroyer.Y) {
				destroyer.goDown = false;
				destroyer.goUp = true;
			}

			if (user.personX >= destroyer.X) {
				destroyer.attackRight = true;
				destroyer.attackLeft = false;
			}
			if (user.personX <= destroyer.X) {
				destroyer.attackRight = false;
				destroyer.attackLeft = true;
			}
		} else {
			destroyer.attackLeft = false;
			destroyer.attackRight = false;
			destroyer.goDown = false;
			destroyer.goUp = false;
		}

		for (int i = 0; i < towers.length; i++) {
			Rectangle tower = towers[i].bounds();
			towers[i].enter = false;
			if (human.intersects(tower)) {
				towers[i].enter = true;
			}
		}


		Rectangle ground = floor.bounds();

		if (human.intersects(ground)) {
			if (user.y > 870 && !iron.track) {
				user.y -= 1;
			}
			user.fallingFactor = 0;
			user.force = 0;
			if (!iron.track) {
				user.speedY = 0;
			}
			user.setForce = true;
		}

	}

	public void mouseDragged(MouseEvent e) {
	}
	public void mouseMoved(MouseEvent e) {
		click.x = e.getX();
		click.y = e.getY();
	}

	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();

		Container contentpane = frame.getContentPane();
		sandbox sPanel = new sandbox();

		Dimension preferredSize = new Dimension();
		preferredSize.setSize(600, 600);

		frame.setSize(preferredSize);
		contentpane.add(sPanel);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
