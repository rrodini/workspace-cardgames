package com.cardtech.game.blackjack;

import static com.cardtech.game.blackjack.BJHandValue.Firmness.*;
import static com.cardtech.game.blackjack.BJResponse.*;


import com.cardtech.core.Card;

/**
 * PlayerStrategy is the fixed strategy of a player taken from the Wikipedia article on blackjack.
 * Note: same strategy was found on other blackjack websites.
 */
public class BJPlayerStrategy implements BJStrategy {

	  /* These tables implement the player strategy found here: */ 
      /* https://en.wikipedia.org/wiki/Blackjack                */ 
		
		final int [][] hardHandResponse = {
                 /*       Dealer's face-up card.           */
				 /* 2   3   4   5   6   7   8   9   10   A */
/* player hand */
/* 0 - 17-20   */ { S , S , S , S , S , S , S , S , S , S  },				
/* 1 - 16      */ { S , S , S , S , S , H , H , SU, SU, SU },				
/* 2 - 15      */ { S , S , S , S , S , H , H , H , SU, H  },				
/* 3 - 13-14   */ { S , S , S , S , S , H , H , H , H , H  },				
/* 4 - 12      */ { H , H , S , S , S , H , H , H , H , H  },				
/* 5 - 11      */ { DH, DH, DH, DH, DH, DH, DH, DH, DH, DH },				
/* 6 - 10      */ { DH, DH, DH, DH, DH, DH, DH, DH, H , H  },				
/* 7 -  9      */ { H , DH, DH, DH, DH, H , H , H , H , H  },				
/* 8 - 5-8     */ { H , H , H , H , H , H , H , H , H , H  },				
		};
		final int [][] softHandResponse = {
                 /*       Dealer's face-up card.           */
				 /* 2   3   4   5   6   7   8   9   10   A */
/* player hand */
/* 0 - A,9     */ { S , S , S , S , S , S , S , S , S , S  },				
/* 1 - A,8     */ { S , S , S , S , DS, S , S , S , S , S  },				
/* 2 - A,7     */ { DS, DS, DS, DS, DS, S , S , H , H , H  },				
/* 3 - A,6     */ { H , DH, DH, DH, DH, H , H , H , H , H  },				
/* 4 - A,4-A,5 */ { H , H , DH, DH, DH, H , H , H , H , H  },				
/* 5 - A,2-A,3 */ { H , H , H , DH, DH, H , H , H , H , H  },				
		};
	final int [][] splitHandResponse = {
                 /*       Dealer's face-up card.           */
	      		 /* 2   3   4   5   6   7   8   9   10   A */
/* player hand */
/* 0 - A-A     */ { SP, SP, SP, SP, SP, SP, SP, SP, SP, SP },				
/* 1 - 10,10   */ { S , S , S , S , S , S , S , S , S , S  },				
/* 2 - 9-9     */ { SP, SP, SP, SP, SP, S , SP, SP, S , S  },				
/* 3 - 8-8     */ { SP, SP, SP, SP, SP, SP, SP, SP, SP, SP },				
/* 4 - 7-7     */ { SP, SP, SP, SP, SP, SP, H , H , H , H  },				
/* 5 - 6-6     */ { SP, SP, SP, SP, SP, H , H , H , H , H  },				
/* 6 - 5-5     */ { DH, DH, DH, DH, DH, DH, DH, DH, H , H  },				
/* 7 - 4-4     */ { H , H , H , SP, SP, H , H , H , H , H  },				
/* 8 - 2-2,3-3 */ { SP, SP, SP, SP, SP, SP, H , H , H , H  },				
	};
   /**
    * hit gives a response to the dealer's question regarding HIT or STAND.
    * The response depends on the player's cards and the dealer's up card.
    * @param playerHand player's hand.
    * @param dealerHand dealer's hand.
    * @return See BJResponse values.
    */
	@Override
	public int hit(BJPlayerHand playerHand, BJDealerHand dealerHand) {
		BJHandValue playerHandValue = BJHandEvaluator.evaluate(playerHand);
//System.out.printf("player hand: %s\n", playerHandValue.toString());
		int response = -1;
		int j = indexFromDealerHand(dealerHand);
		if (playerHand.getCardCount() == 2 && 
			playerHand.getCard(0).getValue() == playerHand.getCard(1).getValue()) {
			// use splitHandResponse table
			int i = splitIndexFromPlayerHand(playerHand);
//System.out.printf("split indices i: %d, j: %d\n", i, j);
			response = splitHandResponse[i][j];
		} else if (playerHandValue.getFirmness() == HARD_HAND) {
			// use hardHandResponse table
			int i = hardIndexFromPlayerHand(playerHandValue.getLowValue()); // low value == high value
//System.out.printf("hard indices i: %d, j: %d\n", i, j);
			response = hardHandResponse[i][j];
		} else if (playerHandValue.getFirmness() == SOFT_HAND) {
			int i = softIndexFromPlayerHand(playerHandValue.getLowValue()-playerHandValue.getAceCount());
//System.out.printf("soft indices i: %d, j: %d\n", i, j);
			response = softHandResponse[i][j];
		} else {
			throw new IllegalStateException("player's hand value is invalid: " + playerHandValue.toString());
		}
		response = mapResponse(response);
		return response;
	}
	/** 
	 * mapResponse collapses some of the responses in order to simplify the implementation 
	 * as there is no DOUBLE or SURRENDER implemented.
	 * @param response one of STAND, HIT, DOUBLE/STAND, DOUBLE/HIT, SURRENDER, or SPLIT
	 * @return one of STAND, HIT, or SPLIT
	 */
	private int mapResponse(int response) {
		switch (response) {
	case S: case H: case SP:
			break;
	case DS:
			response = S;
			break;
	case DH: case SU:
			response = H;
			break;
		}
		return response;
	}
	
	private int splitIndexFromPlayerHand(BJPlayerHand pHand) {
		Card dupCard = pHand.getCard(0);
	    //             0   1   2   3   4   5   6   7   8   9  10  11  12  13  14 	
		int [] map = {-1, -1,  8,  8,  7,  6,  5,  4,  3,  2,  1,  1,  1,  1,  0};
		return map[dupCard.getValue()];
	}
	
	private int hardIndexFromPlayerHand(int total) {
	    //             0   1   2   3   4   5   6   7   8   9  10  11  12  13  14  15  16  17  18  19  20 	
		int [] map = {-1, -1, -1, -1, -1,  8,  8,  8,  8,  7,  6,  5,  4,  3,  3,  2,  1,  0,  0,  0,  0};
		return map[total];
	}
	
	private int softIndexFromPlayerHand(int lowMin) {
	    //             0   1   2   3   4   5   6   7   8   9   	
		int [] map = {-1, -1,  5,  5,  4,  4,  3,  2,  1,  0};
		return map[lowMin];
	}

	private int indexFromDealerHand(BJDealerHand dHand) {
		Card upCard = dHand.getUpCard();
	    //             0   1   2   3   4   5   6   7   8   9  10  11  12  13  14 	
		int [] map = {-1, -1,  0,  1,  2,  3,  4,  5,  6,  7,  8,  8,  8,  8,  9};
		return map[upCard.getValue()];
	}

	
}
