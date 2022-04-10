package com.skilldistillery.blackjack;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.skilldistillery.blackjack.cards.Card;
import com.skilldistillery.blackjack.cards.Color;
import com.skilldistillery.blackjack.cards.Dealer;
import com.skilldistillery.blackjack.cards.Player;

public class BlackjackApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		BlackjackApplication app = new BlackjackApplication();
//		app.run(sc);
		app.playAgain(sc);
		sc.close();

	}

	public void playAgain(Scanner sc) {
		boolean playing = true;
		do {
			run(sc);
			System.out.println();
			System.out.print("Do you want to play again? (Y)es or (N)o: ");
			String input = sc.next();
			sc.nextLine();
			System.out.println();
			if (input.toUpperCase().equals("[N]+")) {
				playing = false;
				break;
			} 
			else if (input.toUpperCase().equals("[Y]+")) {
				playing = true;
				continue;
			}
			else {
				System.out.println("Please choose (Y)es or (N)o to play again.");
				continue;
			}
		} while (playing == true);
	}

	public void run(Scanner sc) {

		Dealer dealer = new Dealer();
		Player player = new Player();

		dealer.buildDeck();
		player.addCard(dealer.deal());
		dealer.addCard(dealer.deal());
		dealer.setCardVisible(false);
		player.addCard(dealer.deal());
		dealer.addCard(dealer.deal());
		displayHands(dealer, player);
		if (checkBlackJack(dealer, player) == true) {

			boolean[] checkIterations = { true, true };
			while (checkIterations[1]) {
				if (checkIterations[0] == false) {
					break;
				}
				checkIterations = playerTurn(dealer, player, sc);
				if (checkIterations[1] == true) {
					dealer.setCardVisible(true);
					displayHands(dealer, player);
					checkIterations = dealerTurn(dealer, player, sc);
				}
			}
		}
	}

	public void displayHands(Dealer d, Player p) {

		System.out.println("DEALER                  PLAYER");
		int spaces = 24;
		for (Card card : d.getHand()) {
			if (card.isVisible() == false) {
				spaces -= 3;
				System.out.print("\u25AE\u25AE ");
			} else if (card.getSuit().getColor() == Color.RED) {
				spaces -= 3;
				System.out.print(card.getRank().getLabel() + card.getSuit() + " ");
			} else {
				spaces -= 3;
				System.out.print(card.getRank().getLabel() + card.getSuit() + " ");
			}
		}
		for (int i = 0; i < spaces; i++) {
			System.out.print(" ");
		}
		for (Card card : p.getHand()) {
			if (card.getSuit().getColor() == Color.RED) {
				System.out.print(card.getRank().getLabel() + card.getSuit() + " ");
				System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
			} else {
				System.out.print(card.getRank().getLabel() + card.getSuit() + " ");
			}
		}
		System.out.println();
		System.out.println(d.getHandValue() + "                      " + p.getHandValue());
	}

	public boolean checkBlackJack(Dealer d, Player p) {
		int dValue = d.getHandValue();
		int pValue = p.getHandValue();
		if (dValue == 21 && pValue == 21) {
			d.setCardVisible(true);
			displayHands(d, p);
			System.out.println("Game ends in a draw.");
			return false;
		} else if (dValue == 21 && pValue < 21) {
			d.setCardVisible(true);
			displayHands(d, p);
			System.out.println("Blackjack. Dealer wins.");
			return false;
		} else if (dValue < 21 && pValue == 21) {
			d.setCardVisible(true);
			displayHands(d, p);
			System.out.println("Blackjack! Player wins!");
			return false;
		} else {
			return true;
		}
	}

	public boolean checkValue(Player p, Dealer d) {
		int pValue = p.getHandValue();
		if (pValue > 21) {
//			if (p.hasAce(p.getHand()) == true) {
//				p.setSoftHand();
			p.hasAce(p.getHand());
			return true;
			} else if {
				d.setCardVisible(true);
				displayHands(d, p);
				System.out.println("Bust. Dealer wins.");
				return false;
			}
		} else {
			return true;
		}
	}

	public boolean checkValue(Dealer d, Player p) {
		int dValue = d.getHandValue();
		int pValue = p.getHandValue();
		if (dValue > 21) {
			if (d.hasAce(d.getHand()) == true) {
				d.setSoftHand();
				return true;
			} else {
				System.out.println("Bust! Player wins!");
				return false;
			}
		} else if (dValue > pValue) {
			System.out.println("Dealer wins.");
			return false;
		} else if (dValue == pValue) {
			System.out.println("Game ends in a draw.");
			return false;
		} else {
			return true;
		}
	}

	public boolean[] playerTurn(Dealer dealer, Player player, Scanner sc) {
		boolean[] bools = new boolean[2];
		boolean hitting = true;
		boolean continuing = true;
		boolean playerSoftAce = player.hasAce(player.getHand());
		boolean dealerSoftAce = dealer.hasAce(dealer.getHand());
		// CONTINUOUS LOOP FOR PLAYER TO KEEP HITTING UNTIL THEY STOP
		while (hitting) {
			System.out.println("(H)it or (S)tay?");
			String choice = sc.next();
			sc.nextLine();
			// ADD A PLAYER CARD TO THE HAND IF PLAYER HITS
			if (choice.toUpperCase().matches("[H]+")) {
				player.addCard(dealer.deal());
				// NEXT TWO LINES SET THE WHILE VALUES
				// BASED ON RETURN FROM checkValue()
				hitting = checkValue(player, dealer);
				continuing = checkValue(player, dealer);
				displayHands(dealer, player);
				if (hitting == false && continuing == false) {
					bools[0] = hitting;
					bools[1] = continuing;
					return bools;
				}
			}
			// STOP ADDING CARDS TO PLAYER HAND IF PLAYER STAYS
			else if (choice.toUpperCase().matches("[S]+")) {
				System.out.println("Dealer's turn.");
				System.out.println();
				hitting = false;
				bools[0] = hitting;
				bools[1] = continuing;
				return bools;
			}
			// REMIND THE PLAYER TO CHOOSE {H, S} IF THEY ENTER SOMETHING ELSE
			else {
				System.out.println("Please choose to (H)it or (S)tay.");
			}
		}
		return bools;

	}

	public boolean[] dealerTurn(Dealer dealer, Player player, Scanner sc) {
		boolean[] bools = new boolean[2];
		boolean continuing = true;
		boolean hitting = false;
		if (dealer.getHandValue() >= 17 && dealer.getHandValue() < player.getHandValue()) {
			System.out.println("Player wins!");
			continuing = false;
			bools[0] = hitting;
			bools[1] = continuing;
			return bools;
		}
		else if (dealer.getHandValue() == player.getHandValue()) {
			System.out.println("Game ends in a draw.");
			continuing = false;
			bools[0] = hitting;
			bools[1] = continuing;
			return bools;
		} else if (dealer.getHandValue() > player.getHandValue()) {
			System.out.println("Dealer wins.");
			continuing = false;
			bools[0] = hitting;
			bools[1] = continuing;
			return bools;
		}
		else {
			// CONTINUOUS LOOP FOR DEALER TO KEEP HITTING UNTIL THEY BUST OR BEAT PLAYER
			do {
				dealer.addCard(dealer.deal());
				displayHands(dealer, player);
				continuing = checkValue(dealer, player);
				bools[0] = hitting;
				bools[1] = continuing;
			} while (dealer.getHandValue() < 17);
			return bools;
		}
	}

}