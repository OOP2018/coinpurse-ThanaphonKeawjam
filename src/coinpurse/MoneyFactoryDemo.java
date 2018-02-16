package coinpurse;

/**
 * A class demo of MoneyFactory.
 * @author Thanaphon Keawjam
 */
public class MoneyFactoryDemo {
	
	/**
	 * Initialize.
	 * @param args is not use
	 */
	public static void main(String[] args) {
		printDemo();
	}
	
	/**
	 * Test all case in MoneyFactory.
	 */
	public static void printDemo(){
		MoneyFactory.setFactory(new ThaiMoneyFactory());
		MoneyFactory f1 = MoneyFactory.getInstance();
		System.out.println("f1 is a " + f1.getClass().getName());
		MoneyFactory f2 = MoneyFactory.getInstance();
		System.out.println("f2 is a " + f2.getClass().getName());
		System.out.print("f1 == f2? ");
		System.out.println(f1==f2);
		System.out.println("============================");
		String[] values = {"0.25", "0.5", "1.0", "2.0", "5.0", "10.0", "20.0", "50.0"};
		for(String str : values){
			System.out.println("creatMoney is " + str);
			try{
				Valuable value = f1.creatMoney(str);
				System.out.println(value.toString());
			}catch(IllegalArgumentException ex){
				System.out.println(ex.getMessage());
			}
		}
		System.out.println("=================================");
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory f3 = MoneyFactory.getInstance();
		String[] values2 = {"0.05", "0.10", "0.20", "0.50", "1.0", "2.0", "100.0", "1000.0"};
		for(String str : values2){
			System.out.println("creatMoney is " + str);
			try{
				Valuable value = f3.creatMoney(str);
				System.out.println(value.toString());
			}catch(IllegalArgumentException ex){
				System.out.println(ex.getMessage());
			}
		}
		
	}

}
