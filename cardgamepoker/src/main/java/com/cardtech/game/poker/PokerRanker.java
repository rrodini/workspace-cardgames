package com.cardtech.game.poker;
import com.cardtech.core.Card;
import com.cardtech.core.Rank;

/**
 * PokerRanker is a helper class.  Use it to rank a poker hand.  <br/>
 * Since you're probably ranking the the hand so as to compare it to other hands <br/>
 * the rank has to keep track of the "kickers" that may be used to break ties..
 */
public class PokerRanker {
	// highCardValue is set as a side-effect of a successful rank assignment.
	// Note that this is really an array of "kickers" each of which is a potential tie breaker for hands with same poker rank.
	private static Card [] highCard;
 /**
  * Given a poker hand with 5 cards determine its rank.  Since the rank will be used to compare the hands of different
  * players to determine a winner, an array of kickers must also be returned.	
  * @param hand - a five card poker hand
  * @return a new PokerRandWithHighCard object 
  */
	public static PokerRankWithHighCards rank(PokerHand hand) {
		PokerRank rank = null;
		PokerHand sortedHand = hand;
		sortedHand.sort();
		// The sequence of if statements below is critical for determining the rank.
		if (isStraightFlush(sortedHand)) {
			rank = (highCard[0].getValue() == 14)?PokerRank.ROYAL_FLUSH: PokerRank.STRAIGHT_FLUSH;
		} else if (isFiveOfAKind(sortedHand)) {
			rank = PokerRank.FIVE_OF_A_KIND;
		} else if (isFourOfAKind(sortedHand)) {
			rank = PokerRank.FOUR_OF_A_KIND;
		} else if (isFullHouse(sortedHand)) {
			rank = PokerRank.FULL_HOUSE;
		} else if (isFlush(sortedHand)) {
			rank = PokerRank.FLUSH;
		} else if (isStraight(sortedHand)) {
			rank = PokerRank.STRAIGHT;
		} else if (isThreeOfAKind(sortedHand)) {
			rank = PokerRank.THREE_OF_A_KIND;
		} else if (isTwoPair(sortedHand)) {
			rank = PokerRank.TWO_PAIR;
		} else if (isOnePair(sortedHand)) {
			rank = PokerRank.ONE_PAIR;
		} else {
			rank = PokerRank.HIGH_CARD;
			Card [] highCard = {sortedHand.getCard(4),sortedHand.getCard(3),sortedHand.getCard(2),sortedHand.getCard(1),sortedHand.getCard(0)};
			PokerRanker.highCard = highCard;
		}
		if (null == rank) {
			throw new IllegalStateException("Can't determine rank of " + hand.toString());
		}
		return new PokerRankWithHighCards(rank, highCard);
	}

 /**
  * Is this a pair?	
  * @param sortedHand
  * @return true <=> pair
  */
	private static boolean isOnePair(PokerHand sortedHand) {
		// XXabc
		if (sortedHand.getCard(0).getValue() == sortedHand.getCard(1).getValue()) {
			// 4 kickers. Card value of pair, followed by remaining cards in descending card value order.
			Card [] highCard = {sortedHand.getCard(0),sortedHand.getCard(4),sortedHand.getCard(3),sortedHand.getCard(2)};
			PokerRanker.highCard = highCard;
			return true;
		}
		// aXXbc
		if (sortedHand.getCard(1).getValue() == sortedHand.getCard(2).getValue()) {
			// 4 kickers. Card value of pair, followed by remaining cards in descending card value order.
			Card [] highCard = {sortedHand.getCard(1),sortedHand.getCard(4),sortedHand.getCard(3),sortedHand.getCard(0)};
			PokerRanker.highCard = highCard;
			return true;
		}
		// abXXc
		if (sortedHand.getCard(2).getValue() == sortedHand.getCard(3).getValue()) {
			// 4 kickers. Card value of pair, followed by remaining cards in descending card value order.
			Card [] highCard = {sortedHand.getCard(2),sortedHand.getCard(4),sortedHand.getCard(1),sortedHand.getCard(0)};
			PokerRanker.highCard = highCard;
			return true;
		}
		// abcXX
		if (sortedHand.getCard(3).getValue() == sortedHand.getCard(4).getValue()) {
			// 4 kickers. Card value of pair, followed by remaining cards in descending card value order.
			Card [] highCard = {sortedHand.getCard(3),sortedHand.getCard(2),sortedHand.getCard(1),sortedHand.getCard(0)};
			PokerRanker.highCard = highCard;
			return true;
		}
		return false;
	}
 /**
  * Is this two pair?
  * @param sortedHand
  * @return true <=> two pair
  */
	private static boolean isTwoPair(PokerHand sortedHand) {
		// XXYYZ
		if (sortedHand.getCard(0).getValue() == sortedHand.getCard(1).getValue())
			if (sortedHand.getCard(2).getValue() == sortedHand.getCard(3).getValue()) {
				// 3 kickers. Card value from "high" pair, Card value from "low" pair, and remaining card value.
				Card [] highCard = {sortedHand.getCard(2), sortedHand.getCard(0), sortedHand.getCard(4)};
				PokerRanker.highCard = highCard;
				return true;
			}
		// XXZYY
		if (sortedHand.getCard(0).getValue() == sortedHand.getCard(1).getValue())
			if (sortedHand.getCard(3).getValue() == sortedHand.getCard(4).getValue()){
				// 3 kickers. Card value from "high" pair, Card value from "low" pair, and remaining card value.
				Card [] highCard = {sortedHand.getCard(3), sortedHand.getCard(0), sortedHand.getCard(2)};
				PokerRanker.highCard = highCard;
				return true;
			}
		// ZXXYY
		if (sortedHand.getCard(1).getValue() == sortedHand.getCard(2).getValue())
			if (sortedHand.getCard(3).getValue() == sortedHand.getCard(4).getValue()) {				
				// 3 kickers. Card value from "high" pair, Card value from "low" pair, and remaining card value.
				Card [] highCard = {sortedHand.getCard(3), sortedHand.getCard(1), sortedHand.getCard(0)};
				PokerRanker.highCard = highCard;
				return true;
			}
		return false;
	}

