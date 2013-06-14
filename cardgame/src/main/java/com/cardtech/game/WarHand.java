package com.cardtech.game;
import java.util.LinkedList;
import java.util.List;

import com.cardtech.core.Card;

/**
 * WarHand represents the state of a war player's hand at some point in the game. <br/>
 * The player has: <br/>
 * 1) unplayed cards.  <br/>
 * 2) some (optional) down cards representing a state of war.  <br/>
 * 3) some (optional) up cards which decide the war winner.  <br/>
 */
public class WarHand extends Hand {
 /**
  * hand is the player's unplayed cards.
  */
	LinkedList<Card> hand = null;
 /**
  * downCards are placed during "war"	
  */	
	LinkedList<Card> downCards = null;
 /**
  * upCards determine the "war" winner. Usually just one up card during play, but maybe more than one during multiple wars	
  */	
	LinkedList<Card> upCards = null;
	
 /**
  * Construct some empty objects.
  */
	public WarHand() {
		hand = new LinkedList<Card>();
		downCards = new LinkedList<Card>();
		upCards = new LinkedList<Card>();
		
	}
	
 /**
  * This should only be called during the initial deal.
  * @param c - dealt card.  Add it to the player's hand.
  */
	public void addCard(List<Card> c) {
		if (c.size() != 1) {
			throw new IllegalArgumentException("Expect only one card here.");
		}
		hand.add(c.get(0));
	}
	
 /**
  * This method should only be called following a war battle.
  * @param cards - that were won.  Add them as-is to the end of the player's hand.
  */
	public void addCards(LinkedList<Card> cards) {
		hand.addAll(cards);
	}
	
 /**
  * Does the player have any cards?  If "no" then the player should be eliminated.
  * @return true => the player has no cards.
  */
	public boolean isHandEmpty() {
		return (hand.size() == 0);
	}
	
 /**
  * A war has broken out and we should play 3 down cards. Since this might exhaust the 
  * cards in a player's hand, we don't let this happen so that the player can still 
  * play an up card.
  * @param count - number of down cards.  A variable in case the rules are varied.
  */
	public void playDownCard(int count) {
		int i = 0;
		while ((i < count) && (hand.size() > 1)) {
			Card c = hand.removeFirst();
			downCards.addLast(c);
			i++;
		}
		
	}
	
 /**
  * Just move the top hand card to the upCards pile.  getUpCard() will return it.
  * @param wc
  */
	public void playUpCard(WarRoundContext wc) {
		if (isHandEmpty()) {
			throw new IllegalStateException("Should always have an up card!");
		}
		Card c = hand.removeFirst();
		upCards.addLast(c);
	}
	
 /**
  * Get the top card from the upCards pile.
  * @return top card from the upCards pile.
  */
	public Card getUpCard() {
		return (upCards.getLast());
	}
	
 /**
  * Get all of the downCards and start a new downCards pile.
  * @return all of the downCards.
  */
	public LinkedList<Card> getDownCards() {
		LinkedList<Card> dup = new LinkedList<Card>(downCards);
		downCards.clear();
		return dup;		
	}
	
 /**
  * Get all of the upCards and start a new upCards pile.
  * @return all of the upCards.
  */
	public LinkedList<Card> getUpCards() {
		LinkedList<Card> dup = new LinkedList<Card>(upCards);
		upCards.clear();
		return dup;		
	}
	
 /**
  * Get the number of cards in the hand.  Good for debugging.
  * @return the number of cards in the hand.
  */
	public int getHandCount() {
		return hand.size();
	}

 /**
  * Get the number of downCards.  Good for debugging.
  * @return the number of downCards.
  */
	public int getDownCardsCount() {
		return downCards.size();
	}

 /**
  * Get the number of upCards.  Good for debugging.
  * @return the number of upCards.
  */
	public int getUpCardsCount() {
		return upCards.size();
	}

}
