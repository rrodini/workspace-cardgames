package com.cardtech.web

import com.cardtech.core.Deck

class DeckController {

	static defaultAction = 'newDeck'
	
	static Deck deckObj
	
	def show() {
		log.debug "DeckController: show"
		if (!deckObj) {
			System.out.println("deckObj is NULL");
		}
		[deckObj: deckObj ]
	}
	
	def newDeck() {
		log.debug "DeckController: newDeck"
		deckObj = new Deck()
		redirect(action: "show")
	}
	
	def shuffle() {
		log.debug "DeckController: shuffle"
		createDeck()
		deckObj.shuffle()
		redirect(action: "show")
	}
	
	def cut() {
		log.debug "DeckController: cut"
		createDeck()
		deckObj.cut()
		redirect(action: "show")
	}

	void createDeck() {
		if (!deckObj) {
			System.out.println("createDeck: deckObj created");
			deckObj = new Deck()
		}
	}
}
