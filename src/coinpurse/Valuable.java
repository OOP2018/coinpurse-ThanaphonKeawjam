package coinpurse;

/**
 * An interface for objects having a monetary value and currency. 
 * @author Thanaphon Keawjam
 */
public interface Valuable extends Comparable<Valuable>{

	/**
	 * get value of object.
	 * @return vale of object.
	 */
	public double getValue();
	
	/**
	 * get currency of object.
	 * @return currency of objeect.
	 */
	public String getCurrency();
	
}
