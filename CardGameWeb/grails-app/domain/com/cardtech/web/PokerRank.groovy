package com.cardtech.web

class PokerRank {
	String name

	static constraints = {
		name(blank: false, matches: /[A-Z]+[_[A-Z]+]*/)  // e.g. HIGH_CARD up to ROYAL_FLUSH
	}
	
	String toString() {
		return name
	}
}
