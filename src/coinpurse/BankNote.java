package coinpurse;

/**
 * Banknote represents coinage (money) with fixed value and currency.
 * @author Thanaphon Keawjam
 *
 */
public class BankNote extends Money{
	
	private static long nextSerialNumber = 1000000;
	private long serialNumber;

	/**
	 * Initialize a new BankNote.
	 * @param value is value of banknote.
	 * @param currency is banknote's currency.
	 */
	public BankNote(double value, String currency){
		super(value, currency);
		serialNumber = nextSerialNumber++;
	}
	
	/**
	 * Get the serial number.
	 * @return the serial number (long).
	 */
	public long getSerial(){
		return serialNumber;
	}
	
	/**
	 * Return a String.
	 * @return String describing.
	 */
	@Override
	public String toString() {
		return String.format("%.2f-%s note [%d]", getValue(), getCurrency(), getSerial());
	}
	
}
