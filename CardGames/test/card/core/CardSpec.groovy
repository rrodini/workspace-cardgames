package card.core

import spock.lang.*
import static card.core.Suit.*
import static card.core.Rank.*
import static card.core.Card.*


class CardSpec extends Specification {
	
	def "Two of Clubs"() {
		expect:
		CLUB == TWO_CLUB.getSuit();
		TWO == 	TWO_CLUB.getRank();
		2 == 	TWO_CLUB.getValue();	
	}
	
	def "Ace of Hearts"() {
		expect:
		ACE_HEART.toString().equals("ACE_HEART")
	}
	
	def "Comparator for Cards"() {
		expect:
		// statement below looks weird but each card is an instance of Comparator
		java.util.Comparator<Card> comparator = TWO_CLUB
		comparator.compare(TWO_CLUB, THREE_CLUB) < 0
		comparator.compare(TWO_CLUB, TWO_HEART) == 0
		comparator.compare(THREE_CLUB, TWO_CLUB) > 0
	}

	def "Cards have correct numeric values"() {
		expect:
		(2..14).each { v ->
			Rank.each { r->
				Suit.each {  s ->
					Card.valueOf("${r}_${s}").value == v
				}
				
			}
		}
	}
	
}
