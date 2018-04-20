package coinpurse;

import java.util.ArrayList;
import java.util.Arrays;
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
	 * Return a list of stuff that implements Valuable that contains 
	 * only the currency from money(the parameter).
	 * @param money is list of value.
	 * @param currency is value's currency.
	 * @return list of stuff that implements Valuable that have same currency.
	 */
	public static <E extends Valuable> List<E> filterByCurrency(List<E> money, String currency){
		List<E> coinFilter = new ArrayList<>();
		for(int i = 0; i < money.size(); i++){
			if(money.get(i).getCurrency().equals(currency)){
				coinFilter.add(money.get(i));
			}
		}
		return coinFilter;
	}
	
	/**
	 * Sort a list of value.
	 * @param money is list of value.
	 */
	public static void sortMoney(List<? extends Valuable> money){
		Comparator<Valuable> comp = new ValueComparator();
		java.util.Collections.sort(money, comp);
	}
	
	/**
	 * Return the larger argument, based on sort oder, using
	 * the objects' own compareTo method for comparing.
	 * @param args one or more Comparable objects to compare.
	 * @return the argument that would be last if sorted the elements.
	 * @throws IllegalArgumentException if no arguments given. 
	 */
	public static <E extends Comparable<? super E>> E max(E ... args) {
		if(args == null || args.length == 0) throw new IllegalArgumentException();
		E max = args[0];
		for(int i = 0; i < args.length; i++) {
			max = (args[i].compareTo(max) > 0) ? args[i]:max; 
		}
		return max;
	}
	
	public static void main(String[] args) {
		Money m1 = new BankNote(100, "Baht", 1);
		Money m2 = new BankNote(500, "Baht", 2);
		Money m3 = new Coin(20, "Baht");
		Money max = MoneyUtil.max(m1, m2, m3);
		System.out.println(max);
		System.out.println("**********");
		List<BankNote> list = new ArrayList<BankNote>();
		list.add(new BankNote(10.0, "USD",1));
		list.add(new BankNote(500.0, "Baht", 1));
		MoneyUtil.sortMoney(list);
		System.out.println(list);
		System.out.println("**********");
		List<Coin> coins = Arrays.asList(new Coin(5, "Baht"), new Coin(0.1, "Rinngit"), new Coin(5, "Cent"));
		List<Coin> result = MoneyUtil.filterByCurrency(coins, "Baht");
		System.out.println(result);
	}
}
