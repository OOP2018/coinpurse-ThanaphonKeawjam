package coinpurse;

/**
 * Banknote represents coinage (money) with fixed value and currency.
 * @author Thanaphon Keawjam
 *
 */
public class BankNote implements Valuable{
	
	private static long nextSerialNumber = 1000000;
	private double value;
	private String currency;
	private long serialNumber;

	/**
	 * Initialize a new BankNote.
	 * @param value is value of banknote.
	 * @param currency is banknote's currency.
	 */
	public BankNote(double value, String currency){
		this.value = value;
		this.currency = currency;
		serialNumber = nextSerialNumber++;
	}
	
	/**
	 * Get value of banknote.
	 * @return the value of this BankNote.
	 */
	@Override
	public double getValue() {
		return value;
	}
	
	/**
	 * Get banknote's currency.
	 * @return the currency 
	 */
	@Override
	public String getCurrency() {
		return currency;
	}
	
	/**
	 * Get the serial number.
	 * @return the serial number (long).
	 */
	public long getSerial(){
		return serialNumber;
	}
	
	/**
	 * Compares this object to the specified object.
	 * @param obj is the object use to compare.
	 * @return true if obj is a BankNote and has the same currency and value.
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(this.getClass() != obj.getClass()) return false;
		BankNote other = (BankNote)obj;
		return this.currency.equals(other.currency) && this.value == other.value;
	}
	
	/**
	 * Return a String.
	 * @return String describing.
	 */
	@Override
	public String toString() {
		return String.format("%.2f-%s note [%d]", value, currency, serialNumber);
	}
	
}
