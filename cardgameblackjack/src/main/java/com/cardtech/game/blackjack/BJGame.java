package com.cardtech.game.blackjack;

import static com.cardtech.game.blackjack.BJHandValue.Precis.*;
import static com.cardtech.game.blackjack.BJResponse.H;
import static com.cardtech.game.blackjack.BJResponse.S;
import static com.cardtech.game.blackjack.BJResponse.SP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.cardtech.core.Card;
import com.cardtech.core.Deck;
import com.cardtech.game.CardGame;
import com.cardtech.game.Player;

public class BJGame extends CardGame {
	// a "natural" is a ACE plus 10 card on 2nd card.
	boolean natural = false;
	List<Player> winners = new ArrayList<>();
   /**
    * This constructor constructs a blackjack game with the given players.	
    * @param players array of Players.  The last player is the dealer by convention.
    */
	public BJGame(BJPlayer... players) {
		// last player is the dealer.
		super(players);
		processPlayers(players);
	}
   /**
    * This constructor constructs a blackjack game with the players and a deck.
    * The desk usually is multiple standard 52 card decks.
    * @param deck deck of cards for the game.
    * @param players array of players.  The last player is the dealer by convention.
    */
	public BJGame(Deck deck, BJPlayer... players) {
		// last player is the dealer.
		super(deck, players);
		processPlayers(players);
	}
	private void processPlayers(BJPlayer... players) {
		int num = players.length;
		// the last player is the dealer with his own strategy.
		// the next line changes the type from Player => BJDealer.
		//super.players.set(num - 1, new BJDealer("dealer")) ;
		// this is what the game framework wants. 
		// now sure this is the best implementation.
		for (int i = 0; i < num ; i++) {
			hands.set(i, new BJPlayerHand());
		}
		hands.set(num - 1, new BJDealerHand());
	}
   /**
	* Initialize the blackjack game.
	*/		
	@Override
	public void initialize(boolean shuffle) {
		// trivial
		if (shuffle) {
			deck.shuffle();
			deck.cut();
		}
	}
   /**
    * play plays the game of blackjack.
    */
	@Override
	public void play() {
		dealFirstCard();
		dealSecondCard();
		// winners may be determined here.
		boolean gameOver = evaluateNaturals();
		if (!gameOver) {
			for (int i = 0; i < players.size()-1; i++) {
				dealCardsToPlayerTilOutcome(i);
			}
			dealCardsToDealerTilOutcome();
			// winners will be determined here.
			evaluateOutcomes();
		}
		// getWinners returns the winners.
	}
   /**
    * Every player has two cards (the dealer has an up and down card).
    * Let's see who has a "natural."
    * @return true if any player or the dealer has a natural.
    */
	private boolean evaluateNaturals() {
		int num;
System.out.println("Evaluate Naturals");
		num = 0;
		for (Player player: players) {
			BJHandValue value = ((BJHand) hands.get(num)).getValue();
			if (value.getPrecis()== EXACTLY21_VALUE) {
				winners.add(player);
				natural = true;
			}
			num++;
		}
		
		return natural;
	}
   /**
    * Deal the first card to all of the players.
    */
	private void dealFirstCard() {
		int num = 0;
		System.out.println("Dealing 1st card:");
		for (Player player: players) {
			dealCardToPlayer(num);
System.out.printf("p%d hand: %s\n", num, hands.get(num).toString());
			num++;
		}
	}
   /**
	* Deal the second card to all of the players.
	*/
	private void dealSecondCard() {
		int num;
		System.out.println("Deal 2nd card:");
		num = 0;
		for (Player player: players) {
			dealCardToPlayer(num);
System.out.printf("p%d hand: %s\n", num, hands.get(num).toString());
			num++;
		}
	}
   /**
    * Deal a card to the player identified by their number. 
    * @param playerNo player's number.
    */
	private void dealCardToPlayer(int playerNo) {
		if (!deck.isEmpty()) {
			BJHand hand = (BJHand) hands.get(playerNo);
			Card c = deck.deal(1).get(0);
			hand.addCard(c);
			hand.evaluate();
		}
	}
   /**
    *  Play continues if there are no naturals.  Each player plays out his hand
    *  one by one.  Before the third card is dealt the player may have the option to
    *  split his hand.  This is implemented by inserting the player into the player's
    *  list again and creating two hands of one card each.
    * @param playerNo player's number.
    */
	private void dealCardsToPlayerTilOutcome(int playerNo) {
System.out.printf("Player: %s\n", players.get(playerNo).toString());
		BJPlayer player = (BJPlayer) players.get(playerNo);
		BJStrategy strat = player.getStrategy();
		BJHand hand = (BJHand) hands.get(playerNo);
		BJHandValue.Precis precis = hand.getValue().getPrecis();
		boolean stand = false;
		while (precis != EXACTLY21_VALUE && precis != OVER21_VALUE && !stand) {
			int dealerNo = hands.size() - 1;
			int response = strat.hit((BJPlayerHand) hands.get(playerNo),(BJDealerHand) hands.get(dealerNo));
			if (response == H) {
System.out.printf("p%d response: HIT\n", playerNo);
				dealCardToPlayer(playerNo);
			} else if (response == S) {
System.out.printf("p%d response: STAND\n", playerNo);
				stand = true;
			} else if (response == SP) {
System.out.printf("p%d response: SPLIT\n", playerNo);
				splitHand(playerNo);
			} else {
				throw new IllegalStateException("invalid response: " + response);
			}
			hand = (BJHand) hands.get(playerNo);
			precis = hand.getValue().getPrecis();
		}
		System.out.printf("p%d outcome: %s\n", playerNo, precis.toString());
		System.out.printf("p%d hand: %s\n", playerNo, hands.get(playerNo).toString());
	}
   /**
    * Now the dealer must play out his hand.	
    */
	private void dealCardsToDealerTilOutcome() {
		int dealerNo = hands.size() - 1;
System.out.printf("Player: %s\n", players.get(dealerNo).toString());
		BJPlayer player = (BJPlayer) players.get(dealerNo);
		BJStrategy strat = player.getStrategy();
		BJDealerHand hand = (BJDealerHand) hands.get(dealerNo);
		// now we can see the dealer's down card.
		hand.showDownCard();
		BJHandValue.Precis precis = hand.getValue().getPrecis();
		boolean stand = false;
		while (precis != EXACTLY21_VALUE && precis != OVER21_VALUE && !stand) {
			int response = strat.hit(null,(BJDealerHand) hands.get(dealerNo));
			if (response == H) {
System.out.printf("p%d response: HIT\n", dealerNo);
				dealCardToPlayer(dealerNo);
			} else if (response == S) {
System.out.printf("p%d response: STAND\n", dealerNo);
				stand = true;
			} else {
				throw new IllegalStateException("invalid response: " + response);
			}
			precis = hand.getValue().getPrecis();
		}
System.out.printf("p%d outcome: %s\n", dealerNo, precis.toString());
System.out.printf("p%d hand: %s\n", dealerNo, hands.get(dealerNo).toString());
	}
   /**
    * The player has decided to split his hand.	
    * @param playerNo player's number
    */
	/* private */ void splitHand(int playerNo) {
		// manipulate the inherited players and hands list
		// by splitting the player's hand.  Do this by re-inserting
		// the player into the players list and inserting the split hands
		// in the hands list.
		BJPlayerHand hand = (BJPlayerHand) hands.get(playerNo);
		BJPlayerHand split1 = new BJPlayerHand(new ArrayList<>(Arrays.asList(hand.getCard(0))));
		BJPlayerHand split2 = new BJPlayerHand(new ArrayList<>(Arrays.asList(hand.getCard(1))));
		hands.set(playerNo, split1);
		
		insertPlayerAndHand(playerNo, (BJPlayer) players.get(playerNo), split2);
		
		// as per wizardsofodds.com/blackjack/basics 
		// "...dealer will automatically give each card a second card..."
		// TODO: guarantee that split Aces get only one card.
		dealCardToPlayer(playerNo);
		dealCardToPlayer(playerNo+1);
for (int i = playerNo; i <= (playerNo + 1); i++) {
	System.out.printf("p%d split hand: %s\n", i, hands.get(i).toString());
}
	}
	
