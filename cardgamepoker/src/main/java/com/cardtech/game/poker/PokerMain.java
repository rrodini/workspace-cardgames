package com.cardtech.game.poker;
import com.cardtech.game.poker.PokerPlayer;

public class PokerMain {

	private static PokerGame game;
	
	private static PokerPlayer [] players = {
			new PokerPlayer("manny"),
			new PokerPlayer("moe"),
			new PokerPlayer("jack"),
			new PokerPlayer("rock"),			
	};

	private static void showWinner(int which) {
		PokerRankWithHighCards rank = game.getWinningRank();
		System.out.printf("%s wins with %s", game.getWinner().get(which), rank.toString());
	}
	
	public static void main(String[] args) {
		game = new PokerGame(players);
		game.initialize(true);
		game.play();
		game.show();
		// Note: ties are possible in poker!
		int noWinners = game.getWinner().size();
		if (noWinners == 1) {
			showWinner(0);
		} else {
			System.out.printf("This is a %d way tie!", noWinners);
			for (int i = 0; i < noWinners; i++ ) {
				showWinner(i);
			}
		}

	}

}
