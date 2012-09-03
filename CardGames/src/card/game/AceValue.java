package card.game;
/**
 * This enum is not currently used.  When it is used it will determine whether the ace can be played high or low.
 */
public enum AceValue {
	
	ACE_IS_LOW(1),
	ACE_IS_HIGH(13);

	int aceValue;
	
	AceValue(int n){
		this.aceValue = n;
	}
}
