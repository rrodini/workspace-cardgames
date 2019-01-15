package com.cardtech.core;
/**
 * This enum has values to indicate where/how to remove a card from the deck. 
 */
public enum RemoveACard { 
 /**
  * Indicates the card at the front of the deck.  Index position zero.	
  */
	TOP_CARD, 
 /**
  * Indicates a randomly picked card.	
  */
	RANDOM_CARD, 
 /**
  * Indicates the card at the back of the deck.  Index position is the size of the deck minus one.
  */
	BOTTOM_CARD,

}
