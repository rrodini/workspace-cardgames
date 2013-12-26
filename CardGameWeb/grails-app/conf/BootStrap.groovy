import com.cardtech.core.Card;
import com.cardtech.game.PokerRank;
import com.cardtech.web.PlayingCard;

class BootStrap {

    def init = { servletContext ->
		for (Card c: Card.values()) {
			String cardName = c.toString()
			System.out.printf("Creating playing card: %s", cardName)
			PlayingCard pc = new PlayingCard()
			pc.name = cardName
			if (pc.validate()) {
				pc.save()
			} else {
				println pc.getErrors()
			}
		}
		for (PokerRank r: PokerRank.values()) {
			String rankName = r.toString()
			System.out.printf("Creating poker rank: %s", rankName)
			com.cardtech.web.PokerRank pr = new com.cardtech.web.PokerRank()
			pr.name = rankName
			if (pr.validate()) {
				pr.save()
			} else {
				println pr.getErrors()
			}
		}
		
		['Bob','Ed','Fred','Dave'].each { name ->
			System.out.println("Creating player: $name")
			com.cardtech.web.Player player = new com.cardtech.web.Player(firstName: name)
			if (player.validate()) {
				player.save()
			} else {
				println player.getErrors()
			} 
		}
		
    }
    def destroy = {
    }
}
