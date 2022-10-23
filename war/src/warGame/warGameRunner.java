package warGame;

import java.util.ArrayList;
import java.util.Scanner; 

import warGame.Deck;
import warGame.hand;

public class warGameRunner {

	int round = 1;
	
	int dealtcard1value;
	int dealtcard2value;

	int finalWarCard1;
	int finalWarCard2;
	
	boolean winner;
	
	card dealtcard1;
	card dealtcard2;

	card wartimecard1P1;
	card wartimecard2P1;
	card wartimecard3P1;
	card wartimecard4P1;

	card wartimecard1P2;
	card wartimecard2P2;
	card wartimecard3P2;
	card wartimecard4P2;

	ArrayList<card> woncards1 = new ArrayList<card>();
	ArrayList<card> woncards2 = new ArrayList<card>();
	
	Scanner scan = new Scanner(System.in);
	Deck deck = new Deck();
	hand p1 = new hand();
	hand p2 = new hand();
	
	public warGameRunner() {

		deck.shuffle();
		
		for (int i = 0; i < 26; i++) {
			p1.addCard(deck.deal());
			p2.addCard(deck.deal());
		}
		winner = false;

		while (!winner) {

			if(!p1.emptyDeck()) {
				//dealtcard1value = p1.currentValue();
				dealtcard1 = p1.dealHand();
				dealtcard1value = dealtcard1.currentValue();
			}
			else {
				System.out.println("pile 1 empty" + "\n");
				getFromPile1();
				if(p1.emptyDeck()) {
					winner = true;
					break;
				}
				//dealtcard1value = p1.currentValue();
				dealtcard1 = p1.dealHand();
				dealtcard1value = dealtcard1.currentValue();
				
			}
			if(!p2.emptyDeck()) {
				//dealtcard2value = p2.currentValue();
				dealtcard2 = p2.dealHand();
				dealtcard2value = dealtcard2.currentValue();
			}
			else {
				System.out.println("pile 2 empty" + "\n");
				getFromPile2();
				if(p2.emptyDeck()) {
					winner = true;
					break;
				}
				//dealtcard2value = p2.currentValue();
				dealtcard2 = p2.dealHand();
				dealtcard2value = dealtcard2.currentValue();
				
			}
			
			divide();
			System.out.println("Round " + round + "\n");
			System.out.println("Player 1: " + dealtcard1);
			System.out.println("Player 2: " + dealtcard2);
			if(!winnerCheck(p1, p2, dealtcard1value, dealtcard2value))
				warCheck(p1, p2, dealtcard1value, dealtcard2value);
			System.out.println("P1 Cards: " + (p1.cardNumber() + woncards1.size()) + "\n" + "P2 Cards: " + (p2.cardNumber()  + woncards2.size()));
			divide();
		
			if(p1.cardNumber() + woncards1.size() <= 0) {
				winner = true;
				System.out.println("PLAYER 2 WINS");
				break;
			}
			if(p2.cardNumber() + woncards2.size() <= 0) {
				winner = true;
				System.out.println("PLAYER 1 WINS");
				break;
			}
		
			System.out.println("Enter any key to continue");
			scan.nextLine();
			round++;
		}
	}
	
	public card dealCardPile1(){
		return woncards1.remove(0);
	}
	
	public card dealCardPile2(){
		return woncards2.remove(0);
	}
	
	public boolean winnerCheck(hand hand1, hand hand2, int p1, int p2) {

		if (p1 > p2) {
			System.out.println("Winner: Player 1");
			woncards1.add(dealtcard2);
			woncards1.add(dealtcard1);
			return true; //we have a winner for this round

		}
		if (p1 < p2) {
			System.out.println("Winner: Player 2");
			woncards2.add(dealtcard1);
			woncards2.add(dealtcard2);
			return true;
		}
		return false;
	}

	public void divide() {
		for (int i = 0; i < 430; i++) {
			System.out.print("_");
		}
		System.out.println();
	}

