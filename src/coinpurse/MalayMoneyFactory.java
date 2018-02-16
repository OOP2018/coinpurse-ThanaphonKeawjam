package coinpurse;

/**
 * Create malaysia money.
 * @author Thanaphon Keawjam
 */
public class MalayMoneyFactory extends MoneyFactory{
	
	private static long nextSerialNumber = 1000000;
	private long serialNumber;

	/**
	 * Create malaysia money. 0.05, 0.1, 0.2, and 0.5 currency are Sen and value must * 100.
	 * 1, 2, 5, 10, 20, 50, and 100 currency are Ringgit.If another value can not create money.
	 * @return new Coin or new BankNote of malaysia.
	 * 		   Return null if it don't have this value.
	 */
	@Override
	public Valuable creatMoney(double value) {
		if(value < 1){
			if(value == 0.05 || value == 0.1 || value == 0.2 || value == 0.5){
				return new Coin(value * 100, "Sen");
			}else{
				throw new IllegalArgumentException("Malaysia doesn't have " + value * 100 + " Sen");
			}
		}else{
			if(value == 1 || value == 2 || value == 5 || value == 10 || value == 20 || value == 50 || value == 100){
				serialNumber = nextSerialNumber++;
				return new BankNote(value, "Ringgit", serialNumber);
			}else{
				throw new IllegalArgumentException("Malaysia doesn't have " + value + " Ringgit");
			}
		}
	}

}
