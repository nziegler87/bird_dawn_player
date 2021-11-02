public class TipCalculator {
    private final double amount;
    private final double tip;

    public TipCalculator(double amount, double tip_percentage) throws IllegalArgumentException{
        if ( tip_percentage > 1) throw new IllegalArgumentException("Tip percentage cannot be greater than 1!");

        this.amount = amount;
        this.tip = tip_percentage;
    }

    public double calculateTip(){
        double tip_amount = this.amount * this.tip;
        double total_bill = this.amount + tip_amount;
        return total_bill;

    }


}
