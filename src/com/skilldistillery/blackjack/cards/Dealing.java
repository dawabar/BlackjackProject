package com.skilldistillery.blackjack.cards;

import java.util.Scanner;

public class Dealing {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();
		
		System.out.println("This app will ask you for how many cards you want dealth from a deck,");
		System.out.println("and it will deal you that many cards and display their total value.");
		System.out.println();
		System.out.print("As an integer, how many cards do you want?");
		int cards = sc.nextInt();
		sc.nextLine();
		if (cards > 0 && cards < deck.checkDeckSize()) {
			int totalValue = 0;
			for (int i = 0; i < cards; i++) {
				Card card = deck.dealCard();
				totalValue += card.getValue();
				//THE NEXT LINES WILL CHANGE THE TEXT BASED ON THE SUIT COLOR
				if (card.getSuit().getColor() == Color.BLACK) {
					System.out.println(card.toString());
				}
				else {
					System.err.println(card.toString());
				}
			}
			System.out.println("Total value of your cards: " + totalValue);
			System.out.println();
			System.out.println("Deck size remaining: " + deck.checkDeckSize());
			
		}
		else {
			System.out.println("Input must be an integer in the range (1-52)");
		}
	}
}
