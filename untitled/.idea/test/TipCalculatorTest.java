import static org.junit.Assert.*;

public class TipCalculatorTest {
    private TipCalculator testCalculator;

    @org.junit.Before
    public void setUp() throws Exception {
        testCalculator = new TipCalculator(10,.20);
    }

    @org.junit.Test
    public void calculateTip() {
        testCalculator.calculateTip();
    }
}