	public void warCheck(hand hand1, hand hand2, int p1, int p2) {
		if (p1 == p2) {
			System.out.println("Result: War!");

			if ((hand1.cardNumber() + woncards1.size()) < 4) {
				//player2 wins
				winner = true;
				hand2.addCard(dealtcard1);
				hand2.addCard(dealtcard2);
				hand2.copyCards(woncards1);
				woncards1.clear();
				hand2.copyCards(hand1.getCards());
				hand1.clearCards();
				return;
				
			}

			if ((hand2.cardNumber() + woncards2.size()) < 4) {
				//player1 wins
				winner = true;
				hand1.addCard(dealtcard1);
				hand1.addCard(dealtcard2);
				hand1.copyCards(woncards2);
				woncards2.clear();
				hand1.copyCards(hand2.getCards());
				hand2.clearCards();
				return;
			}

			if(hand1.cardNumber() < 4) {
				getFromPile1();
			}
			if(hand2.cardNumber() < 4) {
				getFromPile2();
			}
			warDeal1(hand1);
			warDeal2(hand2);

			finalWarCard1 = wartimecard4P1.currentValue();
			finalWarCard2 = wartimecard4P2.currentValue();
			
			
				
			if (finalWarCard1 > finalWarCard2) {
					System.out.println("WARTIME - P1: " + wartimecard4P1);
					System.out.println("WARTIME - P2: " + wartimecard4P2);
					System.out.println("PLAYER 1 WINS THE WAR");

					woncards1.add(dealtcard1);
					woncards1.add(wartimecard1P1);
					woncards1.add(wartimecard2P1);
					woncards1.add(wartimecard3P1);
					woncards1.add(wartimecard4P1);
					woncards1.add(dealtcard2);
					woncards1.add(wartimecard1P2);
					woncards1.add(wartimecard2P2);
					woncards1.add(wartimecard3P2);
					woncards1.add(wartimecard4P2);
					
				} else if (finalWarCard1 < finalWarCard2){
					System.out.println("WARTIME - P1: " + wartimecard4P1);
					System.out.println("WARTIME - P2: " + wartimecard4P2);
					System.out.println("PLAYER 2 WINS THE WAR");

					woncards2.add(dealtcard1);
					woncards2.add(wartimecard1P1);
					woncards2.add(wartimecard2P1);
					woncards2.add(wartimecard3P1);
					woncards2.add(wartimecard4P1);
					woncards2.add(dealtcard2);
					woncards2.add(wartimecard1P2);
					woncards2.add(wartimecard2P2);
					woncards2.add(wartimecard3P2);
					woncards2.add(wartimecard4P2);
					
				}
		
			if(finalWarCard1 == finalWarCard2) {
				//Given back cards
				woncards1.add(dealtcard1);
				woncards1.add(wartimecard1P1);
				woncards1.add(wartimecard2P1);
				woncards1.add(wartimecard3P1);
				woncards1.add(wartimecard4P1);
				
				woncards2.add(dealtcard2);
				woncards2.add(wartimecard1P2);
				woncards2.add(wartimecard2P2);
				woncards2.add(wartimecard3P2);
				woncards2.add(wartimecard4P2);
				
				System.out.println("WARTIME - P1: " + wartimecard4P1);
				System.out.println("WARTIME - P2: " + wartimecard4P2);
				System.out.println("War Cards: Same Value - Round Cancelled");
				return;
			}
		}
	}

	public void warDeal1(hand hand1) {
		wartimecard1P1 = hand1.dealHand();
		wartimecard2P1 = hand1.dealHand();
		wartimecard3P1 = hand1.dealHand();
		wartimecard4P1 = hand1.dealHand();
	}
	public void warDeal2(hand hand2) {
		wartimecard1P2 = hand2.dealHand();
		wartimecard2P2 = hand2.dealHand();
		wartimecard3P2 = hand2.dealHand();
		wartimecard4P2 = hand2.dealHand();
	}

	public void getFromPile1() {
		p1.copyCards(woncards1);
		woncards1.clear();
		p1.shuffle();
	}
	public void getFromPile2() {
		p2.copyCards(woncards2);
		woncards2.clear();
		p2.shuffle();
	}

	public void copyCards(ArrayList<card>arrayPileFrom, ArrayList<card>arrayPileTo) {
		for (int i = 0; i < arrayPileFrom.size(); i++) {
			arrayPileTo.add(arrayPileFrom.get(i));
		}
	
		arrayPileFrom.clear();
	}
}