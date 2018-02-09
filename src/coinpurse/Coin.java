package coinpurse;

/**
 * Coin represent coinage (money) with a fixed value and currency.
 * @author Thanaphon Keawjam
 *
 */
public class Coin extends Money{
	
	/**
	 * Constructor for a Coin with a value and currency.
	 * The value must not be negative.
	 * @param value is value of coin.
	 * @param currency is coin's currency.
	 */
	public Coin(double value, String currency){
		super(value, currency);
	}
	
	/**
	 * Return a String describing what is in the purse.
	 * @return String describing what is in the purse
	 */
	@Override
	public String toString() {
		return String.format("%.2f-%s", getValue(), getCurrency());
	}

}
