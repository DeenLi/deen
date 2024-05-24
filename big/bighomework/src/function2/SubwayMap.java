package function2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SubwayMap {
    private Map<String, List<SubwayStation>> stationMap = new HashMap<>();

    public void loadFromFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        String currentLine = null;
        while ((line = reader.readLine()) != null) {
            if (line.contains("号线站点间距")) {
                currentLine = line.split("号")[0];
            } else if (line.contains("---")) {
                String[] parts = line.split("---|\\s+");
                String station1 = parts[0];
                String station2 = parts[1];
                double distance = Double.parseDouble(parts[2]);

                stationMap.putIfAbsent(station1, new ArrayList<>());
                stationMap.putIfAbsent(station2, new ArrayList<>());

                stationMap.get(station1).add(new SubwayStation(station2, currentLine, distance));
                stationMap.get(station2).add(new SubwayStation(station1, currentLine, distance));
            }
        }
        reader.close();
    }
    public List<SubwayStation> getStationsWithinDistance(String station, double maxDistance) {
        if (!stationMap.containsKey(station)) {
            throw new IllegalArgumentException("Invalid station: " + station);
        }

        List<SubwayStation> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfs(station, 0.0, maxDistance, stationMap, visited, result);

        return result;
    }

    private void dfs(String currentStation, double currentDistance, double maxDistance,
                     Map<String, List<SubwayStation>> stationMap, Set<String> visited,
                     List<SubwayStation> result) {

        visited.add(currentStation);

        // 遍历当前站点的所有相邻站点
        for (SubwayStation neighbor : stationMap.get(currentStation)) {
            if (!visited.contains(neighbor.getName())) {
                double neighborDistance = currentDistance + neighbor.getDistance();
                if (neighborDistance <= maxDistance) {
                    neighbor.setDistance(neighborDistance);
                    result.add(neighbor);
                    dfs(neighbor.getName(), neighborDistance, maxDistance, stationMap, visited, result);
                }
            }
        }
    }
}
