package com.skilldistillery.blackjack;

import java.util.Scanner;

import com.skilldistillery.blackjack.cards.Card;
import com.skilldistillery.blackjack.cards.Color;
import com.skilldistillery.blackjack.cards.Dealer;
import com.skilldistillery.blackjack.cards.Player;

public class BlackjackApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);


		BlackjackApplication app = new BlackjackApplication();
		app.run(sc);
		sc.close();

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
			
			boolean playing = true;
			while (playing) {
				boolean hitting = true;
				while (hitting) {
					System.out.println("(H)it or (S)tay?");
					String choice = sc.next();
					sc.nextLine();
					if (choice.toUpperCase().matches("[H]+")) {
						player.addCard(dealer.deal());
						hitting = checkValue(player);
						playing = checkValue(player);
						displayHands(dealer, player);
					}
					else if (choice.toUpperCase().matches("[S]+")) {
						System.out.println("Dealer's turn.");
						System.out.println();
						hitting = false;
					}
					else {
						System.out.println("Please choose to (H)it or (S)tay.");
					}
				}
				dealer.setCardVisible(true);
				displayHands(dealer, player);
				while (dealer.getHandValue() < player.getHandValue()) {
					dealer.addCard(dealer.deal());
					displayHands(dealer, player);
					playing = checkValue(dealer, player);
				}
			}
		}
	} 

	public void displayHands (Dealer d, Player p) {

		System.out.println("DEALER\t\t\tPLAYER");
		for (Card card : d.getHand()) {
			if (card.isVisible() == false) {
				System.out.print("\u25AE\u25AE ");
			}
			else if (card.getSuit().getColor() == Color.RED) {
			System.out.print(card.getRank().getLabel() + card.getSuit() + " ");
			}
			else {
				System.out.print(card.getRank().getLabel() + card.getSuit() + " ");
			}
		}
		System.out.print("\t\t\t");
		for (Card card : p.getHand()) {
			if (card.getSuit().getColor() == Color.RED) {
			System.out.print(card.getRank().getLabel() + card.getSuit() + " ");
			}
			else {
				System.out.print(card.getRank().getLabel() + card.getSuit() + " ");
			}
		}
		System.out.println();
		
	}
	
	public boolean checkBlackJack (Dealer d, Player p) {
		int dValue = d.getHandValue();
		int pValue = p.getHandValue();
		if (dValue == 21 && pValue == 21) {
			System.out.println("Game ends in a draw.");
			return false;
		}
		else if (dValue == 21 && pValue < 21) {
			System.out.println("Dealer wins.");
			return false;
		}
		else if (pValue == 21 && dValue < 21) {
			System.out.println("Blackjack! Player wins!");
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean checkValue (Player p) {
		int pValue = p.getHandValue();
		if (pValue > 21) {
			System.out.println("Bust! Dealer wins.");
			return false;
		}
		else {
			return true;
		}
	}
	
	public boolean checkValue (Dealer d, Player p) {
		int dValue = d.getHandValue();
		int pValue = p.getHandValue();
		if (dValue > 21) {
			System.out.println("Bust! Player wins!");
			return false;
		}
		else if (dValue > pValue) {
			System.out.println("Dealer wins.");
			return false;
		}
		else {
			return true;
		}
	}

}