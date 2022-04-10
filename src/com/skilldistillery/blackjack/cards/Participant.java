package com.skilldistillery.blackjack.cards;

import java.util.List;

abstract class Participant {

	protected Hand hand = new Hand();
	private int handValue = 0;
	private int aceCount = 0;

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
		int countAcesOnce = 0;
		if (this.getHandValue() > 21) {
			countAcesOnce = setSoftAces(int countAcesOnce);
			ace = true;
			return ace;
		}
			
		}
//		while (this.handValue > 21) {
//			for (Card card : this.getHand()) {
//				if (card.getRank().getLabel() == "A") {
//					countAcesOnce += 1;
//					ace = true;
//				}
//			}
//			if (countAcesOnce > this.aceCount) {
////			for (int i = 0; i < (countAcesOnce - this.aceCount); i++) {
////				incrementAces();
//				this.aceCount += 1;
////			}
//			}
//			setSoftHand
//		}
		return ace;
	}
	
	private int setSoftAces(int aceCount) {
		while (this.handValue > 21) {
			for (Card card : this.getHand()) {
				if (card.getRank().getLabel() == "A") {
					aceCount += 1;
				}
			}
		}
			setSoftHand();
			return aceCount;
	}

//	public void incrementAces() {
//		this.aceCount += 1;		
//	}

//	public int setSoftHand() {
	public void setSoftHand() {
//		return (handValue - (this.aceCount * 10));
		this.aceCount += 1;
		setHandValue();
	}

}
