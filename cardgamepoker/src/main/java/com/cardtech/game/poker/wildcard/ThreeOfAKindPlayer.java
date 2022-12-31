package com.cardtech.game.poker.wildcard;

import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.core.Suit;
import com.cardtech.game.poker.PokerHand;
/**
 * ThreeOfAKindPlayer attempts to play the wildcards into 3 of a kind.
 * 
 * The algorithm is to find the "distinct value count" of the
 * naturals and play the jokers when this count is 1 or 2.
 * 
 * See TestThreeOfAKindPlayer for examples.
 * 
 */
public class ThreeOfAKindPlayer extends WildcardPlayer {

	public ThreeOfAKindPlayer(PokerHand hand) {
		super(hand);
	}
	/**
	 * Attempt to play into 3 of a kind.
	 * See TestThreeOfAKindPlayer for examples.
	 */
	@Override
	public boolean play() {
		int distinctValueCount = distinctValueCount(naturals);
		if (distinctValueCount == 3) {
			List<Card> cards = findCardsSameValue(naturals);
			List<Suit> missing = findMissingSuits(cards);
			int newCardValue = cards.get(0).getValue();
			if (natCount == 3) {
				playWildcard(naturals, new Card(missing.get(0), newCardValue));
				playWildcard(naturals, new Card(missing.get(1), newCardValue));
				return true;
			} else if (natCount == 4) {
				playWildcard(naturals, new Card(missing.get(0), newCardValue));
				return true;
			} else {
				throw new IllegalStateException("Can't analyze these naturals: " + naturals);
			}
		}
		return false;
	}
	// Assume distinct count == 3
	private List<Card> findCardsSameValue(List<Card> list) {
		if (list.size() == 3) {
			// pick highest value card
			return list.subList(2, 3);
		} else if (list.size() == 4) {
			for (int i = 0; i < 3; i++) {
				if (list.get(i).getValue() == list.get(i+1).getValue()) {
					return list.subList(i, i+2);
				}
			}
		} else {
			throw new IllegalArgumentException("Can't analyze this list: " + list);
		}
		return null;
	}
	
}