 /**
  * Is this three of a kind?
  * @param sortedHand
  * @return true <=> three of a kind
  */
	private static boolean isThreeOfAKind(PokerHand sortedHand) {
		// do cards 0..2 match?
		if (sortedHand.getCard(0).getValue() == sortedHand.getCard(1).getValue())
			if (sortedHand.getCard(1).getValue() == sortedHand.getCard(2).getValue()) {
				// one kicker namely the value of three of a kind card.
				// Note that two poker players cannot have the same "three of a kind." 
				Card [] highCard = {sortedHand.getCard(0)};
				PokerRanker.highCard = highCard;
				return true;
			}
		// do cards 1..3 match?
		if (sortedHand.getCard(1).getValue() == sortedHand.getCard(2).getValue())
			if (sortedHand.getCard(2).getValue() == sortedHand.getCard(3).getValue()) {
				// one kicker namely the value of three of a kind card.
				Card [] highCard = {sortedHand.getCard(1)};
				PokerRanker.highCard = highCard;
				return true;
			}
		// do cards 2..4 match?
		if (sortedHand.getCard(2).getValue() == sortedHand.getCard(3).getValue())
			if (sortedHand.getCard(3).getValue() == sortedHand.getCard(4).getValue()) {
				// one kicker namely the value of three of a kind card.
				Card [] highCard = {sortedHand.getCard(2)};
				PokerRanker.highCard = highCard;
				return true;
			}
		return false;
	}

 /**
  * Is this a straight? (card values in ascending order).  Note that the ace is only played high at present.
  * @param sortedHand
  * @return true <=> straight.
  */
	private static boolean isStraight(PokerHand sortedHand) {
		if (sortedHand.getCard(0).getValue() + 1 == sortedHand.getCard(1).getValue())
			if (sortedHand.getCard(1).getValue() + 1 == sortedHand.getCard(2).getValue())
				if (sortedHand.getCard(2).getValue() + 1 == sortedHand.getCard(3).getValue())
					if (sortedHand.getCard(3).getValue() + 1 == sortedHand.getCard(4).getValue()) {
						// one kicker since the values are "contiguous"
						Card [] highCard = {sortedHand.getCard(4)};
						PokerRanker.highCard = highCard;
						return true;
					}
		return false;
	}

 /**
  * Is this a flush? (all cards have same suit).
  * @param sortedHand
  * @return true <=> flush
  */
	private static boolean isFlush(PokerHand sortedHand) {
		if (sortedHand.getCard(0).getSuit() == sortedHand.getCard(1).getSuit())
			if (sortedHand.getCard(1).getSuit() == sortedHand.getCard(2).getSuit())
				if (sortedHand.getCard(2).getSuit() == sortedHand.getCard(3).getSuit())
					if (sortedHand.getCard(3).getSuit() == sortedHand.getCard(4).getSuit()) {
						// five kickers, namely the card values from highest to lowest.
						Card [] highCard = {sortedHand.getCard(4),sortedHand.getCard(3),sortedHand.getCard(2),sortedHand.getCard(1),sortedHand.getCard(0)};
						PokerRanker.highCard = highCard;
						return true;
					}
		return false;
	}

