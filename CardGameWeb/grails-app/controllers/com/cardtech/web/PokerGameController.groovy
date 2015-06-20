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
		int numOfGames = params.int('numOfGames')
		// actually play the poker game within the service
        def results = pokerGameService.playGames(playerObjs, numOfGames)
		// show the game result using different views if single or multiple games were played.
		if (numOfGames == 1) {
			render(view: "showGame", model: results)
		} else {
			render(view: "showMultipleGames", model: results)
		}
	}
		
}
