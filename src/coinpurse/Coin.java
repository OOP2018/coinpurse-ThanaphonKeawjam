package coinpurse;

/**
 * Coin represent coinage (money) with a fixed value and currency.
 * @author Thanaphon Keawjam
 *
 */
public class Coin implements Comparable<Coin>{
	
	private double value;
	private String currency;
	
	/**
	 * Constructor for a Coin with a value and currency.
	 * The value must not be negative.
	 * @param value is value of coin.
	 * @param currency is coin's currency.
	 */
	public Coin(double value, String currency){
		this.value = value;
		this.currency = currency;
	}
	
	/**
	 * Get value of the coin.
	 * @return the value of the coin.
	 */
	public double getValue(){
		return value;
	}
	
	/**
	 * Get currency of the coin.
	 * @return the currency of the coin.
	 */
	public String getCurrency(){
		return currency;
	}
	
	/**
	 * Compares this object to the specified object.
	 * @param arg is the object use to compare.
	 * @return true if the value and the currenct are the same.
	 */
	@Override
	public boolean equals(Object arg) {
		if(arg == null) return false;
		if(arg.getClass() !=  this.getClass()) return false;
		Coin other = (Coin)arg;
		return this.currency.equals(other.currency) && this.value == other.value;
	}
	
	/**
	 * Compares this object with the specified object for order.
	 * @param coin is the object that want to compare.
	 * @return 1 if value this object before specified object,
	 * 		   -1 if value this object after specified object,
	 * 		   and 0 if both are the same value.
	 */
	@Override
	public int compareTo(Coin coin){
		Coin other = (Coin)coin;
		if(this.value > other.value){
			return 1;
		}else if(this.value < other.value){
			return -1;
		}else {
			return 0;
		}
	}
	
	/**
	 * Return a String describing what is in the purse.
	 * @return String describing what is in the purse
	 */
	@Override
	public String toString() {
		return String.format("%.2f-%s", value, currency);
	}

}
