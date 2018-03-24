package coinpurse;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import coinpurse.strategy.GreedyWithdraw;
import coinpurse.strategy.RecursiveWithdraw;
import coinpurse.strategy.WithdrawStrategy;

import java.util.Collections;
 
/**
 *  A valuable purse contains money.
 *  You can insert money, withdraw money, check the balance,
 *  and check if the purse is full.
 *  @author Thanaphon Keawjam
 */
public class Purse {
    /** Collection of objects in the purse. */
	private List<Valuable> money;
	
    /** Capacity is maximum number of items the purse can hold.
     *  Capacity is set when the purse is created and cannot be changed.
     */
    private final int capacity;
    
    private WithdrawStrategy strategy = null;
    
    private Comparator<Valuable> comp;
    
    private MoneyFactory factory = MoneyFactory.getInstance();
    
    /** 
     *  Create a purse with a specified capacity.
     *  @param capacity is maximum number of money you can put in purse.
     */
    public Purse( int capacity ) {
    	this.capacity = capacity;
    	money = new ArrayList<>();
    	comp = new ValueComparator();
    	strategy = new GreedyWithdraw();
    }
    
    public void setWithdrawStrategy(WithdrawStrategy strategy) {
    	this.strategy = strategy;
    }
    
    /**
     * Count and return the number of money in the purse.
     * This is the number of money, not their value.
     * @return the number of money in the purse
     */
    public int count() { return money.size(); }
    
    /** 
     *  Get the total value of all items in the purse.
     *  @return the total value of items in the purse.
     */
    public double getBalance() {
    	double value = 0.0;
    	for(int i = 0; i < money.size(); i++){
    		if(factory.getClass().getName().contains("Malay") && money.get(i).getValue() < 1)
    		value +=  (money.get(i).getValue() / 100);
    		
    		value +=  money.get(i).getValue();
    	}
		return value; 
	}
    
    /**
     * Return the capacity of the valuable purse.
     * @return the capacity
     */
    public int getCapacity() { 
		return capacity; 
	}
    
    /** 
     *  Test whether the purse is full.
     *  The purse is full if number of items in purse equals
     *  or greater than the purse capacity.
     *  @return true if purse is full.
     */
    public boolean isFull() {
    	if(money.size() == capacity){
    		return true;
    	}else{
    		return false;
    	}
    }

    /** 
     * Insert a money into the purse.
     * The money is only inserted if the purse has space for it
     * and the money has positive value.  No worthless money!
     * @param insertValue is a Valuable object to insert into purse
     * @return true if money inserted, false if can't insert
     */
    public boolean insert( Valuable insertValue ) {
    	boolean ok = isFull();
    	if(ok || insertValue.getValue() <= 0){
    		return false;
    	}else{
    		money.add(insertValue);
    		return true;
    	}
    }
    
    /**  
     *  Withdraw the requested amount of money.
     *  Return an array of Valuable withdrawn from purse,
     *  or return null if cannot withdraw the amount requested.
     *  @param amount is the amount to withdraw
     *  @return array of Valuable objects for money withdraw, 
	 *    or null if cannot withdraw requested amount.
     */
    public Valuable[] withdraw( double amount ) {
    	String currency = "";
    	MoneyFactory factory = MoneyFactory.getInstance();
    	if(factory.getClass().getName().contains("Thai")) currency = "Baht";
    	else if(factory.getClass().getName().contains("Malay")){
    		if(amount < 1) currency = "Sen";
    		else currency = "Ringgit";
    	}
    	return withdraw(new Money(amount, currency));
	}
    
    /**
     * Withdraw the requested amount of money.
     * @param amount is the amount to withdraw
     * @return array of Valuable objects for money withdraw, 
	 *    	   or null if cannot withdraw requested amount.
     */
    public Valuable[] withdraw(Valuable amount){
    	List<Valuable> list = new ArrayList<Valuable>();
    	
  //  	if(amount == null || amount.getValue() < 0) return null;
    	
    	double amountNeededToWithdraw = amount.getValue();
  
    //	Collections.sort(money, comp);
    //	Collections.reverse(money);
    	
    	if(amountNeededToWithdraw < 0 || this.getBalance() < amountNeededToWithdraw || count() == 0) return null;
    	
    	if(strategy.withdraw(amount, money) == null) return null;
    	list.addAll(strategy.withdraw(amount, money));
    	
    //	for(Valuable value : money){	
    //		if(value.getCurrency().equalsIgnoreCase(amount.getCurrency())){	
    //			if(amountNeededToWithdraw >= value.getValue()){
    //				amountNeededToWithdraw -= value.getValue();
    //				list.add(value);
    //			}
    //		}
    		
    //		if(amountNeededToWithdraw == 0) break;
    //	}
    	
    //	if(amountNeededToWithdraw != 0) return null;
    	
    	for(Valuable removeValue : list) money.remove(removeValue);
    	
    	Valuable[] withdraw = new Valuable[list.size()];
    	
    	return list.toArray(withdraw);
     }  
    
    /** 
     * toString returns a string description of the purse contents.
     * It can return whatever is a useful description.
     */
    public String toString() {
    	return String.format("Amount of money %d with value %.2f", money.size(),getBalance());
    }

}
