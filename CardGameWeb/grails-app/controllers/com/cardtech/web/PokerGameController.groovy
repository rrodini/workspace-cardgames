package com.cardtech.web

import java.util.List

class PokerGameController {

	static defaultAction = 'showPlayers'
	
	def pokerGameService
	
	def showPlayers() {
		log.debug "PokerGameController: showPlayers"
		// show screen for player selection
		// if 'play' button is chosen then submit to showGame action 
	}
		
	def showGame() {
		log.debug "PokerGameController: showGame"
		log.debug "${params.playerIds}"
//		params.playerIds is a List of String(s), not a List of Long(s)
		List playerObjs = []
		params.playerIds.each { String idString ->
			playerObjs.add(Player.read(idString.toLong()))
		}
		// actually play the poker game within the service
		pokerGameService.playGame(playerObjs)
		// show the game result.  The either 'Home' or 'Play Again'
		// pokerGameService returns a map containing data shown on the screen
	}
	
}
