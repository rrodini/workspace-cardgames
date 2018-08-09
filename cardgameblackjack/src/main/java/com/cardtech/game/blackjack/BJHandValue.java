package com.cardtech.game.blackjack;

import java.util.StringJoiner;

/**
 * BJHandValue class represents the value of a blackjack hand.
 * Not sure if Firmness will be used.
 */
public class BJHandValue {
	/**
	 * Firmness the hand SOFT or HARD.
	 */
	public enum Firmness {SOFT_HAND, HARD_HAND};
	/**
	 * Precis is a summary value.  The total can be <, =, or > to 21.
	 */
	public enum Precis {UNDER21_VALUE, EXACTLY21_VALUE, OVER21_VALUE};
	private final Firmness firmness;
	private final Precis precis;
	private final int aceCount;
	private final int lowValue;
	private final int highValue;
	
	BJHandValue(Firmness firmness, Precis precis, int aceCount, int lowValue, int highValue) {
		this.firmness = firmness;
		this.precis = precis;
		this.aceCount = aceCount;
		this.lowValue = lowValue;
		this.highValue = highValue;
	}
	
	public Firmness getFirmness() {
		return firmness;
	}

	public Precis getPrecis() {
		return precis;
	}

	public int getAceCount() {
		return aceCount;
	}

	public int getLowValue() {
		return lowValue;
	}

	public int getHighValue() {
		return highValue;
	}
	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(",", this.getClass().getSimpleName()+ "[", "]");
		sj.add(firmness.toString());
		sj.add(precis.toString());
		sj.add("" + aceCount);
		sj.add("" + lowValue);
		sj.add("" + highValue);
		return sj.toString();
	}
	@Override
	// Need this for unit testing (value equality).
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof BJHandValue)) {
			return false;
		}
		BJHandValue val = (BJHandValue) o;
		return val.firmness == this.firmness &&
			   val.precis == this.precis &&
			   val.aceCount == this.aceCount &&
			   val.lowValue == this.lowValue &&
			   val.highValue == this.highValue;
	}
	@Override
	// As per Bloch.
	public int hashCode() {
		int result = 17;
		result = 31 * result + this.firmness.ordinal();
		result = 31 * result + this.precis.ordinal();
		result = 31 * result + this.aceCount;
		result = 31 * result + this.lowValue;
		result = 31 * result + this.highValue;
		return result;
	}
}
