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
	private MoneyFactory factory = MoneyFactory.getInstance();

	@Test(timeout=1000)
	public void testGetInstance(){
		MoneyFactory tmf = ThaiMoneyFactory.getInstance();
		MoneyFactory mmf = MalayMoneyFactory.getInstance();
		MoneyFactory f1 = factory;
		MoneyFactory f2 = factory;
		assertEquals(true, f1 == f2);
		assertEquals(true, f1 == tmf);
		assertEquals(true, f1 == mmf);
		assertEquals(true, tmf == mmf);
	}
	
	@Test(timeout=1000)
	public void testValueAndCurrency(){
		Valuable m1 = factory.creatMoney("100.00");
		Valuable m2 = factory.creatMoney(500.00);
		Valuable m3 = factory.creatMoney(20.0);
		assertEquals(100.00, m1.getValue(), TOL);
		assertEquals("Baht", m1.getCurrency());
		assertEquals(500.00, m2.getValue(), TOL);
		assertEquals("Baht", m2.getCurrency());
		assertEquals(20.0, m3.getValue(), TOL);
		assertEquals("Baht", m3.getCurrency());
		
		MoneyFactory.setFactory(new MalayMoneyFactory());
		MoneyFactory f = MoneyFactory.getInstance();
		Valuable m4 = f.creatMoney("0.05");
		Valuable m5 = f.creatMoney(1.0);
		Valuable m6 = f.creatMoney(1000.0);
		assertEquals(5, m4.getValue(), TOL);
		assertEquals("Sen", m4.getCurrency());
		assertEquals(1, m5.getValue(), TOL);
		assertEquals("Ringgit", m5.getCurrency());
		assertNull(m6);
	}
	
	
}
