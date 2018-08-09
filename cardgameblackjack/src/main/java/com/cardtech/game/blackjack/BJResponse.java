package com.cardtech.game.blackjack;

public class BJResponse {
	/* Really want an enum here but also want a two dimensional array. */
	static final int S  = 0;	// Stand
	static final int H  = 1;   // Hit
	static final int DH = 2;   // Double (if not allowed, then hit)
	static final int DS = 3;   // Double (if not allowed, then stand)
	static final int SP = 4;   // Split 
	static final int SU = 5;   // Surrender (if not allowed, then hit)
}
