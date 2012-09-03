package card.game;
import java.util.Set;
import java.util.TreeMap;

import card.core.Deck;


public class MainClass {

	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.show();
		deck.cut();
		deck.show();
		
//		Hand hand1 = new Hand(deck.deal(13)); 
//		Hand hand2 = new Hand(deck.deal(13)); 
//		Hand hand3 = new Hand(deck.deal(13)); 
//		Hand hand4 = new Hand(deck.deal(13)); 
//		hand1.show();
//		hand2.show();
//		hand3.show();
//		hand4.show();
//		
//		deck.shuffle();
//		hand1 = new PokerHand(deck.deal(5));
//		hand2 = new PokerHand(deck.deal(5));
		
//		for (int i=0; i < 5; i++) {
//			Card card1 = hand1.getCard(i);
//			Card card2 = hand2.getCard(i);
//			int result = card1.compareTo(card2);
//			card1.show();
//			if (result == -1) {
//				System.out.print(" < ");
//			} else if (result == 0) {
//				System.out.print(" = ");
//			} else {
//				System.out.print(" > ");
//			}
//			card2.show();
//			System.out.println();
//		}
		
//		deck.shuffle();
//		PokerHand pokerHand1 = new PokerHand(deck.deal(5));
//		PokerHand pokerHand2 = new PokerHand(deck.deal(5));		
//		System.out.print("Hand1: "); pokerHand1.getRank(); pokerHand1.show();	
//		System.out.print("Hand2: "); pokerHand1.getRank(); pokerHand2.show();
		
//      Simple comparison: Hand1 vs. Hand2		
//		int result = pokerHand1.compareTo(pokerHand2);
//		System.out.print("Hand1 - " + pokerHand1.getRank());
//		if (result == -1) {
//			System.out.print(" < ");
//		} else if (result == 0) {
//			System.out.print(" = ");
//		} else {
//			System.out.print(" > ");
//		}
//		System.out.print("Hand2 - " + pokerHand2.getRank());

//		deck.shuffle();
//		// deal hands to 4 players
//		PokerHand [] hands = {new PokerHand(deck.deal(5)),new PokerHand(deck.deal(5)),
//		                 	  new PokerHand(deck.deal(5)),new PokerHand(deck.deal(5))};
//		// show the players hands after sorting
//		for (int i=0; i < 4; i++)  {
//			System.out.print(i + ": "); hands[i].getRank(); hands[i].show();	
//		}
//		TreeMap<PokerHand,Integer> treeMap = new TreeMap<PokerHand, Integer>();
//		for (int i=0; i < 4; i++) {
//			treeMap.put(hands[i], i);
//		}
//		Set<PokerHand> keySet = treeMap.descendingKeySet();
//		// show the ranks in descending order
//		for (PokerHand key : keySet){
//			System.out.printf("Rank: %s, player %d\n", key.getRank(), treeMap.get(key));
//		}
		
//		WarHand player0 = new WarHand();
//		WarHand player1 = new WarHand();
//		WarHand [] twoPlayers = new WarHand[2];
//		twoPlayers[0] = player0;
//		twoPlayers[1] = player1;
//
//		WarGame game = new WarGame(twoPlayers);
//		game.initialize(true);
//		game.play();
//		int winner = game.getWinner();
//		System.out.println("Winner is p" + winner);
		
	}
		

}
