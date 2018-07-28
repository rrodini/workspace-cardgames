package com.cardtech.game.poker;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PokerHandMultiMap extends TreeMap<PokerHand, List<Integer>> {
	private static final long serialVersionUID = 1L;

	public void put(PokerHand key, Integer number){
		List<Integer> current = get(key);
		if (current == null) {
			current = new ArrayList<Integer>();
			super.put(key, current);
		}
		current.add(number);
	}

}
