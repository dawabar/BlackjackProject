package com.skilldistillery.blackjack.cards;

import java.util.List;

abstract class Participant {

	protected Hand hand = new Hand();
	protected int handValue = 0;

	public void addCard (Card card) {
		hand.addCard(card);
		handValue += card.getValue();
	}
	
	public int getHandValue() {
		return handValue;
	}
	
	public List<Card> getHand() {
		return hand.getHand();
	}

}
