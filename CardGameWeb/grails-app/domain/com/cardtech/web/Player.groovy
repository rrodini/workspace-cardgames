package com.cardtech.web
import com.cardtech.web.GameParticipation

class Player {
	String firstName
	//String lastName

    static constraints = {
		firstName (blank:false)
		//lastName(blank: true)
    }
	
	String toString() {
		return "${firstName}"
	}
	
	static hasMany = [gamesPlayedIn: GameParticipation]
	
}
