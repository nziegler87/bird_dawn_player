import java.util.List;

public class TipCalculator {
    private final double tip_percent;
    private final double bill_amount;

    public TipCalculator(double bill_amount, double tip_percent){
        this.bill_amount = bill_amount;
        this.tip_percent = tip_percent;
    }

    public void calculateTip(){
        double tip_amount = bill_amount * tip_percent;
        double total_bill = tip_amount + bill_amount;
        System.out.println("Bill before tip: " + bill_amount +
                            "\nTip Percent: " + tip_percent +
                            "\nTip Amount: " + tip_amount +
                            "\nTotal Bill: " + total_bill);
    }
}
