package com.cardtech.web

import com.cardtech.core.Card
import com.cardtech.game.Player
import com.cardtech.game.PokerPlayer
import com.cardtech.game.PokerGame
import com.cardtech.game.Hand
import com.cardtech.web.PokerGame
import com.cardtech.web.GameParticipation


class PokerGameService {
	
	static scope = 'prototype'
	static transactional = true // true => each method below constitutes an atomic transaction.
	def sessionFactory	// this is to get the hibernate session

    def playGames(List<Player> playerObjs, int numOfGames) { // playerObjs is a list of domain objects
		// 
		def result
		Date firstGame = new Date();  // this is the timestamp for the first game
		// To get all games ever played set firstGame via the SELECT below
		//def firstGame = com.cardtech.web.PokerGame.executeQuery("select min(dateCreated) from PokerGame")
		//firstGame = firstGame[0]
		for (int i=0; i < numOfGames; i++) {
			result = playSingleGame(playerObjs)
		}
		if (numOfGames == 1) {
			return result
		} else {
			// return a summary of the games played
			// get SQL timestamp of last game (also a Date) from the database
			def lastGame = com.cardtech.web.PokerGame.executeQuery("select max(dateCreated) from PokerGame")
			// above returns an array list of timestamps.
			lastGame = lastGame[0]
			def criteria = com.cardtech.web.GameParticipation.createCriteria()
			// get GameParticipation outcomes between two dates
			def outcomeList = criteria.list{
					pokerGame {
						between ('dateCreated', firstGame, lastGame)
					}
				}
			def playerWins = [:]
			outcomeList.each { game -> 
println "GP: ${game.id}, ${game.player.id}, ${game.outcome} "
				int win = (game.outcome == 'W')? 1 : 0

				playerWins.put(game.player.id, playerWins.get(game.player.id,0)+win) 
			}
			// find the highest winning rank in poker games played between timestamps for given player
		    def playerHighestWinningRank = [:]
		    playerObjs.each { playerObj ->
println "Determine highest rank for playerId: ${playerObj.id}"
// Hibernate query below doesn't work.  Don't know why.
//				def query = hibSession.createQuery(
//"select max(PG.winningRank.id) from com.cardtech.web.PokerGame as PG inner join PG.gamesParticipants as GP where (PG.dateCreated between :start and :end) and (GP.player.id = :playerId and GP.outcome = 'W')")
// 				query.setDate('start', firstGame)
//				query.setDate('end',   lastGame)
//				query.setLong('playerId', playerObj.id)
//				def rankList = query.list()
				def rankList = com.cardtech.web.PokerGame.executeQuery(
					"select max(PG.winningRank.id) from com.cardtech.web.PokerGame as PG inner join PG.gamesParticipants as GP where (PG.dateCreated between :start and :end) and (GP.player.id = :playerId and GP.outcome = 'W')",
					[start: firstGame, end: lastGame, playerId: playerObj.id ])
				def rankId = null
				// query returns one record containing a PokerRank id.
				if (rankList?.size() > 0) {
					rankId = rankList?.get(0)
					playerHighestWinningRank.putAt(playerObj.id, rankId)
					println "Highest rank: playerId: ${playerObj.id}, ${rankId}"
				}
		    }
			// return a map
			return [numOfGames: numOfGames, playerObjs: playerObjs, playerWins: playerWins, 
				    playerHighestWinningRank: playerHighestWinningRank]
// Below is sample of results map
//		def results = [numOfGames: numOfGames,
//		   playerObjs: playerObjs, 
//           playerWins: [1L: 2, 3L:3, 4L:1], 
//		   playerHighestWinningRank: [1L: 6L, 3L:3L, 4L:2L] ]
//
		}
    }
	
	
	
