package patterns.models;

/**
 * Created with IntelliJ IDEA.
 * User: Valiantsin_Vlasau
 * Date: 10/26/12
 * Time: 6:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class Battery {
    private int charge;

    public Battery() {
        charge = 0;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        if(charge < 0)
            this.charge = 0;
        else if (charge > 100)
            this.charge = 100;
        else
            this.charge = charge;
    }
}
