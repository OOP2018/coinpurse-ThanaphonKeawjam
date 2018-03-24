package coinpurse.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import coinpurse.Valuable;
import coinpurse.ValueComparator;

/**
 * Find amount of withdraw by greedy solution.
 * @author Thanaphon Keawjam
 */
public class GreedyWithdraw implements WithdrawStrategy{
	
	private Comparator<Valuable> comp;

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
		comp = new ValueComparator();
    	
    	List<Valuable> list = new ArrayList<Valuable>();
    	double amountNeededToWithdraw = amount.getValue();
  
    	Collections.sort(money, comp);
    	Collections.reverse(money);
    
    	for(Valuable value : money){	
    		if(value.getCurrency().equalsIgnoreCase(amount.getCurrency())){	
    			if(amountNeededToWithdraw >= value.getValue()){
    				amountNeededToWithdraw -= value.getValue();
    				list.add(value);
    			}
    		}
    		
    		if(amountNeededToWithdraw == 0) break;
    	}
    	
    	if(amountNeededToWithdraw != 0) return null;
    	
    	return list;
	}

}
