package com.cardtech.game.poker;

import com.cardtech.game.Player;

/**
 * PokerPlayer is the simplistic representation of a poker player. <br/>
 * Poker players of course have a hand object but this is an instance variable of PokerGame. <br/>
 * Why? PokerGames are perfect for concurrent games, but a PokerPlayer is not.
 */
public class PokerPlayer extends Player {
	
	public PokerPlayer() {
		super();
	}
	
	public PokerPlayer(String name) {
		super(name);
	}
 /**
  * During massive simulations is may be nice to track the highest random hand that is dealt.	
  */
	PokerRank highestWinningRank = PokerRank.HIGH_CARD;
	
 /**
  * After the simulations have finished get the highest hand rank.
  * @return the highest hand rank for this player.
  */
	public PokerRank getHighestWinningHandRank() {
		return highestWinningRank;
	}
	
 /**
  * At the end of a simulation set the highest hand rank.
  * @param newWinningRank - rank of hand that just won.
  */
	public synchronized void setHighestWinningRank(PokerRank newWinningRank) {
		if (newWinningRank.compareTo(highestWinningRank) > 0 ) {
			highestWinningRank = newWinningRank;
		}
	}
	
	

}
