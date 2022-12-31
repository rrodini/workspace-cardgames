package com.cardtech.game.poker.wildcard;

import java.util.List;

import com.cardtech.game.poker.PokerHand;
/**
 * PlayWildcards is the driver for Wildcard Player.  That is, it creates an instance
 * of the concrete wildcard players (e.g. FourOfAKind) and sequences through them 
 * from the highest value hand to the lowest value hand to determine the best way
 * to play the wildcards.
 * 
 * Notes:
 * - The sequence matches that of PokerRanker by design.
 * - The poker hand must contain wildcards.
 *
 */
public class PlayWildcards {
	private WildcardPlayer bestPlayer = null;
    /**
     * PlayWildcards constructor does all of the work.
     * Call getBestHand after new object is created.
     * 	
     * @param hand poker hand containing wildcard(s), i.e. jokers.
     */
	public PlayWildcards(PokerHand hand) {
		if (!hand.containsWildcards()) {
			throw new IllegalArgumentException("Poker hand does not contain wildcards: " + hand);
		}
		List<WildcardPlayer> players = List.of(
				new StraightFlushPlayer(hand),
				new FourOfAKindPlayer(hand),
				new FullHousePlayer(hand),
				new FlushPlayer(hand),
				new StraightPlayer(hand),
				new ThreeOfAKindPlayer(hand),
				new TwoPairPlayer(hand),
				new OnePairPlayer(hand));
		for (WildcardPlayer player: players) {
			if (player.play()) {
				bestPlayer = player;
				return;
			}
		}
		throw new IllegalStateException("A wildcard player couldn't be found: " + hand);
	}
	/** 
	 * getBestHand returns the highest (best) value poker hand.
	 * 
	 * Notes:
	 * - should only be called after the constructor is called.
	 * 
	 * @return a PokerHand object.
	 */
	public PokerHand getBestHand() {
		if (bestPlayer == null) {
			throw new IllegalStateException("bestPlayer is null!");
		}
		return bestPlayer.getBestHand();
	}
	

}