package coinpurse;

/**
 * Create Thailand money.
 * @author Thanaphon Keawjam
 */
public class ThaiMoneyFactory extends MoneyFactory{
	
	private static long nextSerialNumber = 100000;
	private long serialNumber;

	/**
	 * Create Thailand money. 0.25, 0.5, 1, 2, 5, and 10 currency are Baht.
	 * 20, 50, 100, 500, and 1000 currency are Baht.If another value can not create money.
	 * @return new Coin or new BankNote of Thailand.
	 * 		   Return null if it don't have this value.
	 */
	@Override
	public Valuable createMoney(double value) {
		if(value < 20){
			if(value == 0.25 || value == 0.5 || value == 1 || value == 2 || value == 5 || value == 10){
				return new Coin(value, "Baht");
			}else{
				throw new IllegalArgumentException("Thailand doesn't have " + value + " coin.");
			}
		}else{
			if(value == 20 || value == 50 || value == 100 || value == 500 || value == 1000){
				serialNumber = nextSerialNumber++;
				return new BankNote(value, "Baht", serialNumber);
			}else{
				throw new IllegalArgumentException("Thailand doesn't have " + value + " note.");
			}
		}
	}

}
