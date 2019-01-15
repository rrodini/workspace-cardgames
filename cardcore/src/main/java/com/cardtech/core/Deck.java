package com.cardtech.core;

import static com.cardtech.core.AddToDeck.ADD_TO_BOTTOM;
import static com.cardtech.core.AddToDeck.ADD_TO_RANDOM;
import static com.cardtech.core.AddToDeck.ADD_TO_TOP;
import static com.cardtech.core.DealPosition.DEAL_FROM_BOTTOM;
import static com.cardtech.core.DealPosition.DEAL_FROM_TOP;
import static com.cardtech.core.RemoveACard.BOTTOM_CARD;
import static com.cardtech.core.Rank.TWO;
import static com.cardtech.core.Rank.ACE;


import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * Deck object is a deck of Card objects.  A deck is a List and implemented by LinkedList.
 * There are LOTS of methods to assist the card trick DSL.
 *
 */
public class Deck implements Iterable<Card> {

	private List<Card> cards = new LinkedList<>();

 /**
  * Create a new deck in "standard" order, i.e., ordered by suit.
  */
	public Deck() {
		createDeckSuitOrder();
	}
 /**
  * Create a deck as per the order parameter.
  * @param order by Suit or by Rank.
  */
	public Deck(DeckOrder order) {
		if (order == DeckOrder.SUIT_ORDER) {
			createDeckSuitOrder();
		} else if (order == DeckOrder.RANK_ORDER) {
			createDeckRankOrder();
		} else {
			throw new IllegalArgumentException("order value not Suit or Rank: " + order );
		}
	}
 /**
  *  Create 52 card deck by suit.
  */
	private void createDeckSuitOrder() {
		createSuit(Suit.CLUB);
		createSuit(Suit.DIAMOND);
		createSuit(Suit.HEART);
		createSuit(Suit.SPADE);
	}
	
 /**
  * Create 14 cards of the given suit.
  * @param s suit to create.
  */
	private void createSuit(Suit s) {
		for (int i = TWO.getValue(); i <= ACE.getValue(); i++) {
			cards.add(new Card(s, i));
		}
	}
 /**
  * Create a new deck in rank order.  Useful for specific test cases.	
  */
	private void createDeckRankOrder() {
		for (int i = TWO.getValue(); i <= ACE.getValue(); i++) {
			cards.add(new Card(Suit.CLUB, i));
			cards.add(new Card(Suit.DIAMOND, i));
			cards.add(new Card(Suit.HEART, i));
			cards.add(new Card(Suit.SPADE, i));
		}
	}

	//@Inject
/**
 * Let the caller create the cards for the deck (DI here).
 * @param cards to use for deck.	
 */
	public Deck(List<Card> cards) {
		this.cards = cards;
	}
 /**
  * Shuffle the deck.  This puts whatever cards are in the deck in a random order.
  */
	public void shuffle() {
		int rand;
		Card temp;
		int size = cards.size();
		for (int i=0; i < size; i++) {
			rand = (int) (Math.random() * size);
			temp = cards.get(i);
			cards.set(i, cards.get(rand));
			cards.set(rand, temp);
		}
	}
	
 /**
  * Cut the deck (needed for realism).
  */
	public void cut() {
		int size = cards.size();
		if (size <= 1) {
			return;
		}
		int headStart = 0;
		int headLen = size / 2;
		int tailStart = headLen;
		List<Card> head = new LinkedList<Card>(cards.subList(headStart, headLen));
		List<Card> tail = new LinkedList<Card>(cards.subList(tailStart, size));
		cards.clear();
		cards.addAll(tail);		
		cards.addAll(head);
	}
	
 /**
  * Deal cards from the top of the deck.
  * @param count - how many cards to deal (usually just one)
  * @return a List of Card.
  * 
  * @throws IllegalStateException when deck doesn't contain enough cards for deal.
  */
	public List<Card> deal(int count) {
		Card [] d = new Card[count];
		for (int i=0; i<count; i++) {
			if (cards.isEmpty()) {
				throw new IllegalStateException("Deck is empty so cannot deal card.");
			}
			d[i] = cards.remove(0);
		}
		return new LinkedList<Card>(Arrays.asList(d));
	}
	
