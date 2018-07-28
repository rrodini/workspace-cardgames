package com.cardtech.game;

import java.util.List;

import com.cardtech.core.Deck;

public class ConcreteCardGame extends CardGame {

	public ConcreteCardGame(Player[] players) {
		super(players);
	}
	
	public ConcreteCardGame(Deck deck, Player... players) {
		super(deck, players);
	}
	@Override
	public void initialize(boolean shuffle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Player> getWinner() {
		// TODO Auto-generated method stub
		return null;
	}

}
