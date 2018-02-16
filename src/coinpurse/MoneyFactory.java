package coinpurse;

/**
 * A class for creating money.
 * @author Thanaphon Keawjam
 */
public abstract class MoneyFactory {
	
	private static MoneyFactory factory;

	/**
	 * Get an instance of MoneyFactory. 
	 * This method returns an object of a subclass (such as ThaiMoneyFactor).
	 * The instance is a Singleton -- it always returns the same object. 
	 * @return object of subclass.
	 */
	public static MoneyFactory getInstance(){
		if(factory == null) factory = new ThaiMoneyFactory();
		return factory;
	}
	
	/**
	 * Creat new money object in the local currency.
	 * If the value is not a valid currency amount, then throw IllegalArgumentException. 
	 * @param value is value of money.
	 * @return object of valuable.
	 */
	public abstract Valuable creatMoney(double value);
	
	/**
	 * Accepts money value as a String
	 * @param value is value of money.
	 * @return value from creatMoney(double) method.
	 */
	public Valuable creatMoney(String value){
		double values = 0;
		try{
			values = Double.parseDouble(value);
		}catch(IllegalArgumentException ex){
			System.out.println(ex.getMessage());
		}
		return creatMoney(values);
	}
	
	/**
	 * Static method to a "set" the MoneyFactory object that is used.
	 * This is mostly for testing of MoneyFactory.
	 * @param mf is object of MoneyFactory.
	 */
	public static void setFactory(MoneyFactory mf){
		factory = mf;
	}
	
}
