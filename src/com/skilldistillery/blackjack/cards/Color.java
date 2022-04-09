package com.skilldistillery.blackjack.cards;

public enum Color {
	RED("\033[0;31m"), BLACK("\033[0m");
	
	private final String code;
	
	Color(String s) {
		this.code = s;
	}
	
    @Override
    public String toString() {
        return code;
    }

}
