import java.util.List;

public class Station {
    private String stationName;
    private List<SubwayLine> lines;

    public Station(String stationName, List<SubwayLine> lines){
        this.stationName = stationName;
        this.lines = lines;
    }

    public String getStationName() {
        return stationName;
    }

    public List<SubwayLine> getLines(){
        return lines;
    }


}