 /**
  * Is this a full house (a pair and three of a kind).
  * @param sortedHand
  * @return true <=> full house
  */
	private static boolean isFullHouse(PokerHand sortedHand) {
		// do cards 0..1 and 2..4 match?
		if (sortedHand.getCard(0).getValue() == sortedHand.getCard(1).getValue())
			if (sortedHand.getCard(2).getValue() == sortedHand.getCard(3).getValue())
				if (sortedHand.getCard(3).getValue() == sortedHand.getCard(4).getValue()) {
					// 1 kicker.  Card value from three of a kind. 
					Card [] highCard = {sortedHand.getCard(2)};
					PokerRanker.highCard = highCard;
					return true;
				}
		// do cards 0..2 and 3..4 match?
		if (sortedHand.getCard(0).getValue() == sortedHand.getCard(1).getValue())
			if (sortedHand.getCard(1).getValue() == sortedHand.getCard(2).getValue())
				if (sortedHand.getCard(3).getValue() == sortedHand.getCard(4).getValue()) {
					// two kickers.  Card value from three of a kind. 
					Card [] highCard = {sortedHand.getCard(0)};
					PokerRanker.highCard = highCard;
					return true;
				}
		return false;
	}

 /**
  * Is this four of a kind?	
  * @param sortedHand
  * @return true <=> four of a kind
  */
	private static boolean isFiveOfAKind(PokerHand sortedHand) {
		long distinctValueCount = sortedHand.getHand()
				.stream()
				.map(c -> c.getValue())
				.distinct()
				.count();
		Card [] highCard = {sortedHand.getCard(0)};
		PokerRanker.highCard = highCard;
		return distinctValueCount == 1;
	}
 /**
  * Is this four of a kind?	
  * @param sortedHand
  * @return true <=> four of a kind
  */
	private static boolean isFourOfAKind(PokerHand sortedHand) {
		// do cards 0..3 match?
		if (sortedHand.getCard(0).getValue() == sortedHand.getCard(1).getValue())
			if (sortedHand.getCard(1).getValue() == sortedHand.getCard(2).getValue())
				if (sortedHand.getCard(2).getValue() == sortedHand.getCard(3).getValue()) {
					// only one kicker here. It's the value of the four of a kind.
					Card [] highCard = {sortedHand.getCard(0)};
					PokerRanker.highCard = highCard;
					return true;
				}
		// do cards 1..4 match?
		if (sortedHand.getCard(1).getValue() == sortedHand.getCard(2).getValue())
			if (sortedHand.getCard(2).getValue() == sortedHand.getCard(3).getValue())
				if (sortedHand.getCard(3).getValue() == sortedHand.getCard(4).getValue()) {
					// only one kicker here. It's the value of the four of a kind. 
					Card [] highCard = {sortedHand.getCard(1)};
					PokerRanker.highCard = highCard;
					return true;
				}
		return false;
	}

 /**
  * Is this a straight flush?  (Presently the ace is only played as a high card).	
  * @param sortedHand poker hand with 5 cards.
  * @return true <=> straight flush.
  */
	private static boolean isStraightFlush(PokerHand sortedHand) {
		boolean straightFlush = false;
		if (sortedHand.getCard(0).getValue() + 1 == sortedHand.getCard(1).getValue()) {
			if (sortedHand.getCard(1).getValue() + 1 == sortedHand.getCard(2).getValue()) {
				if (sortedHand.getCard(2).getValue() + 1 == sortedHand.getCard(3).getValue()) {
					if (sortedHand.getCard(3).getValue() + 1 == sortedHand.getCard(4).getValue()) {
						straightFlush = sortedHand.getCard(0).getSuit() == sortedHand.getCard(1).getSuit() &&
								sortedHand.getCard(1).getSuit() == sortedHand.getCard(2).getSuit() &&
								sortedHand.getCard(2).getSuit() == sortedHand.getCard(3).getSuit() &&
								sortedHand.getCard(3).getSuit() == sortedHand.getCard(4).getSuit() ;
						if (straightFlush) {
							// only one kicker here. It's the highest value card which sorted to the end.
							Card [] highCard = {sortedHand.getCard(4)};
							PokerRanker.highCard = highCard;
						}
					}
				}
			}
		}
		return straightFlush;
	}
}
