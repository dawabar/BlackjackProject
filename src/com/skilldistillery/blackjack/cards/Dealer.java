package com.skilldistillery.blackjack.cards;

public class Dealer extends Participant {

	private Deck deck;

	public Dealer() {
		super();
	}

	public void buildDeck() {
		deck = new Deck();
		deck.shuffle();
	}

	public Card deal() {
		return deck.dealCard();
	}

	public void setCardVisible(boolean b) {
		hand.getCard(0).setVisible(b);
	}

}
