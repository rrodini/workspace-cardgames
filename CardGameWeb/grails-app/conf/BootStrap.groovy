import com.cardtech.core.Card;
import com.cardtech.game.PokerRank;
import com.cardtech.web.PlayingCard;

class BootStrap {

    def init = { servletContext ->
		for (Card c: Card.values()) {
			String cardName = c.toString()
			// skip the Ace of Hearts by uncommenting the next three lines.
//			if (cardName.equalsIgnoreCase("ACE_HEART")) {
//				continue;
//			}
			PlayingCard pc = new PlayingCard()
			pc.name = cardName
			if (!PlayingCard.findByName(cardName)) {
				if (pc.validate()) {
					System.out.printf("Creating playing card: %s", cardName)
					pc.save()
				} else {
					println pc.getErrors()
				}
			}
		}
		for (PokerRank r: PokerRank.values()) {
			String rankName = r.toString()
			com.cardtech.web.PokerRank pr = new com.cardtech.web.PokerRank()
			pr.name = rankName
			if (!com.cardtech.web.PokerRank.findByName(rankName)) {
				if (pr.validate()) {
					System.out.printf("Creating poker rank: %s", rankName)
					pr.save()
				} else {
					println pr.getErrors()
				}
			}
		}
		
		['Bob','Ed','Fred','Dave'].each { name ->
			com.cardtech.web.Player player = new com.cardtech.web.Player(firstName: name)
			if (!com.cardtech.web.Player.findByFirstName(player.firstName)) {
				if (player.validate()) {
					System.out.printf("Creating player: %s", player.firstName)
					player.save()
				} else {
					println player.getErrors()
				}
			}
		}
		
    }
    def destroy = {
    }
}
