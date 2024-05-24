package function2;

public class SubwayStation {
    private String name;
    private String line;
    private double distance;

    public SubwayStation(String name, String line, double distance) {
        this.name = name;
        this.line = line;
        this.distance = distance;
    }
    public String getName() {
        return name;
    }
    public double getDistance() {
        return distance;
    }
    public void setDistance (double d){
        this.distance = d;
    }
    @Override
    public String toString() {
        return String.format("<%s，%s，%.3f>", name, line, distance);
    }
}

