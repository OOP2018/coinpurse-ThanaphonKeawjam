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
	
	private String currency;
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
		currency = "Baht";
		money = new ArrayList<>();
	}

	@Test
	public void testWithdrawWithZero() {
		money.add(new Money(10.0, currency));
		money.add(new Money(5.0, currency));
		money.add(new Money(1.0, currency));
		money.add(new Money(1.0, currency));
		
		List<Valuable> withdraw = strategy.withdraw(new Money(0.0, currency), money);
		assertEquals(0, withdraw.size(), TOL);
		assertTrue(null != withdraw);
		
		money.clear();
	}
	
	@Test
	public void testDiffCurrency() {
		money.add(new Money(50.0, currency));
		money.add(new Money(20.0, currency));
		money.add(new Money(50.0, "USD"));
		money.add(new Money(100.0, "USD"));
		
		assertNull(strategy.withdraw(new Money(20.0, "Rupees"), money));
		assertNull(strategy.withdraw(new Money(100.0, "Yen"), money));
		assertNull(strategy.withdraw(new Money(50.0, "Yen"), money));
		assertNotNull(strategy.withdraw(new Money(20.0, currency), money));
		
		money.clear();
	}
	
	@Test
	public void testWithdrawOver() {
		money.add(new Money(1000.0, currency));
		money.add(new Money(1000.0, currency));
		money.add(new Money(500.0, currency));
		
		assertNull(strategy.withdraw(new Money(3000.0, currency), money));
		assertNull(strategy.withdraw(new Money(5000.0, currency), money));
		assertNotNull(strategy.withdraw(new Money(2500.0, currency), money));
		
		money.clear();
	}
	
	@Test
	public void testMultiWithdraw() {
		money.add(new Money(50.0, currency));
		money.add(new Money(100.0, currency));
		money.add(new Money(500.0, currency));
		money.add(new Money(1000.0, currency));
		
		List<Valuable> value1 = strategy.withdraw(new Money(100.0, currency), money);
		assertTrue(1 == value1.size());
		assertEquals(100.0, value1.get(0).getValue(), TOL);
		
		List<Valuable> value2 = strategy.withdraw(new Money(1000.0, currency), money);
		assertEquals(1100.0, value1.get(0).getValue() + value2.get(0).getValue(), TOL);
		
		money.clear();
		
	}
	
	@Test
	public void testEasyWithdraw() {
		money.add(new Money(5.0, currency));
		money.add(new Money(20.0, currency));
		
		List<Valuable> withdraw = strategy.withdraw(new Money(5.0, currency), money);
		assertTrue(1 == withdraw.size());
		assertEquals(5.0, withdraw.get(0).getValue(), TOL);
		
		money.clear();
	}
	
	@Test
	public void testWithdrawNotChangePurse() {
		money.add(new Money(500.0, currency));
		money.add(new Money(100.0, currency));
		money.add(new Money(1000.0, currency));
		
		strategy.withdraw(new Money(500.0, currency), money);
		strategy.withdraw(new Money(100.0, currency), money);
		strategy.withdraw(new Money(1000.0, currency), money);
		
		assertTrue(money.size() != 0);
		assertTrue(money.size() == 3);
		
		money.clear();
	}

}
