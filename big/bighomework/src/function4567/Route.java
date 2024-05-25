package function4567;

import java.util.ArrayList;

public class Route {

    private String name;
    private ArrayList<Interval> intevals = new ArrayList<Interval>() ;

    public ArrayList<Interval> getIntevals() {
        return intevals;
    }

    public void setIntevals(ArrayList<Interval> intevals) {
        this.intevals = intevals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
