package com.cardtech.web

class PlayingCard {
	String name
	

    static constraints = {
		name(blank: false, matches: /[A-Z]+_[A-Z]+/)  // e.g. TWO_CLUB or ACE_HEART
    }
}
