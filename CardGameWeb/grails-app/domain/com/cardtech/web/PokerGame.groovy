package com.cardtech.web

class PokerGame {
	Date dateCreated
	Integer noParticipants
	PokerRank winningRank
	
    static constraints = {
		
		dateCreated ()
		noParticipants (blank:false, maxSize:10)
		winningRank ()
    }
	
	static hasMany = [gamesParticipants: GameParticipation, deals: PokerDeal]
	
//	static mapping = {
//		id generator: 'sequence'
//	}
	
}
