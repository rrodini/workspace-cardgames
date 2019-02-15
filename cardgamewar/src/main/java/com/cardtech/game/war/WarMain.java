package com.cardtech.game.war;

import com.cardtech.game.war.WarPlayer;
import com.cardtech.game.Player;
import com.cardtech.game.war.WarGame;

public class WarMain {

	private static WarGame game;

	private static WarPlayer [] players = {
			new WarPlayer("manny"),
			new WarPlayer("moe"),
	};

	public static void main(String[] args) {
		game = new WarGame(players);
		game.initialize(true);
		game.play();
		int noWinners = game.getWinner().size();
		System.out.println("Winner(s):");
		for (Player player: game.getWinner()) {
			System.out.printf("%s\n", player.getName());
		}
	}

}
