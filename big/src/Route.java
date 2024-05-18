import java.util.List;

public class Route {
    private List<Station> stations;
    private int transfers;
    private double totalDistance;

    public Route(List<Station> stations, int transfers, double totalDistance) {
        this.stations = stations;  
        this.transfers = transfers;  
        this.totalDistance = totalDistance;  
    }
    
    public List<Station> getStations() {  
        return stations;  
    }  
  
    public int getTransfers() {  
        return transfers;  
    }  
  
    public double getTotalDistance() {  
        return totalDistance;  
    }  
}
