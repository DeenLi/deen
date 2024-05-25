package function4567;

public class UsualPay extends Pay{

    public UsualPay(double d) {
        this.distance = d;
    }

    /**
     * count the fee of usual payment
     */
    protected double count() {
        if(this.distance >= 0 && this.distance <= 9) {
            this.fee = 2;
        }
        else if(this.distance <= 14) {
            this.fee = 3;
        }
        else if(this.distance <= 21){
            this.fee = 5;
        }
        else if(this.distance <= 30) {
            this.fee = 5;
        }
        else if(this.distance <= 41) {
            this.fee = 6;
        }
        return this.fee;
    }

}