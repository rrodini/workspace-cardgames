package com.cardtech.game;

public class WarPlayer extends Player {
	
 /**
  * WarPlayer is the simplistic representation of a war player. <br/>
  * War players of course have a hand object but this is an instance variable of WarGame. <br/>
  * Why? WarGames are perfect for concurrent games, but a WarPlayer is not.
  */
	public WarPlayer() {
		super();
	}
	
	public WarPlayer(String name) {
		super(name);
	}

 /**
  * During simulations it is interesting to track the minimum number of rounds needed to win a war game.  <br/>
  * (Low number indicates a lot of "war" rounds.)  Similarly it is interesting to track the maximum number of rounds. <br/>
  * This is the purpose of: <br/>
  * 1) minRoundsToAWin and <br/>
  * 2) maxRoundsToAWin. <br/>
  */
	
	int minRoundsToAWin = Integer.MAX_VALUE;
	int maxRoundsToAWin = 0;
	
 /**
  * After a simulation update the min and max tracking values.
  * @param rounds - # of top level rounds till game is decided.
  */
	public synchronized void setNewRoundsToAWin(int rounds){
		if (rounds < minRoundsToAWin) {
			minRoundsToAWin = rounds;
		}
		if (rounds > maxRoundsToAWin) {
			maxRoundsToAWin = rounds;
		}
	}

}
