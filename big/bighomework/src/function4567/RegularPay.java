package function4567;

public class RegularPay extends Pay{

    private int type;

    public RegularPay(int type) {
        this.type = type;
    }

    /**
     * count the fee of regular payment
     */
    protected double count() {
        if(this.type == 1) {
            this.fee = 18;
        }
        else if(this.type == 3) {
            this.fee = 45;
        }
        else if(this.type == 7) {
            this.fee = 90;
        }
        return this.fee;
    }

}