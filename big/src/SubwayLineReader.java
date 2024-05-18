import java.io.BufferedReader;  
import java.io.FileReader;  
import java.io.IOException;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
public class SubwayLineReader {  
  
    public static void main(String[] args) {  
        String filePath = "subway.txt"; // 替换为你的txt文件路径  
        try {  
            Map<String, Double> stationDistances = readStationDistances(filePath);  
            printStationDistances(stationDistances);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static Map<String, Double> readStationDistances(String filePath) throws IOException {  
        Map<String, Double> stationDistances = new HashMap<>();  
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {  
            String line;  
            while ((line = reader.readLine()) != null) {  
                // 忽略标题行  
                if (line.startsWith("站点名称")) {  
                    continue;  
                }  
                  
                // 分割每行的内容  
                String[] parts = line.split("\t");  
                if (parts.length >= 2) {  
                    String stationPair = parts[0];  
                    double distance = Double.parseDouble(parts[1]);  
                    stationDistances.put(stationPair, distance);  
                }  
            }  
        }  
        return stationDistances;  
    }  
  
    public static void printStationDistances(Map<String, Double> stationDistances) {  
        for (Map.Entry<String, Double> entry : stationDistances.entrySet()) {  
            String stations = entry.getKey();  
            double distance = entry.getValue();  
            System.out.println("站点: " + stations + " 间距: " + distance + " KM");  
        }  
    }  
}

