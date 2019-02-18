package com.cardtech.game.blackjack;

import static com.cardtech.game.blackjack.BJHandValue.Firmness.*;
import static com.cardtech.game.blackjack.BJResponse.*;

/**
 * BJDealerStrategy is the fixed strategy of the dealer following house rules.
 * Dealer takes hits when his total is < 17.
 * If the total is 17 and the hand is "hard" the dealer stands.
 * However, if the total is 17 and the hand is "soft" a house rule kicks in.
 * S17 states that the dealer must take a HIT (favorable to player).
 * H17 states that the dealer must stand (favorable to house).  
 */
public class BJDealerStrategy implements BJStrategy {

	@Override
	public int hit(BJPlayerHand playerHand, BJDealerHand dealerHand) {
		// assumption is that the playerHand is null.
		int response = -1;
		BJHandValue val = BJHandEvaluator.evaluate(dealerHand);
		if (val.getFirmness() == HARD_HAND) {
			int count = val.getLowValue();
			response = (count < 17)? H: S;
		} else {
			// SOFT hand
			// S17 rule => dealer must take a hit at 17. val.getHighValue() <= 17 => true.
			// H17 rule => dealer declines a hit at 17.  val.getHighValue() < 17  => true.
			response = (val.getHighValue() <= 17)? H : S;  // S17 here
		}		
		return response;
	}

}
