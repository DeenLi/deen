import java.util.List;

public class SubwayLine {
    private String lineName;
    private List<Station> stations;
    

    public SubwayLine(String lineName, List<Station> stations){
        this.lineName = lineName;
        this.stations = stations;
    }

    public String getLineName(){
        return lineName;
    }

    public List<Station> geStations() {
        return stations;
    }


}
