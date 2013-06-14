package com.cardtech.core;

import static com.cardtech.core.AddToDeck.ADD_TO_BOTTOM;
import static com.cardtech.core.AddToDeck.ADD_TO_RANDOM;
import static com.cardtech.core.AddToDeck.ADD_TO_TOP;
import static com.cardtech.core.Card.ACE_CLUB;
import static com.cardtech.core.Card.ACE_DIAMOND;
import static com.cardtech.core.Card.ACE_HEART;
import static com.cardtech.core.Card.ACE_SPADE;
import static com.cardtech.core.Card.EIGHT_CLUB;
import static com.cardtech.core.Card.EIGHT_DIAMOND;
import static com.cardtech.core.Card.EIGHT_HEART;
import static com.cardtech.core.Card.EIGHT_SPADE;
import static com.cardtech.core.Card.FIVE_CLUB;
import static com.cardtech.core.Card.FIVE_DIAMOND;
import static com.cardtech.core.Card.FIVE_HEART;
import static com.cardtech.core.Card.FIVE_SPADE;
import static com.cardtech.core.Card.FOUR_CLUB;
import static com.cardtech.core.Card.FOUR_DIAMOND;
import static com.cardtech.core.Card.FOUR_HEART;
import static com.cardtech.core.Card.FOUR_SPADE;
import static com.cardtech.core.Card.JACK_CLUB;
import static com.cardtech.core.Card.JACK_DIAMOND;
import static com.cardtech.core.Card.JACK_HEART;
import static com.cardtech.core.Card.JACK_SPADE;
import static com.cardtech.core.Card.KING_CLUB;
import static com.cardtech.core.Card.KING_DIAMOND;
import static com.cardtech.core.Card.KING_HEART;
import static com.cardtech.core.Card.KING_SPADE;
import static com.cardtech.core.Card.NINE_CLUB;
import static com.cardtech.core.Card.NINE_DIAMOND;
import static com.cardtech.core.Card.NINE_HEART;
import static com.cardtech.core.Card.NINE_SPADE;
import static com.cardtech.core.Card.QUEEN_CLUB;
import static com.cardtech.core.Card.QUEEN_DIAMOND;
import static com.cardtech.core.Card.QUEEN_HEART;
import static com.cardtech.core.Card.QUEEN_SPADE;
import static com.cardtech.core.Card.SEVEN_CLUB;
import static com.cardtech.core.Card.SEVEN_DIAMOND;
import static com.cardtech.core.Card.SEVEN_HEART;
import static com.cardtech.core.Card.SEVEN_SPADE;
import static com.cardtech.core.Card.SIX_CLUB;
import static com.cardtech.core.Card.SIX_DIAMOND;
import static com.cardtech.core.Card.SIX_HEART;
import static com.cardtech.core.Card.SIX_SPADE;
import static com.cardtech.core.Card.TEN_CLUB;
import static com.cardtech.core.Card.TEN_DIAMOND;
import static com.cardtech.core.Card.TEN_HEART;
import static com.cardtech.core.Card.TEN_SPADE;
import static com.cardtech.core.Card.THREE_CLUB;
import static com.cardtech.core.Card.THREE_DIAMOND;
import static com.cardtech.core.Card.THREE_HEART;
import static com.cardtech.core.Card.THREE_SPADE;
import static com.cardtech.core.Card.TWO_CLUB;
import static com.cardtech.core.Card.TWO_DIAMOND;
import static com.cardtech.core.Card.TWO_HEART;
import static com.cardtech.core.Card.TWO_SPADE;
import static com.cardtech.core.DealPosition.DEAL_FROM_BOTTOM;
import static com.cardtech.core.DealPosition.DEAL_FROM_TOP;
import static com.cardtech.core.RemoveACard.BOTTOM_CARD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deck {

	private List<Card> cardsBySuit = (List<Card>) Arrays.asList(
			TWO_CLUB,THREE_CLUB,FOUR_CLUB,FIVE_CLUB,SIX_CLUB,SEVEN_CLUB,EIGHT_CLUB,NINE_CLUB,TEN_CLUB,JACK_CLUB,QUEEN_CLUB,KING_CLUB,ACE_CLUB,
			TWO_HEART,THREE_HEART,FOUR_HEART,FIVE_HEART,SIX_HEART,SEVEN_HEART,EIGHT_HEART,NINE_HEART,TEN_HEART,JACK_HEART,QUEEN_HEART,KING_HEART,ACE_HEART,
			TWO_DIAMOND,THREE_DIAMOND,FOUR_DIAMOND,FIVE_DIAMOND,SIX_DIAMOND,SEVEN_DIAMOND,EIGHT_DIAMOND,NINE_DIAMOND,TEN_DIAMOND,JACK_DIAMOND,QUEEN_DIAMOND,KING_DIAMOND,ACE_DIAMOND,
			TWO_SPADE,THREE_SPADE,FOUR_SPADE,FIVE_SPADE,SIX_SPADE,SEVEN_SPADE,EIGHT_SPADE,NINE_SPADE,TEN_SPADE,JACK_SPADE,QUEEN_SPADE,KING_SPADE,ACE_SPADE
	);

	private List<Card> cardsByRank = (List<Card>) Arrays.asList(
			TWO_CLUB,TWO_HEART,TWO_DIAMOND,TWO_SPADE,THREE_CLUB,THREE_HEART,THREE_DIAMOND,THREE_SPADE,FOUR_CLUB,FOUR_HEART,FOUR_DIAMOND,FOUR_SPADE,
			FIVE_CLUB,FIVE_HEART,FIVE_DIAMOND,FIVE_SPADE,SIX_CLUB,SIX_HEART,SIX_DIAMOND,SIX_SPADE,SEVEN_CLUB,SEVEN_HEART,SEVEN_DIAMOND,SEVEN_SPADE,
			EIGHT_CLUB,EIGHT_HEART,EIGHT_DIAMOND,EIGHT_SPADE,NINE_CLUB,NINE_HEART,NINE_DIAMOND,NINE_SPADE,TEN_CLUB,TEN_HEART,TEN_DIAMOND,TEN_SPADE,
			JACK_CLUB,JACK_HEART,JACK_DIAMOND,JACK_SPADE,QUEEN_CLUB,QUEEN_HEART,QUEEN_DIAMOND,QUEEN_SPADE,KING_CLUB,KING_HEART,KING_DIAMOND,KING_SPADE,
			ACE_CLUB,ACE_HEART,ACE_DIAMOND,ACE_SPADE
	);
	private static ArrayList<Card> cards;

 /**
  * Create a new deck in "standard" order, i.e. ordered by suit.
  */
	public Deck() {
		cards = new ArrayList<Card>(cardsBySuit);
	}

 /**
  * Create a new deck in rank order.  Useful for specific test cases.	
  * @param rankOrder must be true.
  */
	public Deck(boolean rankOrder) {
		if (rankOrder) {
			cards = new ArrayList<Card>(cardsByRank);
		} else {
			throw new IllegalArgumentException();
		}
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
		ArrayList<Card> head = new ArrayList<Card>(cards.subList(headStart, headLen));
		ArrayList<Card> tail = new ArrayList<Card>(cards.subList(tailStart, size));
		cards.clear();
		cards.addAll(tail);		
		cards.addAll(head);
	}
	
 /**
  * Deal cards from the top of the deck.
  * @param count - how many cards to deal (usually just one)
  * @return an ArrayList of Card.
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
		return new ArrayList<Card>(Arrays.asList(d));
	}
	
	 /**
	  * Deal cards from the top or bottom of the deck.
	  * This method is only used for card tricks.
	  * @param count - how many cards to deal (usually just one)
	  * @param pos - DEAL_FROM_TOP or DEAL_FROM_BOTTOM
	  * @return an ArrayList of Card.
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
				throw new IllegalArgumentException("Unexpected value: " + pos.toString());
			}
			return new ArrayList<Card>(Arrays.asList(d));			
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
  * @param c - identity of card to remove
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
  * @param how - enum value indicating position
  * @return the card removed.
  * 
  * @throws IllegalArgumentException when unexpected parameter value is passed.
  */
	public Card removeCard(RemoveACard how) {
		Card c = null;
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
		default:
			throw new IllegalArgumentException("Unexpected value: " + how.toString());
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
		ArrayList<Card> cardsRemoved = new ArrayList<Card>(); 
		for (Card c: cardsToRemove) {
			removeCard(c);
			cardsRemoved.add(c);
		}
		return cardsRemoved;
	}
	
 /**
  * Add a list of cards to the deck placing them either on top or the bottom of the deck. 
  * @param list - List of cards to add
  * @param how - enum indicating how to add
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
			throw new IllegalArgumentException("Unexpected value: " + how.toString());			
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
  */
	public List<Card> getDeck() {
		return cards;
	}

	
}
