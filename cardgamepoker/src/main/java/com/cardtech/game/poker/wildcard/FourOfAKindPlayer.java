package com.cardtech.game.poker.wildcard;

import com.cardtech.game.poker.PokerHand;

import java.util.List;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;

/**
 * FourOfAKindPlayer attempts to play the wildcards into
 * four (possibly five) of a kind.
 * 
 * The algorithm is to find the "distinct value count" of the
 * naturals and play the jokers when this count is 1 or 2.
 * 
 * See TestFourOfAKindPlayer for examples.
 * 
 */
public class FourOfAKindPlayer extends WildcardPlayer {

	public FourOfAKindPlayer(PokerHand hand) {
		super(hand);
	}
	/**
	 * Attempt to play into 4 (or 5) of a kind.
	 * See TestFourOfAKindPlayer for examples.
	 */
	@Override
	public boolean play() {
		int distinctValueCount = distinctValueCount(naturals);
		if (distinctValueCount == 1) {
			if (natCount == 3) {
				// 2:C,2:H,2:D,J1,J2
				// naturals are 3 of a kind, play two wildcards.
				playWildcardsFourOfAKind(naturals);
			} else if (natCount == 4) {
				// 2:C,2:H,2:D,2:S,J1
				// naturals are 4 of a kind, play one wildcard.
				playWildcardsFourOfAKind(naturals);
			} else {
				throw new IllegalStateException("Can't analyze these naturals: " + naturals);
			}
			return true;
		} else if (distinctValueCount == 2) {
			// may have a two pairs
			if (natCount == 4 &&
				// 3:C,3:H,8:D,8:S,J1 <= ELIMINATE THIS ONE
				naturals.get(1).getValue() != naturals.get(2).getValue()) {
				return false;
			} else if (natCount == 3){
				// 2:C,8:H,8:D,J1,J2  or 3:C,3:H,8:S,J1,J2
				// naturals are 2 of a kind, play two wildcards.
				playWildcardsFourOfAKind(naturals);
			} else if (natCount == 4) {
				// 3:C,3:H,3:D,8:S,J1 or 2:C,8:H,8:D,8S,J2
				// naturals are 3 of a kind play one wildcard.
				playWildcardsFourOfAKind(naturals);
			} else {
				throw new IllegalStateException("Cant's analyze thse naturals: " + naturals);
			}
			return true;

		}
		return false;
	}
	/**
	 * playWildcardsFourOfAKind() plays the wildcards.
	 * Note:
	 * - side-effect of altering the list.
	 * @param list of Card objects.
	 */
	private void playWildcardsFourOfAKind(List<Card> list) {
		List<Card> sameValueCards = findCardValueSame(list);
		int newCardValue = sameValueCards.get(0).getValue();
		List<Suit> missingSuits = findMissingSuits(sameValueCards);
		if (missingSuits.size() < 2) {
			// guarantee a suit for a second wildcard.
			missingSuits.add(Suit.CLUB);
		}
		if (natCount == 3) {
			playWildcard(list, new Card(missingSuits.remove(0), newCardValue));
			if (wildCount == 1) {			
				playWildcard(list, new Card(missingSuits.remove(0), newCardValue));
			}
		} else if (natCount == 4) {
			// 5 of a kind
			playWildcard(list, new Card(missingSuits.remove(0), newCardValue));			
		}
	}
}