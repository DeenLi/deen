package function3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

    public class SubwayMap {
    Map<SubwayStation, List<Connection>> graph = new HashMap<>();

    void addConnection(SubwayStation from, SubwayStation to, double distance) {
        graph.computeIfAbsent(from, k -> new ArrayList<>()).add(new Connection(from, to, distance));
        graph.computeIfAbsent(to, k -> new ArrayList<>()).add(new Connection(to, from, distance));
    }

    void loadFromFile(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine(); // Skip the header
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("---|\\t"); // Use "---" and "\t" as delimiters
            if (parts.length >= 3) { // Check if there are enough parts
                SubwayStation from = new SubwayStation(parts[0].trim());
                SubwayStation to = new SubwayStation(parts[1].trim());
                String distanceStr = parts[2].trim().replace("KM", ""); // Remove "KM" from the distance string
                double distance = Double.parseDouble(distanceStr); // Parse the distance
                addConnection(from, to, distance);
            }
        }
        br.close();
    }
    List<List<SubwayStation>> findAllPaths(SubwayStation start, SubwayStation end) {
        List<List<SubwayStation>> paths = new ArrayList<>();
        findPaths(start, end, new HashSet<>(), new ArrayList<>(), paths);
        return paths;
    }

    private void findPaths(SubwayStation current, SubwayStation end, Set<SubwayStation> visited,
                           List<SubwayStation> path, List<List<SubwayStation>> paths) {
        visited.add(current);
        path.add(current);

        if (current.equals(end)) {
            paths.add(new ArrayList<>(path));
        } else {
            for (Connection connection : graph.getOrDefault(current, Collections.emptyList())) {
                if (!visited.contains(connection.to)) {
                    findPaths(connection.to, end, visited, path, paths);
                }
            }
        }

        visited.remove(current);
        path.remove(path.size() - 1);
    }
}
