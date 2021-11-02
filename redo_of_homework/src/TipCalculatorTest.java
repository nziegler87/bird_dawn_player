import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TipCalculatorTest {
    TipCalculator twenty_bill;

    @Before
    public void setUp() throws Exception {
        this.twenty_bill = new TipCalculator(20.00, .20);
    }

    @Test
    public void 
    @Test
    public void calculateTip() {
        assertEquals(this.twenty_bill.calculateTip(), 24, .01);
    }
}