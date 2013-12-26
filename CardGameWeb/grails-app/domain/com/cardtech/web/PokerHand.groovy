package com.cardtech.web

import com.cardtech.game.PokerRankWithHighCards

class PokerHand {
	PlayingCard c1
	PlayingCard c2
	PlayingCard c3
	PlayingCard c4
	PlayingCard c5
	PokerRank   rank
	PlayingCard breaker1  // Should be at least one breaker but others may be null
	PlayingCard breaker2
	PlayingCard breaker3
	PlayingCard breaker4
	PlayingCard breaker5	

    static constraints = {
		c1 (nullable: false)
		c2 (nullable: false)
		c3 (nullable: false)
		c4 (nullable: false)
		c5 (nullable: false)
		breaker1 (nullable: true)
		breaker2 (nullable: true)
		breaker3 (nullable: true)
		breaker4 (nullable: true)
		breaker5 (nullable: true)
    }
	
	static hasMany = [pokerDeal: PokerDeal]
	
}