	 /**
	  * Deal cards from the top or bottom of the deck.
	  * This method is only used for card tricks.
	  * @param count - how many cards to deal (usually just one)
	  * @param pos - DEAL_FROM_TOP or DEAL_FROM_BOTTOM
	  * @return an List of Card.
	  * 
	  * @throws IllegalStateException when deck doesn't contain enough cards for deal.
	  */
		public List<Card> deal(int count, DealPosition pos) {
			Card [] d = new Card[count];
			if (pos == DEAL_FROM_TOP) {
				return deal(count);
			} else if (pos == DEAL_FROM_BOTTOM) {
				for (int i=0; i<count; i++) {
					d[i] = removeCard(BOTTOM_CARD);
				}
			} else {
				throw new IllegalArgumentException("Unexpected value: " + pos);
			}
			return new LinkedList<Card>(Arrays.asList(d));			
		}
		
 /**
  * Is the deck empty?
  * @return true if the deck has no cards.
  */
	public boolean isEmpty() {
		return (cards.size() == 0);
	}
	
 /**
  * Get the number of cards in the deck.
  * @return the number of cards in the deck.
  */
  public int getSize() {
    return cards.size();
  }
		
 /**
  * Remove a card from the deck.  After removal all index positions of trailing cards have shifted.
  * Remember that indexing is zero-relative.
  * @param index - index position of card to remove
  * @return the card at the index position.
  * 
  * @throws IllegalArgumentException when index is out of range.
  */
	public Card removeCard(int index) {
		int last = cards.size();
		if ((index < 0) || (index >= last) ) {
			throw new IllegalArgumentException("Index out of range. index: " + index + "range: 0.." + (last-1) );
		}
		return cards.remove(index);
	}
	
 /**
  * Remove the specified card from the deck wherever it occurs.
  * This requires the Card class to implement an 'equals' method.
  * @param c identity of card to remove
  * @return the card removed.
  * 
  * @throws IllegalStateException if the card is not in the deck.
  */
	public Card removeCard(Card c) {
		boolean removed = cards.remove(c);
		if (removed) {
			return c;
		}
		// Make sure you can't remove a card twice.
		throw new IllegalStateException("Card not in deck: " + c.toString());
	}

 /**
  * Remove a card from the top, bottom, or a random position in the deck.
  * @param how enum value indicating position
  * @return the card removed.
  * 
  * @throws IllegalArgumentException when unexpected parameter value is passed.
  */
	public Card removeCard(RemoveACard how) {
		Card c = null;
		if (null == how) {
			throw new IllegalArgumentException("Unexpected value: " + how);
		}
		switch (how) {
		case TOP_CARD:
			c = removeCard(0);
			break;
		case BOTTOM_CARD:
			c = removeCard(cards.size()-1);
			break;
		case RANDOM_CARD:
			int which = (int) (Math.random() * cards.size());
			c = removeCard(which);
			break;
		}
		return c;
	}
	
 /**
  * Remove a list of cards from the deck.  Useful in setting up card tricks.
  * @param cardsToRemove - List of cards to remove
  * @return the cardsRemoved which should be same as cardsToRemove
  * @throws IllegalStateException if any card in the list is not in the deck.
  */
	public List<Card> removeCard(List<Card> cardsToRemove) {
		List<Card> cardsRemoved = new LinkedList<Card>(); 
		for (Card c: cardsToRemove) {
			removeCard(c);
			cardsRemoved.add(c);
		}
		return cardsRemoved;
	}
	
 /**
  * Add a list of cards to the deck placing them either on top or the bottom of the deck. 
  * @param list List of cards to add
  * @param how enum indicating how to add
  * 
  * @throws IllegalArgumentException when unexpected enum value is passed.
  */
	public void addCards(List<Card> list, AddToDeck how) {
		if (how == ADD_TO_TOP) {
			cards.addAll(0, list);
		} else if (how == ADD_TO_BOTTOM) {
			cards.addAll(list);			
		} else if (how == ADD_TO_RANDOM) {
			int index = (int) (Math.random() * getSize());
			cards.addAll(index, list);
		} else {
			throw new IllegalArgumentException("Unexpected value: " + how);			
		}
		
	}
	
  /**
   * Show the cards in the deck.	
   */
	public void show() {
		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).show();
		}
		System.out.println();
	}

   /**
    * Return the deck.	
    * @return list of cards
    */
	public List<Card> getDeck() {
		return cards;
	}
   /**
    * Return an iterator over the cards of the deck.
    * @return an iterator.
    */
	@Override
	public Iterator<Card> iterator() {
		return (Iterator<Card>) cards.iterator();
	}
	

	
}
