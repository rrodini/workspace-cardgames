package com.cardtech.game.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
/**
 * PokerHandMultiMap is a TreeMap that orders the keys (which are PokerHand objects)
 * as per their poker rank.  The values are just the indexes of the players.
 * Since there is the possibility of ties in poker (rare but possible), if the key 
 * is already present then there is a tie between players.
 * 
 *  Note: PokerHand uses kickers to break ties (already).
 *
 */
public class PokerHandMultiMap extends TreeMap<PokerHand, List<Integer>> {
	private static final long serialVersionUID = 1L;
 /**
  * If the key is already present, there is a tie between players.
  * @param key PokerHand object.
  * @param number index of player with hand.
  */
	public void put(PokerHand key, Integer number){
		List<Integer> current = get(key);
		if (current == null) {
			current = new ArrayList<Integer>();
			super.put(key, current);
		}
		current.add(number);
	}

}
