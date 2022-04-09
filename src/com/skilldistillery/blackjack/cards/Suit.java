package com.skilldistillery.blackjack.cards;

public enum Suit {
  HEARTS("\u2665", Color.RED), SPADES("\u2660", Color.BLACK), CLUBS("\u2663", Color.BLACK), DIAMONDS("\u2666", Color.RED);
  private String name;
  private Color color;

  Suit(String name, Color color) {
    this.name = name;
    this.color = color;
  }
  
  public Color getColor() {
	  return color;
  }

  @Override
  public String toString() {
    return name;
  }
}