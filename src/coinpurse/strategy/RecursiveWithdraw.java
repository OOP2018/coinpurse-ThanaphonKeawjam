package coinpurse.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import coinpurse.Money;
import coinpurse.Valuable;
import coinpurse.ValueComparator;

/**
 * Find amount of withdraw by recursive solution.
 * @author Thanaphon Keawjam
 */
public class RecursiveWithdraw implements WithdrawStrategy{
	
	List<Valuable> list = new ArrayList<>();

	/**
	 * Find and return items from a collection whose total value equals
	 * the requested amount.
	 * @param amount is the amount of money to withdraw, with currency.
	 * @param money the contents that are available for possible withdraw.
	 * 		  Must not be null, but may be an empty list. This list is not modified.
	 * @return if a solution is found, return a List containing references
	 * 		   from the money parameter (List) whose sum equals the amount.
	 * 		   If a solution is not found, return null.
	 */
	@Override
	public List<Valuable> withdraw(Valuable amount, List<Valuable> money) {
		if(amount.getValue() == 0) return new ArrayList<>();
		if(amount.getValue() < 0 || money.size() == 0) return null;
		Valuable vale = money.get(0);
		if(amount.getCurrency().equals(money.get(0).getCurrency())) {
			list = withdraw(new Money(amount.getValue() - money.get(0).getValue(), amount.getCurrency()), money.subList(1, money.size()));
			if(list != null) {
				list.add(vale);
				Collections.sort(list, comp);
				Collections.reverse(list);
				return list;
			}
			return withdraw(amount, money.subList(1, money.size()));
		}
		return null; 
    	
	}

}
