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
			if (input.equals("[N]+")) {
				playing = false;
				break;
			}
			else {
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
			
			boolean playing = true;
			while (playing) {
				playing = playerTurn(dealer, player, sc);
				if (playing == true) {
					dealer.setCardVisible(true);
					displayHands(dealer, player);
					dealerTurn(dealer, player, sc, playing);
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
		else if (dValue < 21 && pValue == 21) {
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
	
	public boolean playerTurn (Dealer dealer, Player player, Scanner sc) {
		boolean hitting = true;
		boolean continuing = true;
		//CONTINUOUS LOOP FOR PLAYER TO KEEP HITTING UNTIL THEY STOP
		while (hitting) {
			System.out.println("(H)it or (S)tay?");
			String choice = sc.next();
			sc.nextLine();
			//ADD A PLAYER CARD TO THE HAND IF PLAYER HITS
			if (choice.toUpperCase().matches("[H]+")) {
				player.addCard(dealer.deal());
				//NEXT TWO LINES SET THE WHILE VALUES 
				//BASED ON RETURN FROM checkValue() 
				displayHands(dealer, player);
				hitting = checkValue(player);
				continuing = checkValue(player);
				if (hitting == false && continuing == false) {
					break;
				}
			}
			//STOP ADDING CARDS TO PLAYER HAND IF PLAYER STAYS
			else if (choice.toUpperCase().matches("[S]+")) {
				System.out.println("Dealer's turn.");
				System.out.println();
				hitting = false;
				break;
			}
			//REMIND THE PLAYER TO CHOOSE {H, S} IF THEY ENTER SOMETHING ELSE
			else {
				System.out.println("Please choose to (H)it or (S)tay.");
			}
		}
		return continuing;

	}
	
	public boolean dealerTurn (Dealer dealer, Player player, Scanner sc, boolean playing) {
		boolean continuing = true;
		//CONTINUOUS LOOP FOR DEALER TO KEEP HITTING UNTIL THEY BUST OR BEAT PLAYER
		while (dealer.getHandValue() < player.getHandValue()) {
			dealer.addCard(dealer.deal());
			displayHands(dealer, player);
			continuing = checkValue(dealer, player);
		}
		return continuing;
	}

}