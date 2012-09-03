package card.core

import spock.lang.*
import static card.core.Suit.*
import static card.core.Rank.*
import static card.core.Card.*
import static card.core.RemoveACard.*
import static card.core.AddToDeck.*


class DeckSpec extends Specification {

	Deck deck;

	def setup() {
		deck = new Deck();
	}

	def "New deck in suit order"() {
		given: "new deck"
		expect:
		deck.size == 52
		Suit.every {  s ->
			Rank.every { r->
				Card c = deck.removeCard(TOP_CARD);
				"${r}_${s}" == c.toString()
			}
		}
	}

	def "New deck in rank order"() {
		given: "deck in rank order"
		deck = new Deck(true);
		expect:
		deck.size == 52
		Rank.every {  r ->
			Suit.every { s->
				Card c = deck.removeCard(TOP_CARD);
				"${r}_${s}" == c.toString()
			}
		}
	}

	def "Test shuffling the deck"() {
		given: // new deck
		when:
		deck.shuffle()
		then:
		// how to test for randomness?  Measuring the distance of each card from its unshuffle position is too hard to do. So...
		int counter = 0
		deck.size == 52
		Card.each { c ->
			deck.removeCard(c)
			counter++
		}
		deck.size == 0
		counter == 52
	}

	def "Test cutting the deck"() {
		given: // new deck
		when:
		deck.cut()
		then:
		deck.size == 52
		[DIAMOND, SPADE, CLUB, HEART].every {  s ->
			Rank.every { r->
				Card c = deck.removeCard(TOP_CARD)
				"${r}_${s}" == c.toString()
			}
		}
		deck.size == 0
	}

	def "Test dealing from the deck"() {
		given: // new deck
		when:
		def hand1 = deck.deal(13);
		def hand2 = deck.deal(13);
		def hand3 = deck.deal(13);
		def hand4 = deck.deal(13);
		then:
		deck.empty == true
		hand1.every { c ->
			"${c.rank}" + "_CLUB" == c.toString()
		}
		hand2.every { c ->
			"${c.rank}" + "_HEART" == c.toString()
		}
		hand3.every { c ->
			"${c.rank}" + "_DIAMOND" == c.toString()
		}
		hand4.every { c ->
			"${c.rank}" + "_SPADE" == c.toString()
		}
	}

	def "Remove cards from different areas of the deck"() {
		given: // new deck
		when:
		def card1 = deck.removeCard(TOP_CARD)
		def card2 = deck.removeCard(BOTTOM_CARD)
		def card3 = deck.removeCard(RANDOM_CARD)
		then:
		card1 == TWO_CLUB
		card2 == ACE_SPADE
		card3 != TWO_CLUB && card3 != ACE_SPADE
	}

	def "Remove a list of cards"() {
		given: // new deck
		when:
		def aces = [
			ACE_CLUB,
			ACE_HEART,
			ACE_DIAMOND,
			ACE_SPADE
		]
		deck.removeCard(aces)
		then:
		deck.size == 48
		Card c
		while (deck.size > 0) {
			c = deck.removeCard(TOP_CARD)
			if (aces.contains(c)) {
			  break	
			} 
		}
		!(aces.contains(c))
		deck.size == 0
	}


	def "Remove cards then add them to top of the deck"() {
		given:  // new deck
		when:
		def jacks = [
			JACK_CLUB,
			JACK_HEART,
			JACK_DIAMOND,
			JACK_SPADE
		]
		deck.removeCard(jacks)
		deck.addCards(jacks, ADD_TO_TOP)
		then:
		def deal = deck.deal(4)
		deal == jacks
	}

	def "Remove cards then add them to bottom of the deck"() {
		given: // new deck
		when:
		def jacks = [JACK_CLUB,JACK_HEART,JACK_DIAMOND,JACK_SPADE]
		deck.removeCard(jacks)
		deck.addCards(jacks, ADD_TO_BOTTOM)
		def jack4 = deck.removeCard(deck.size - 1)
		def jack3 = deck.removeCard(deck.size - 1)
		def jack2 = deck.removeCard(deck.size - 1)
		def jack1 = deck.removeCard(deck.size - 1)
		then:
		[jack1, jack2, jack3, jack4] == jacks
	}

	def "Show the deck of cards"() {
		given: // new deck
		expect:
		deck.show()
	}

	def "Exception when card is removed 2x from deck"() {
		given: // new deck
		when:
		def c1 = deck.removeCard(TWO_CLUB)
		def c2 = deck.removeCard(TWO_CLUB)
		then:
		thrown(IllegalStateException)
	}

	def "Exception when 53rd card is dealt"() {
		given: "new deck"
		when:
		def hand1 = deck.deal(13);
		def hand2 = deck.deal(13);
		def hand3 = deck.deal(13);
		def hand4 = deck.deal(13);
		def c = deck.deal(1)
		then:
		thrown(IllegalStateException)
	}

	def "Exception when too many cards are dealt"() {
		given: "new deck"
		when:
		def hand1 = deck.deal(14);
		def hand2 = deck.deal(14);
		def hand3 = deck.deal(14);
		def hand4 = deck.deal(14);
		def c = deck.deal(1)
		then:
		thrown(IllegalStateException)
	}
}