	// playSingleGame returns a map used by the showGame view
	def playSingleGame(List<Player> playerObjs) {
		// Convert domain objects to card.cardtech.game.Player objects
		com.cardtech.game.PokerPlayer [] players = new PokerPlayer[playerObjs.size()]
		playerObjs.eachWithIndex { p,i ->
			players[i] = new com.cardtech.game.PokerPlayer(p.firstName)
		}
		// create new PokerGame with given players
		com.cardtech.game.PokerGame game = new com.cardtech.game.PokerGame(players)
		// shuffle the cards before playing a game
		game.initialize(true)
		game.play()
		// get the player's hands for display
		List<com.cardtech.game.Hand> hands = game.getHands()
		// get the winners.  Usually just one but there can be ties
		List<com.cardtech.game.Player> winners = game.getWinner()
		// get the names of the winners
		List<String> winnerNames = winners.collect { it.name }
		// get the winning rank
		com.cardtech.game.PokerRank winningRank = game.getWinningRank()
		// convert that rank to a domain object
		com.cardtech.web.PokerRank winningRankObj = com.cardtech.web.PokerRank.findByName(winningRank.toString())
		// now persist all of the game data to domain objects realizing that List of players and the List of poker hands
		// are "parallel" arrays, i.e. player[0] has pokerhand[0]
		// create domain object PokerGame
		com.cardtech.web.PokerGame gameObj = new com.cardtech.web.PokerGame()
		gameObj.noParticipants = playerObjs.size()
		gameObj.winningRank = winningRankObj
		if (gameObj.validate()) {
			gameObj.save(failOnError: true, flush: true)
		} else {
			log.debug "gameObj not valid: ${gameObj.getErrors()}"
		}
		// create GameParticipation associative records
		log.debug "writing game participation records"
		playerObjs.eachWithIndex { p, pIndex ->
			GameParticipation partObj = new GameParticipation()
			partObj.player = p
			partObj.pokerGame = gameObj
			log.debug "player id: ${p.id}"
			log.debug "game   id: ${gameObj.id}"
			int noWinners = winners.size()
			if (winnerNames.contains(p.firstName)) {
				partObj.outcome = (noWinners == 1)? "W" : "T"
			 } else {
				 partObj.outcome = "L"
			 }
			if (partObj.validate()) {
				partObj.save(flush:true)
			} else {
				log.debug "partObj not valid: ${partObj.getErrors()}"
			}
			// create PokerHand records
			PokerHand pokerHandObj = new PokerHand()
			com.cardtech.game.PokerHand pokerHand = (com.cardtech.game.PokerHand) hands.get(pIndex)
			log.debug "writing poker hand records"
			assignPokerHandObj(pokerHandObj, pokerHand)
			if (pokerHandObj.validate()) {
				pokerHandObj.save(flush:true)
			} else {
				log.debug "pokerHandObj not valid: ${pokerHandObj.getErrors()}"
			}
			log.debug "writing poker deal records"
			// create PokerDeal associative records
			PokerDeal pokerDealObj = new PokerDeal()
			pokerDealObj.player = p
			pokerDealObj.pokerHand = pokerHandObj
			pokerDealObj.pokerGame = gameObj
			if (pokerDealObj.validate()) {
				pokerDealObj.save(flush:true)
			} else {
				log.debug "pokerDealObj not valid: ${pokerDealObj.getErrors()}"
			}

		}
		// return a map
		[players: playerObjs.collect{it.firstName}, hands: hands, winners: winners, winningRank: winningRank]
	}
	
	// use the names of the cards to find to domain objects
	private def assignPokerHandObj(PokerHand pokerHandObj, com.cardtech.game.PokerHand hand) {
		pokerHandObj.c1 = PlayingCard.findByName(hand.getCard(0).toString())
		pokerHandObj.c2 = PlayingCard.findByName(hand.getCard(1).toString())
		pokerHandObj.c3 = PlayingCard.findByName(hand.getCard(2).toString())
		pokerHandObj.c4 = PlayingCard.findByName(hand.getCard(3).toString())
		pokerHandObj.c5 = PlayingCard.findByName(hand.getCard(4).toString())
		assignRankObjs(pokerHandObj, hand.getRank())
	}
	
	// there's a variable number of high cards (breakers) so you have to be careful to avoid
	// a null reference here
	private def assignRankObjs(PokerHand pokerHandObj, com.cardtech.game.PokerRankWithHighCards rank) {
		pokerHandObj.rank  = PokerRank.findByName(rank.getRank().toString())
		Card [] highCards = rank.getHighCards()
		
		int size = highCards.length
		for (int i=0; i<5; i++) {
			Card highCard =  (i < size) ? highCards[i] : null
			switch (i) {
			case 0:
				pokerHandObj.breaker1 = getHighCardObj(highCard)
				break
			case 1:
				pokerHandObj.breaker2 = getHighCardObj(highCard)
				break
			case 2:
				pokerHandObj.breaker3 = getHighCardObj(highCard)
				break
			case 3:
				pokerHandObj.breaker4 = getHighCardObj(highCard)
				break
			case 4:
				pokerHandObj.breaker5 = getHighCardObj(highCard)
				break
			}
		}
	}
	
	// use the name of the high card to find domain object (provided it's not null).
	private PlayingCard getHighCardObj(Card c) {
		return (c != null) ? PlayingCard.findByName(c.toString()) : null
	}
	
}
