package com.cardtech.core;
/**
 * This simple enum indicates the desired order of a brand new deck. 
 */
public enum DeckOrder {
 /**
  * The standard order.  All of the clubs in ascending order followed by all hearts...
  */
	SUIT_ORDER, 
 /**
  * Non-standard but useful for testing.  All of the twos followed by all threes...
  */
	RANK_ORDER,
}
