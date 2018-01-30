package coinpurse;

import java.util.Comparator;

/**
 * A class use for compare two object that implement Valuable.
 * @author Thanaphon Keawjam
 */
public class ValueComparator implements Comparator<Valuable>{
	
	/**
	 * Compare two object that implement Valuable.
	 * First compare them by currency, so that "Baht" < "Dollar".
	 * If both objects have the same currency, order them by value.
	 * @param a is object that want to compare.
	 * @param b is object that want to compare.
	 * @return value 1, -1, or 0 after compare.
	 */
	public int compare(Valuable a, Valuable b){
		if(a.getCurrency().equals(b.getCurrency())){
			if(a.getValue() > b.getValue()) return 1;
			else if(a.getValue() < b.getValue()) return -1;
			return 0;
		}else{
			if(a.getCurrency().charAt(0) > b.getCurrency().charAt(0)) return 1;
			else return -1;
		}
	}

}
