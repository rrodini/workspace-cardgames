package com.cardtech.web

import com.cardtech.web.Player
import com.cardtech.web.PokerGame

class GameParticipation {
	String outcome;   // 'W', 'T', 'L'

    static constraints = {
		player ()
		pokerGame ()
		outcome (inList: ['W', 'T', 'L'])
    }
	
	static belongsTo = [player: Player, pokerGame: PokerGame]
	
}
