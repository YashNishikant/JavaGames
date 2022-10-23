
public class RaceGame {
	public static void main(String[] Args) {

		//THE TOP IMAGE (BRIGHTER GREEN) IS THE CREEPER
		//THE MIDDLE IMAGE (DARKER GREEN) IS THE ZOMBIE
		//THE GRAY SKULL AT THE BOTTOM IS THE WITHER SKELETON
		
		int countC = 0;
		int countZ = 0;
		int countW = 0;

		int limit = 682;

		int xCreeper = 0;
		int xZombie = 0;
		int xWither = 0;

		RacerCanvas race = new RacerCanvas(800, 800);
		race.setFiles("raceimg1.png", "raceimg2.png", "raceimg3.png"); // I first set the images

		race.moveRacer1(0, 100); // i first set the racers to their starting place
		race.moveRacer2(0, 300);
		race.moveRacer3(0, 500);

		int winC = 0; //These variables are going to be used to determine the overall winner.
		int winZ = 0;
		int winW = 0;
		
		
		for (int i = 0; i < 3; i++) {          //This for loop is for looping the race game 3 times

			while (xCreeper != limit || xZombie != limit || xWither != limit) {

				// 1
				race.moveRacer1(xCreeper, 100); // i first set the racers to their starting place, and the variables increase each time
				race.moveRacer2(xZombie, 300);
				race.moveRacer3(xWither, 500);

				if (xCreeper >= limit) { // sets the limit for the creeper
					xCreeper = limit;
				

				} else {
					xCreeper = xCreeper + 3;
				}

				int staggeringSpeed1 = ((int) (Math.random() * 5) + 1); // sets a wide range of speeds so the creeper won't win everytime
				if (staggeringSpeed1 == 1 && xCreeper < limit) {
					xCreeper -= 1;
				}

				if (staggeringSpeed1 == 2 && xCreeper < limit) {// sets values from 1-5
					xCreeper -= 2;
				}

				if (staggeringSpeed1 == 3 && xCreeper < limit) {
					xCreeper -= 3;
				}
				// 2
				if (xZombie >= limit) {// sets the limit for the creeper
					xZombie = limit;
		

				} else {
					xZombie = xZombie + 3;
				}

				int staggeringSpeed2 = ((int) (Math.random() * 5) + 1); // sets a wide range of speeds so the zombie won't win everytime
				if (staggeringSpeed2 == 1 && xZombie < limit) {
					xZombie -= 1;
				}

				if (staggeringSpeed2 == 2 && xZombie < limit) {// sets values from 1-5
					xZombie -= 2;
				}

				if (staggeringSpeed2 == 3 && xZombie < limit) {
					xZombie -= 3;
				}
				// 3
				if (xWither >= limit) { // sets a limit so the creeper won't pass the edge of the screen
					xWither = limit;

				} else {
					xWither = xWither + 3;
				}

				int staggeringSpeed3 = ((int) (Math.random() * 5) + 1); // sets a wide range of speeds so the zombie won't win everytime
				if (staggeringSpeed3 == 1 && xWither < limit) {
					xWither -= 1;
				}

				if (staggeringSpeed3 == 2 && xWither < limit) { // sets range from 1-5 (speed)
					xWither -= 2;
				}

				if (staggeringSpeed3 == 3 && xWither < limit) {
					xWither -= 3;
				}

				// On to the winners
				// 1
				if (xCreeper == limit && xCreeper > xZombie && xCreeper > xWither) { //if the creeper wins then-

					winC++;
					race.showText(false);
					if (xZombie > xWither) {
						race.setPlaces("The creeper is in first place, the zombie is in second place, the wither skeleton is in third place",50, 50, 17);
						//compares x coords to check which racer is ahead of the other.
					}

					if (xWither > xZombie) {
						race.setPlaces("The creeper is in first place, the wither skeleton is in second place, the zombie is in third place",50, 50, 17);
						//compares x coords to check which block is ahead of the other.
					}
					countC++;
					//adds a "win" for the creeper. This is what keeps track of the amount of wins out of 3 for creeper
				}
				// 2
				if (xZombie == limit && xZombie > xCreeper && xZombie > xWither) { //if the zombie wins then-

					winZ++;
					race.showText(false);
					if (xCreeper > xWither) {//when the creeper is in second place: the x coordinate of the creeper is greater than the x coord of the wither
						race.setPlaces("The zombie is in first place, the creeper is in second place, the wither skeleton is in third place",50, 50, 17);

					}

					if (xWither > xCreeper) {
						race.setPlaces("The zombie is in first place, the wither skeleton is in second place, the creeper is in third place",50, 50, 17);
					}

					countZ++;//adds a "win" for the zombie. This is what keeps track of the amount of wins out of 3 for zombie
				}
				// 3
				if (xWither == limit && xWither > xZombie && xWither > xCreeper) { //if the wither skeleton wins then-

					winW++;
					race.showText(false);
					if (xCreeper > xZombie) {
						race.setPlaces("The wither skeleton is in first place, the creeper is in second place, the zombie is in third place",50, 50, 17);
					}

					if (xZombie > xCreeper) {
						race.setPlaces("The wither skeleton is in first place, the zombie is in second place, the creeper is in third place",50, 50, 17);
					}

					countW++;//adds a "win" for the wither. This is what keeps track of the amount of wins out of 3 for wither
				}

				if (xCreeper == limit && xZombie == limit && xWither == limit) {

					race.showText(true);

				}

				race.repaint();
				race.delay(15);

			}

			race.delay(3000);

			xCreeper = 0;
			xZombie = 0;
			xWither = 0;
			race.showText(false);
		}

		// FINAL SCORES: who won overall?

		
		race.showText(true);// i need text to pop up for this
		race.delay(1500); //placing a small delay
		
		if (countC > countZ && countC > countW) { // if the creeper won the most, then...

			xCreeper = 350;//coordinates for where the creeper is placed
			xZombie = -200;
			xWither = -200;// the zombie and wither will be hidden since they lost

			race.moveRacer1(xCreeper, 100);
			race.moveRacer2(xZombie, 300);
			race.moveRacer3(xWither, 500);
			race.setPlaces("THE OVERALL WINNER IS... THE CREEPER",190, 50, 17);
		}


		
		if (countZ > countC && countZ > countW) {

			xCreeper = -200;
			xZombie = 350;//coordinates for where the zombie is placed
			xWither = -200;// the creeper and wither will be hidden since they lost

			race.moveRacer1(xCreeper, 200);
			race.moveRacer2(xZombie, 200);
			race.moveRacer3(xWither, 200);
			race.setPlaces("THE OVERALL WINNER IS... THE ZOMBIE",190, 50, 17);
		}

		
		
		if (countW > countC && countW > countZ) {

			xCreeper = -200;
			xZombie = -200;// creeper and zombie will be hidden
			xWither = 350;//coords for wither

			race.moveRacer1(xCreeper, 200);
			race.moveRacer2(xZombie, 200);
			race.moveRacer3(xWither, 200);
			race.setPlaces("THE OVERALL WINNER IS... THE WITHER SKELETON",190, 50, 17);
		}
		
		
		if (countW == countC && countW == countZ && countC == countZ) { //added a condition for a tie. If every racer wins once, then a three way tie will occur

			xCreeper = 10;
			xZombie = 350;
			xWither = 650;// coords for all racers to be shown on the screen

			race.moveRacer1(xCreeper, 200);
			race.moveRacer2(xZombie, 200);
			race.moveRacer3(xWither, 200);
			race.setPlaces("THE OVERALL WINNER IS... ALL THREE! IT'S A TIE",190, 50, 17);
		}
		race.repaint();

	}

}
