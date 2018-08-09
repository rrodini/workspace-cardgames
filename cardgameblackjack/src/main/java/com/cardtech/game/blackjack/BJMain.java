package com.cardtech.game.blackjack;
import  com.cardtech.game.blackjack.BJPlayer;

import java.util.List;

import com.cardtech.core.Deck;
import com.cardtech.core.Utils;
import  com.cardtech.game.Player;
public class BJMain {
	// everyone plays with the same strategy for now.
	static BJPlayerStrategy strategy = new BJPlayerStrategy();
	static BJDealerStrategy dealerStrategy = new BJDealerStrategy();

	static BJPlayer [] players = { 
			new BJPlayer("manny", strategy), 
			new BJPlayer("moe", strategy), 
			new BJPlayer("jack", strategy), 
			new BJPlayer("rock", strategy), 
			new BJPlayer("dealer", dealerStrategy) };
	
	
	public static void main(String [] args) {
		int count = players.length;
		BJGame game;
		if (count > 3) { 
			Deck deck = Utils.createMultipleDecksSuitOrder(count - 2);
			game = new BJGame(deck, players);
			
		} else { // play with a 52 card deck
			game = new BJGame(players);
		}
		
		List<Player> winners;
		game.initialize(true);
		game.play();
		winners = game.getWinner();
		System.out.println("Winners:");
		for (Player winner: winners) {
			System.out.println(winner.toString());
		}
	}

}
