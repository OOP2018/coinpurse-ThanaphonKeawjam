package coinpurse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * MoneyUtil use to demonstrate compareTo() method,
 * print list of Valuable before and after sort the money,
 * and print list of Valuable by filter currency.
 * @author Thanaphon Keawjam
 *
 */
public class MoneyUtil {

	/**
	 * add value and currency to value list.
	 */
	public static void sortCoins(){
		List<Valuable> coins = new ArrayList<Valuable>();
		coins.add(new Coin(10.0, "Baht"));
		coins.add(new Coin(5.0, "Baht"));
		coins.add(new Coin(5.0, "Baht"));
		coins.add(new Coin(0.5, "Baht"));
		coins.add(new Coin(20.0, "USD"));
		coins.add(new Coin(20.0, "Peso"));
		coins.add(new Coin(1000.0, "USD"));
		coins.add(new Coin(100.0, "USD"));
		coins.add(new Coin(100.0, "Yen"));
		coins.add(new Coin(100.0, "Peso"));
		coins.add(new Coin(50.0, "Yen"));
		coins.add(new Coin(50.0, "Yuan"));
		coins.add(new Coin(120.0, "Yuan"));
		coins.add(new BankNote(200.0, "Yen"));
		coins.add(new BankNote(500.0, "Yen"));
		
		printCoins(coins);
		System.out.println("==========");
		sortCoins(coins);
		System.out.println("=========");
		System.out.println(filterByCurrency(coins, "USD"));
	}
	
	/**
	 * Print list of value.
	 * @param coins is list of value.
	 */
	public static void printCoins(List<Valuable> coins){
		for(int i = 0; i < coins.size(); i++){
			System.out.println(coins.get(i));
		}
	}
	
	/**
	 * Return a list of Valuable that cotains only the coins from coins(the parameter).
	 * @param money is list of value.
	 * @param currency is value's currency.
	 * @return list of value that have same currency.
	 */
	public static List<Valuable> filterByCurrency(List<Valuable> money, String currency){
		List<Valuable> coinFilter = new ArrayList<>();
		for(int i = 0; i < money.size(); i++){
			if(money.get(i).getCurrency().equals(currency)){
				coinFilter.add(money.get(i));
			}
		}
		return coinFilter;
	}
	
	/**
	 * Sort a list of value and print the result.
	 * @param coins is list of value.
	 */
	public static void sortCoins(List<Valuable> coins){
		Comparator<Valuable> comp = new ValueComparator();
		java.util.Collections.sort(coins, comp);
		printCoins(coins);
	}
	
	public static void main(String[] args) {
		sortCoins();
	}
}
