package coinpurse;

/**
 * Money class is a superclass of Coin class and BankNote class.
 * @author Thanaphon Keawjam
 */
public class Money implements Valuable{

	protected double value;
	protected String currency;

	/**
	 * Initialize a new Money
	 * @param value is value of the coin or banknote.
	 * @param currency is currency of the coin or banknote. 
	 */
	public Money(double value, String currency) {
		this.value = value;
		this.currency = currency;
	}

	/**
	 * Get value of the coin or banknote.
	 * @return the value of the coin or banknote.
	 */
	public double getValue() {
		return value;
	}

	/**
	 * Get currency of the coin or banknote.
	 * @return the currency of the coin or banknote.
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Compares this object to the specified object.
	 * @param arg is the object use to compare.
	 * @return true if the value and the currency are the same.
	 */
	@Override
	public boolean equals(Object arg) {
		if(arg == null) return false;
		if(arg.getClass() !=  this.getClass()) return false;
		Money other = (Money)arg;
		return this.currency.equals(other.currency) && this.value == other.value;
	}

	/**
	 * Compares this object with the specified object for order.
	 * @param o is the object that want to compare.
	 * @return if currency are same return value from compare method.
	 * 		   if currency are not same return value from compareTo method.
	 */
	@Override
	public int compareTo(Valuable o) {
		if(this.getCurrency().equals(o.getCurrency())) 
			return Double.compare(this.getValue(), o.getValue());
		else return this.getCurrency().compareTo(o.getCurrency());
	}
}