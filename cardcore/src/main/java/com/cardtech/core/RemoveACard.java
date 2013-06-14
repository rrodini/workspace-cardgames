package com.cardtech.core;
/**
 * This enum has values to indicate where/how to remove a card from the deck. 
 */
public enum RemoveACard { 
 /**
  * TOP_CARD indicates the card at the front of the deck.  Index position zero.	
  */
	TOP_CARD, 
 /**
  * RANDOM_CARD indicates a randomly picked card.	
  */
	RANDOM_CARD, 
 /**
  * BOTTOM_CARD  indicates the card at the back of the deck.  Index position is the size of the deck minus one.
  */
	BOTTOM_CARD,

}
