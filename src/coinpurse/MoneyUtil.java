package coinpurse;

import java.util.ArrayList;
import java.util.List;

/**
 * MoneyUtil use to demonstrate compareTo() method,
 * print list of coins before and after sort the coins,
 * and print list of coins by filter currency.
 * @author Thanaphon Keawjam
 *
 */
public class MoneyUtil {

	public static void sortCoins(){
		List<Coin> coins = new ArrayList<Coin>();
		//add for test compareTo method.
		coins.add(new Coin(10.0, "Baht"));
		coins.add(new Coin(5.0, "Baht"));
		coins.add(new Coin(5.0, "Baht"));
		coins.add(new Coin(0.5, "Baht"));
		
		//add for test compareTo method(different currency but same value case).
		coins.add(new Coin(20.0, "USD"));
		coins.add(new Coin(20.0, "Peso"));
		
		//add for test filterByCurrency method.
		coins.add(new Coin(1000.0, "USD"));
		coins.add(new Coin(100.0, "USD"));
		
		System.out.println("======== Test compareTo ========");
		System.out.println(coins.get(0).compareTo(coins.get(1)));
		System.out.println(coins.get(3).compareTo(coins.get(1)));
		System.out.println(coins.get(1).compareTo(coins.get(2)));
		System.out.println();
		
		System.out.println("======== Different currency ========");
		System.out.println(coins.get(4).compareTo(coins.get(5)));
		System.out.println();
		
		System.out.println("======== Before sort ========");
		printCoins(coins);
		java.util.Collections.sort(coins);
		System.out.println();
		
		System.out.println("======== After sort =========");
		printCoins(coins);
		System.out.println();
		
		System.out.println("======== Same currency ========");
		List<Coin> filter = new ArrayList<>();
		filter = filterByCurrency(coins, "USD");
		printCoins(filter);
	}
	
	/**
	 * Print list of coins.
	 * @param coins is list of coins.
	 */
	public static void printCoins(List<Coin> coins){
		for(int i = 0; i < coins.size(); i++){
			System.out.println(coins.get(i));
		}
	}
	
	/**
	 * Return a list of coins that cotains only the coins from coins(the parameter).
	 * @param coins is list of coins.
	 * @param currency is coin's currency.
	 * @return list of coins that have same currency.
	 */
	public static List<Coin> filterByCurrency(List<Coin> coins, String currency){
		List<Coin> coinFilter = new ArrayList<>();
		for(int i = 0; i < coins.size(); i++){
			if(coins.get(i).getCurrency().equals(currency)){
				coinFilter.add(coins.get(i));
			}
		}
		return coinFilter;
	}
	
	/**
	 * Sort a list of coins and print the result.
	 * @param coins is list of coins.
	 */
	public static void sortCoins(List<Coin> coins){
		java.util.Collections.sort(coins);
		printCoins(coins);
	}
	
	public static void main(String[] args) {
		sortCoins();
	}
}
