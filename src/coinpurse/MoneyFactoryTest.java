package coinpurse;
import static org.junit.Assert.*;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import org.junit.Test;

/**
 * JUnit 4 test for MoneyFactory.
 * @author Thanaphon Keawjam
 */
public class MoneyFactoryTest {
	
	private static final double TOL = 1.0E-6;

	@Test(timeout=1000)
	public void testGetInstance(){
		MoneyFactory mf1 = MoneyFactory.getInstance();
		MoneyFactory mf2 = MoneyFactory.getInstance();
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory mf3 = MoneyFactory.getInstance();
		MoneyFactory mf4 = MoneyFactory.getInstance();
		assertEquals(true, mf1 == mf2);
		assertEquals(false, mf1 == mf3);
		assertEquals(false, mf2 == mf3);
		assertEquals(true, mf3 == mf4);
	}
	
	@Test(timeout=1000)
	public void testValueAndCurrency(){
		MoneyFactory facetory = MoneyFactory.getInstance();
		Valuable m1 = facetory.createMoney("100.00");
		Valuable m2 = facetory.createMoney(500.00);
		Valuable m3 = facetory.createMoney(20.0);
		assertEquals(100.00, m1.getValue(), TOL);
		assertEquals("Baht", m1.getCurrency());
		assertEquals(500.00, m2.getValue(), TOL);
		assertEquals("Baht", m2.getCurrency());
		assertEquals(20.0, m3.getValue(), TOL);
		assertEquals("Baht", m3.getCurrency());
		
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory f = MoneyFactory.getInstance();
		Valuable m4 = f.createMoney("0.05");
		Valuable m5 = f.createMoney(1.0);
		assertEquals(5, m4.getValue(), TOL);
		assertEquals("Sen", m4.getCurrency());
		assertEquals(1, m5.getValue(), TOL);
		assertEquals("Ringgit", m5.getCurrency());
	}
	
	
}
