package coinpurse.strategy;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coinpurse.Money;
import coinpurse.Valuable;

/**
 * Test the WithdrawStrategy with GreedyWithdraw and RecursiveWithdraw class
 * using JUnit. This is a JUint 6 test suite.
 * @author Thanaphon Keawjam
 */
public class WithdrawTest {
	
	private static final double TOL = 1.0E-6;
	private WithdrawStrategy strategy;
	private List<Valuable> money = null;
	
	/**
	 * Code to run before each test. Setup the "test fixture".
	 */
	@Before
	public void setUp() {
	//	strategy = new GreedyWithdraw();
		strategy = new RecursiveWithdraw();
		money = new ArrayList<>();
	}

	@Test
	public void testSmallest() {
		money.add(new Money(1.0, "Baht"));
		assertEquals(1, strategy.withdraw(new Money(1.0, "Baht"), money).size(), TOL);
		
		money.clear();
		
		money.add(new Money(0.25, "Baht"));
		money.add(new Money(0.25, "Baht"));
		money.add(new Money(0.5, "Baht"));
		
		List<Valuable> list = new ArrayList<>();
		list.add(new Money(0.5, "Baht"));
		list.add(new Money(0.25, "Baht"));
		
		strategy.withdraw(new Money(0.25, "Baht"), money);
		
		for(int i = 0; i < list.size(); i++) {
			assertEquals(list.get(i).getValue(), money.get(i).getValue(), TOL);
		}
		
		money.clear();
		
	} 
	
	@Test
	public void testWithdrawEverything() {
		money.add(new Money(10.0, "Bahtt"));
		money.add(new Money(5.0, "Baht"));
		money.add(new Money(1.0, "Baht"));
		money.add(new Money(1.0, "Baht"));
		money.add(new Money(1.0, "Baht"));
		
		strategy.withdraw(new Money(3.0, "Baht"), money);
		
		List<Valuable> sort = new ArrayList<>();
		sort.add(new Money(10.0, "Baht"));
		sort.add(new Money(5.0, "Baht"));
		
		List<Valuable> notSort = new ArrayList<>();
		notSort.add(new Money(5.0, "Baht"));
		notSort.add(new Money(10.0, "Baht"));
		
		for(int i = 0; i < sort.size(); i++) {
			assertEquals(sort.get(i).getValue(), money.get(i).getValue(), TOL);
		}
		
		assertFalse(sort.get(0) == notSort.get(0));
		assertFalse(sort.get(1) == notSort.get(1));
		
		money.clear();
		
		money.add(new Money(5.0, "Baht"));
		money.add(new Money(4.0, "Baht"));
		money.add(new Money(3.0, "Baht"));
		assertEquals(2, strategy.withdraw(new Money(8.0, "Baht"), money).size(), TOL);
		
		money.clear();
		
		money.add(new Money(5.0, "Baht"));
		money.add(new Money(4.0, "Baht"));
		money.add(new Money(3.0, "Baht"));
		
		assertTrue(2 == strategy.withdraw(new Money(8.0, "Baht"), money).size());
		assertEquals(5.0, strategy.withdraw(new Money(8.0, "Baht"), money).get(0).getValue(), TOL);
		assertEquals(3.0, strategy.withdraw(new Money(8.0, "Baht"), money).get(1).getValue(), TOL);
		
		money.clear();
	} 
	
	@Test
	public void testWithdrawWithNegative() {
		money.add(new Money(1.0, "Baht"));
		money.add(new Money(1.0, "Baht"));
		money.add(new Money(100.0, "Baht"));
		
		assertNull(strategy.withdraw(new Money(-200, "Baht"), money));
		
		money.clear();
		
		money.add(new Money(1.0, "USD"));
		money.add(new Money(5.0, "USD"));
		money.add(new Money(10.0, "Baht"));
		
		assertNull(strategy.withdraw(new Money(-10, "USD"), money));
		
		money.clear();
	} 
	
	@Test
	public void testDiffCurrency() {
		money.add(new Money(100.0, "Baht"));
		assertNull(strategy.withdraw(new Money(100.0, "Yen"), money));
		
		money.add(new Money(50.0, "Baht"));
		money.add(new Money(100.0, "Yen"));
		assertEquals(1, strategy.withdraw(new Money(100.0, "Yen"), money).size(), TOL);
		
		money.clear();
		
		money.add(new Money(100.0, "USD"));
		money.add(new Money(500.0, "Baht"));
		
		assertTrue(1 == strategy.withdraw(new Money(100.0, "USD"), money).size());
		assertEquals(100.0, strategy.withdraw(new Money(100.0, "USD"), money).get(0).getValue(), TOL);
		assertEquals("USD", strategy.withdraw(new Money(100.0, "USD"), money).get(0).getCurrency());
		
		money.clear();
	} 
	
	@Test
	public void testWithdrawWithZero() {
		money.add(new Money(50.0, "Baht"));
		strategy.withdraw(new Money(0, "Bhat"), money);
		assertEquals(50.0, money.get(0).getValue(), TOL);
		
		money.add(new Money(50.0, "Baht"));
		money.add(new Money(10.0, "Baht"));
		money.add(new Money(5.0, "Baht"));
		money.add(new Money(10.0, "Baht"));

		assertTrue(strategy.withdraw(new Money(0, "Baht"), money).size() == 0);
		
		money.clear();
	} 
	
	@Test
	public void testWithdrawOver() {
		money.add(new Money(10.0, "Baht"));
		money.add(new Money(50.0, "Baht"));
		money.add(new Money(100.0, "Baht"));
		
		assertNull(strategy.withdraw(new Money(200.0, "Baht"), money));
		
		money.add(new Money(100.0, "USD"));
		money.add(new Money(500.0, "USD"));
		
		assertNull(strategy.withdraw(new Money(500.0, "Baht"), money));
		
		money.clear();
	} 

}
