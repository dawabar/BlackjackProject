package com.skilldistillery.blackjack.cards;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private List<Card> hand = new ArrayList<>();
//	private boolean hard = true;

	public Hand() {
	}

	public void addCard(Card card) {
		hand.add(card);
	}
	
	public Card getCard(int c) {
		return hand.get(c);
	}
	
	public List<Card> getHand() {
		return hand;
	}
	
	public void setVisible (boolean b) {
//		Card card = hand.get(0);
		hand.get(0).setVisible(b);
//		card.isVisible(b);
	}
		
}
