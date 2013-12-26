package com.cardtech.web

class PokerDeal {
	
	Player player

    static constraints = {
		pokerGame ()
		pokerHand ()
		player ()
    }
	
	static belongsTo = [pokerGame: PokerGame, pokerHand: PokerHand]
	
	
}
