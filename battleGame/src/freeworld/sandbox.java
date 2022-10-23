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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

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
import scenes.BuildingScene;
import scenes.Inventory;
import structures.Airport;
import structures.buildings;
import vehicles.Plane;
import vehicles.SuperCar;
import vehicles.car;
import weapons.DestroyerBullets;
import weapons.Rifle;
import weapons.RifleBullets;
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
	
	boolean onGround = false;
	double brightness = 1;

	buildings[] towers = new buildings[10];
	land[] landscape = new land[10];
	Ground floor = new Ground(0);
	Airport airport = new Airport((int) (Math.random() * 20000) - 10000);
	Airport airport2 = new Airport((int) (Math.random() * 160000) + 80000);
	BattleBoss destroyer = new BattleBoss(-100000, -1000000);
	NPC[] player = new NPC[0];
	armor iron = new armor();
	car car = new car();
	Plane plane = new Plane();
	SuperCar supercar = new SuperCar();

	Ground floor1;
	mouseClicker click = new mouseClicker();
	sun Sun = new sun();
	Inventory[] inventory = new Inventory[5];
	rain[] raindrop = new rain[200];
	clouds cloud = new clouds();
	human user = new human();
	DestroyerBullets[] enemyBulletLeft = new DestroyerBullets[100];
	DestroyerBullets[] enemyBulletRight = new DestroyerBullets[100];
	bullet[] bullets = new bullet[100];
	controls gui = new controls(0);
	RifleBullets[] rbullets = new RifleBullets[gui.rifleammo];
	Shield shield = new Shield(iron.armorPosX + 50, iron.armorPosY);
	Rifle rifle = new Rifle();
	battery power = new battery();
	BuildingScene buildScene = new BuildingScene();
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

		for (int i = 0; i < landscape.length; i++) {
			land land1 = new land(landSpacing);
			landSpacing += 800;
			landscape[i] = land1;
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

		for (int i = 0; i < rbullets.length; i++) {
			RifleBullets r1 = new RifleBullets((int) rifle.X, (int) rifle.y);
			rbullets[i] = r1;
		}

		for (int i = 0; i < inventory.length; i++) {
			Inventory i1 = new Inventory(spacing);
			spacing += 200;
			inventory[i] = i1;

		}

		rifle.X = (int) user.personX + 30;
		rifle.Y = (int) user.y + 30;

		int randomPosPlayer = (int) (Math.random() * 100) + 1;

		for (int i = 0; i < player.length; i++) {
			randomPosPlayer = (int) (Math.random() * 100) + 1;
			if (i < 20) {
				player[i].npcX = airport.X + 3000 + randomPosPlayer;
			} else {
				player[i].npcX = towers[0].bX + 3000 + randomPosPlayer;
			}
		}

		time.start();
		addKeyListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	public void actionPerformed(ActionEvent e) {
		if(!iron.track)
			user.gravity(DOWNWARD_FORCE);
		
		user.applyForceVertical(upwardForce);
		car.gravity(DOWNWARD_FORCE);
		supercar.gravity(DOWNWARD_FORCE);
		Sun.sunPath();
		beginRain();
		iron.uncrafted();
		airport.move();
		airport2.move();
		car.move();
		supercar.move();
		plane.move();
		movingObjects();
		refreshBullets();
		gunDelay();
		moveTowardsPlayer();
		weather();
		user.move();
		knockbackRNG();
		deadnpc();
		keepCarMoving();
		keepPlaneMoving();
		keepSupercarMoving();
		iron.move();
		fireGun();
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
		enterBuilding(g);
		gui.naturaldrawings(g);
		Sun.draw(g);
		drawWorld(g);
		floor.draw(g);
		cloud.draw(g);
		airport.draw(g);
		airport2.draw(g);
		drawNPC(g);
		plane.draw(g);
		car.draw(g);
		supercar.draw(g);
		drawShield(g);
		destroyer.draw(g);
		destroyer.drawHealth(g);
		drawEnemyBullets(g);
		flyWithShield(g);
		iron.Images(g);
		drawRainDrops(g);
		buildScene.draw(g);
		drawUser(g);
		rifle.draw(g);
		drawbullets(g);
		cleargunshot(g);
		darkenworld(g);
		inventory(g);
		drawSlots(g);
		drawSelect(g);
		drawItems(g);
		batteryAndBullets(g);
	}

	public void miscRandomBugFix() {

		if (iron.track) {
			user.gravityActivate = false;
		}

		rifle.X = (int) user.personX + 30;
		rifle.Y = (int) user.y + 30;

		if (gui.setAmmo) {
			gui.rifleammo = rbullets.length - 1;
			gui.setAmmo = false;
		}

		for (int i = 0; i < rbullets.length; i++) {

			rbullets[i].trackbullet(rifle.X);

			if (!rbullets[i].bulletFire) {
				rbullets[i].bulletX = 0;
				rbullets[i].bulletY = 0;
			}
		}

		if (plane.planePos) {
			plane.x = airport.X + 1000;
			plane.planePos = false;
		}
		for (int i = 0; i < landscape.length; i++) {

			if (landscape[i].X < -1000) {
				landscape[i].X = 1800;
			}

			if (landscape[i].X > 1920) {
				landscape[i].X = -850;
			}

		}

		if (iron.track) {
			user.holdingWeapon = false;
		}
		for (int i = 0; i < bullets.length; i++) {
			if (!bullets[i].bulletFire) {
				bullets[i].bulletX = iron.armorPosX + 19;
				bullets[i].bulletY = iron.armorPosY - 5;
			}

			shield.x = iron.armorPosX + 50;
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
			for (int j = 0; j < landscape.length; j++) {
				landscape[j].backgroundspeed = x / 2;
			}
			for (int j = 0; j < player.length; j++) {
				player[j].speed = x;
			}
			for (int j = 0; j < towers.length; j++) {
				towers[j].speed = x;
			}

			if (!car.enter) {
				car.illusionspeed = x;
			}

			if (!supercar.enter) {
				supercar.illusionspeed = x;
			}

			plane.illusionspeed = x;
			airport.illusionSpeed = x;
			airport2.illusionSpeed = x;
			destroyer.speed = x;
			iron.armorspeed = x;
		}
	}
	
	public void fireGun() {
		if (rifle.gunClicked && gui.rifleammo > 0 && rifle.canFire) {

			if (rifle.gunR) {
				rbullets[gui.rifleammo].bulletY = rifle.Y - 25;
				rbullets[gui.rifleammo].bulletX = rifle.X + 35;
			} else {
				rbullets[gui.rifleammo].bulletY = rifle.Y - 25;
				rbullets[gui.rifleammo].bulletX = rifle.X - 85;
			}
			rbullets[gui.rifleammo].bulletFire = true;
			gui.rifleammo--;
			rifle.fireweapon = true;
			rifle.flame = true;
		}
	}

	public void cleargunshot(Graphics g) {
		if (rifle.flame) {
			rifle.fireCount++;

			if (rifle.fireCount > 10) {
				rifle.fireweapon = false;
				rifle.flame = false;
				rifle.fireCount = 0;
			}

			if (gui.rifleammo <= 0) {
				rifle.fireweapon = false;
			}
		}
	}

	public void gunDelay() {
		rifle.delay++;

		if (rifle.delay % 10 == 0) {
			rifle.delay = 0;
			rifle.canFire = true;
		} else {
			rifle.canFire = false;
		}

	}

	public void drawItems(Graphics g) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].draw) {
				addImage(g, "Icons//ARicon.png", inventory[0].X + 50, inventory[0].Y + 80);
				addImage(g, "Icons//None.png", inventory[4].X + 45, inventory[4].Y + 75);
			}
		}
	}

	public void drawSelect(Graphics g) {
		for (int i = 0; i < inventory.length; i++) {
			inventory[i].selectDraw(g);
		}
	}

	public void inventory(Graphics g) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].draw) {
				addImage(g, "Inventory//bg.png", inventory[i].Xboard, inventory[i].Yboard);
			}
		}
	}

	public void drawSlots(Graphics g) {
		for (int i = 0; i < inventory.length; i++) {
			inventory[i].drawSlots(g);
		}
	}

	public void drawbullets(Graphics g) {
		for (int i = 0; i < rbullets.length; i++) {
			rbullets[i].drawBullet(g);
			rbullets[i].fire();
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
		if (!iron.track && !car.enter && !plane.enter && !supercar.enter) {
			user.drawHealth(g, user.personX, user.personX, 5);
		} else if (!car.enter && !plane.enter && !supercar.enter) {
			user.drawHealth(g, user.personX, user.personX, 10);
		}

		if (!iron.track && !car.enter && !plane.enter && !supercar.enter) {
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

	void keepCarMoving() {
		if (car.enter) {
			movePlayer(car.carSpeed);
		}
	}

	void keepSupercarMoving() {
		if (supercar.enter) {
			movePlayer(supercar.carSpeed);
		}
	}

	void keepPlaneMoving() {
		if (plane.enter) {
			movePlayer(plane.planeSpeed);
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

	public void enterBuilding(Graphics g) {
		for (int i = 0; i < towers.length; i++) {
			if (towers[i].enter) {
				addImage(g, "Icons//E_Icon.png", 10, 10);
			}
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

		for (int j = 0; j < towers.length; j++) {
			if (i == KeyEvent.VK_E && towers[j].enter) {
				buildScene.entered = true;
			}

			if (i == KeyEvent.VK_E) {

			}

			if (i == KeyEvent.VK_3 && supercar.canEnter) {
				supercar.enter = true;
			}
			if (i == KeyEvent.VK_3 && car.canEnter) {
				car.enter = true;
			}
			if (i == KeyEvent.VK_3 && plane.canEnter) {
				plane.enter = true;
			}

			if (i == KeyEvent.VK_4) {
				car.enter = false;
				car.carSpeed = 0;

				supercar.enter = false;
				supercar.carSpeed = 0;

				plane.enter = false;
				plane.planeSpeed = 0;

			}
			if (i == KeyEvent.VK_Q) {
				buildScene.entered = false;
			}

		}

		if (i == KeyEvent.VK_1) {
			for (int j = 0; j < inventory.length; j++) {
				inventory[j].draw = true;
			}
		}

		if (i == KeyEvent.VK_2) {
			for (int j = 0; j < inventory.length; j++) {
				inventory[j].draw = false;
				inventory[j].selected = false;
			}
		}

		if (i == KeyEvent.VK_B && gui.shieldHP > 0) {
			shield.activateShield = true;
			iron.blast = false;
		}
		if (i == KeyEvent.VK_Z) {
			shield.activateShield = false;
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

				iron.blast = true;
				iron.normal = false;
				iron.fire = false;
				iron.activatefire = false;
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
		if (i == KeyEvent.VK_SPACE && !user.nobattery) {
			if (iron.track == true && iron.confirmgroundfire == false && iron.fireonground == false) {

				user.speedY = -5;
				
				if (user.y >= 870) {
					power.isflyingforbattery = false;
				}
			}
			if (!iron.track) {
				user.forceUp = true;
			}
		}

		if (i == KeyEvent.VK_S && iron.track == true && !(iron.armorPosY >= 870)) {
			user.speedY = 5;

		}
		if (i == KeyEvent.VK_A && !user.death) {
			if (car.enter) {
				movePlayer(car.carSpeed);
				car.carLeft = true;
				car.carRight = false;
				car.accelerate = true;
				car.decelerate = false;
			}

			if (supercar.enter) {
				movePlayer(supercar.carSpeed);
				supercar.carLeft = true;
				supercar.carRight = false;
				supercar.accelerate = true;
				supercar.decelerate = false;
			}

			if (plane.enter) {
				movePlayer(plane.planeSpeed);
				plane.accelerate = true;
				plane.decelerate = false;
				plane.planeRight = false;
				plane.planeLeft = true;
			}

			for (int j = 0; j < rbullets.length; j++) {
				rbullets[j].Right = false;
			}

			rifle.gunR = false;
			rifle.gunL = true;
			user.turnLeft = true;
			user.turnRight = false;
			user.animateLeft = true;
			user.animateRight = false;

			iron.blast = false;

			if (!shield.activateShield) {
				iron.ableToTurbo_LEFT = true;
			}
			// ground
			if (iron.track == true && iron.armorPosY >= 870 && !car.enter && !plane.enter) {
				movePlayer(6);
			}
			if (!iron.track && !car.enter && !plane.enter && !supercar.enter) {
				movePlayer(user.walkingspeed);
				iron.armorspeed = 2;
			}

			// air
			if (iron.track == true && iron.armorPosY < 870 && !car.enter && !plane.enter) {

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
			if (car.enter) {
				car.carLeft = false;
				car.carRight = true;
				car.accelerate = true;
				car.decelerate = false;
			}

			if (supercar.enter) {
				supercar.carLeft = false;
				supercar.carRight = true;
				supercar.accelerate = true;
				supercar.decelerate = false;
			}

			if (plane.enter) {
				plane.accelerate = true;
				plane.decelerate = false;
				plane.planeRight = true;
				plane.planeLeft = false;
				plane.rise = true;
				plane.fall = false;
			}

			for (int j = 0; j < rbullets.length; j++) {
				rbullets[j].Right = true;
			}

			rifle.gunR = true;
			rifle.gunL = false;
			user.turnLeft = false;
			user.turnRight = true;
			user.animateRight = true;
			user.animateLeft = false;
			iron.blast = false;

			if (!shield.activateShield) {
				iron.ableToTurbo = true;
			}
			// ground
			if (iron.track == true && iron.armorPosY >= 870 && !car.enter && !plane.enter) {
				movePlayer(-6);
			}
			if (!iron.track && !car.enter && !plane.enter && !supercar.enter) {
				movePlayer(-user.walkingspeed);
			}
			// air
			if (iron.track == true && iron.armorPosY < 870 && !car.enter && !plane.enter) {

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

			if (car.enter) {
				car.decelerate = true;
				car.accelerate = false;
			}

			if (plane.enter) {
				plane.decelerate = true;
				plane.accelerate = false;
				plane.rise = false;
				plane.fall = true;
			}

			if (supercar.enter) {
				supercar.decelerate = true;
				supercar.accelerate = false;
			}
		}

		if (i == KeyEvent.VK_A) {

			iron.flyIMG_LEFT = false;
			iron.ableToTurbo_LEFT = false;
			user.animateLeft = false;

			if (user.y <= 865) {
				iron.fire = true;
			}

			if (car.enter) {
				car.decelerate = true;
				car.accelerate = false;
			}

			if (supercar.enter) {
				supercar.decelerate = true;
				supercar.accelerate = false;
			}

			if (plane.enter) {
				plane.decelerate = true;
				plane.accelerate = false;
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
		Rectangle Car = car.bounds();
		Rectangle superCar = supercar.bounds();
		Rectangle aircraft = plane.bounds();

		Rectangle clicker = click.bounds();

		for (int j = 0; j < inventory.length; j++) {

			Rectangle slot = inventory[j].bounds();

			inventory[j].drawselect = false;
			if (clicker.intersects(slot) && inventory[j].draw) {
				inventory[j].drawselect = true;
			}
		}

		if (rifle.ready) {

			for (int i = 0; i < bullets.length; i++) {
				Rectangle BRec = bullets[i].bounds();
				if (BRec.intersects(boss) && bullets[i].bulletFire) {
					bullets[i].bulletFire = false;
					bullets[i].letdestroy = true;
					destroyer.knockback = true;
					if (destroyer.healthcount > 0) {
						destroyer.healthcount -= bullets[i].damageForBoss;
					}
				}
			}

			for (int i = 0; i < rbullets.length; i++) {
				Rectangle BRec = rbullets[i].bounds();
				if (BRec.intersects(boss) && rbullets[i].bulletFire) {
					rbullets[i].bulletFire = false;
					rbullets[i].letdestroy = true;
					destroyer.knockback = true;
					if (destroyer.healthcount > 0) {
						destroyer.healthcount -= rbullets[i].damageForBoss;
					}
				}
			}

			for (int k = 0; k < player.length; k++) {

				Rectangle npc = player[k].bounds();

				for (int j = 0; j < rbullets.length; j++) {
					Rectangle riflebullet = rbullets[j].bounds();
					if (riflebullet.intersects(npc)) {
						if (rifle.gunR) {
							player[k].knockbackR = true;
							player[k].knockbackL = false;
						}
						if (rifle.gunL) {
							player[k].knockbackR = false;
							player[k].knockbackL = true;
						}

						rbullets[j].bulletFire = false;
						rbullets[j].letdestroy = true;
						if (player[k].healthcount > 0) {
							player[k].healthcount -= rbullets[j].damage;
						}
					}
				}
			}
		}

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

		if (human.intersects(Car)) {
			car.canEnter = true;
		} else {
			car.canEnter = false;
		}

		if (human.intersects(aircraft)) {
			plane.canEnter = true;
		} else {
			plane.canEnter = false;
		}

		if (human.intersects(superCar)) {
			supercar.canEnter = true;
		} else {
			supercar.canEnter = false;
		}

			Rectangle ground = floor.bounds();
			if (Car.intersects(ground)) {
				car.fallingFactor = 0;
				car.yspeed = 0;
				car.gravityActivate = false;
				if (car.y > 850) {
					car.y--;
				}

			}
			if (superCar.intersects(ground)) {
				supercar.fallingFactor = 0;
				supercar.yspeed = 0;
				supercar.gravityActivate = false;
				if (supercar.y > 860) {
					supercar.y--;
				}

			}
			
			if (human.intersects(ground)) {
				onGround = true;
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
			if(user.y + user.height < floor.YGround) {
				onGround = false;
			}
		

	}

	public void mouseDragged(MouseEvent e) {
		if (rifle.ready && !inventory[4].drawselect) {
			rifle.gunClicked = true;
		}
	}

	public void mouseMoved(MouseEvent e) {
		click.x = e.getX();
		click.y = e.getY();

	}

	public void mouseClicked(MouseEvent arg0) {
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i].drawselect) {

				if (i == 0) {
					user.holdingWeapon = true;
					rifle.ready = true;
					inventory[i].selected = true;
				}

				if (i == 4) {
					user.holdingWeapon = false;
					rifle.ready = false;
					inventory[0].selected = false;
				}

			}
		}
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {
		if (rifle.ready && !inventory[4].drawselect) {
			rifle.gunClicked = true;
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		if (rifle.ready) {
			rifle.gunClicked = false;
		}
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
