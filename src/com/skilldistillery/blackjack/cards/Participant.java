package com.skilldistillery.blackjack.cards;

import java.util.List;

abstract class Participant {

	protected Hand hand = new Hand();
	private int aceCount = 0;
	private int handValue = 0 - (aceCount * 10);

	public void addCard(Card card) {
		hand.addCard(card);
		handValue += card.getValue();
	}

	public int getHandValue() {
		return handValue;
	}
	
	private void setHandValue() {
		this.handValue -= 10;
	}
	
	public List<Card> getHand() {
		return hand.getHand();
	}

	public boolean hasAce(List<Card> hand) {
		boolean ace = false;
		for (Card card : this.getHand()) {
			if (card.getRank().getLabel() == "A") {
				ace = true;
			}
		}
		return ace;
	}
	
	public void setSoftAces(List<Card> hand) {
		int aceCount = 0;
		this.handValue += this.aceCount * 10;
		for (Card card : hand) {
			if (card.getRank().getLabel() == "A") {
				aceCount += 1;
				this.aceCount = aceCount;
			}
		}
		while (this.getHandValue() > 21 && aceCount > 0) {
			this.setHandValue();
			aceCount -= 1;
		}
	}
}