	private void insertPlayerAndHand(int playerNo, BJPlayer player, BJPlayerHand hand) {
		// assume that players and hands are in one-to-one correspondence.
		int last = players.size();
		// add a slot at the end of each list.
		players.add(null);
		hands.add(null);
		// now move elements to the end opening up a gap.
		for (int i = last; i > playerNo + 1; i--) {
			players.set(i, players.get(i - 1));
			hands.set(i, hands.get(i - 1));
		}
		// now set the gap entry.
		players.set(playerNo + 1, player);
		hands.set(playerNo + 1, hand);
	}
	
	@Override
	public List<Player> getWinner() {
System.out.print("Winner(s): ");
for (Player p: winners) {
	System.out.print(p.getName() + " ");
}
System.out.println();
		return winners;
	}

	private void evaluateOutcomes() {
		int num = players.size();
		showPlayerHands();
		BJPlayer dealer = (BJPlayer) players.get(players.size() - 1);
		BJHandValue dealerValue = ((BJHand) hands.get(players.size() - 1)).getValue();
		int dealerTotal = dealerValue.getLowValue();
		if (dealerValue.getPrecis() == OVER21_VALUE) {
			// every player who hasn't gone BUST has won.
			for (int i = 0; i < num - 1; i++) {
				BJHandValue value = ((BJHand) hands.get(i)).getValue();
				if (value.getPrecis() != OVER21_VALUE) {
					winners.add(players.get(i));
				}
			}
		} else {
			// must compare player totals with dealer's total.
			for (int i = 0; i < num - 1; i++) {
				BJHandValue value = ((BJHand) hands.get(i)).getValue();
				int playerTotal = (value.getHighValue() > 21) ? value.getLowValue() : value.getHighValue();
				if ((value.getPrecis() != OVER21_VALUE) && (playerTotal > dealerTotal)) {
					winners.add(players.get(i));
				}
			}
			if (winners.size() == 0) {
				winners.add(dealer);
			}
		}
	}
	
	private void showPlayerHands() {
System.out.println("Game over.");
		int i = 0;
		for (Player player: players) {
System.out.printf("p%d hand: %s\n", i, hands.get(i).toString());
			i++;
		}
	}
	
	private boolean isDealerWinner() {
		return winners.contains(players.get(players.size() - 1));
	}

}
