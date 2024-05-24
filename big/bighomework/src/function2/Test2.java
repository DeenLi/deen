package function2;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        SubwayMap map = new SubwayMap();
        try {
            map.loadFromFile("C:\\Users\\dell\\Desktop\\subway.txt");
            try (Scanner scanner = new Scanner(System.in)) {
				System.out.println("Enter start station:");
				String station = scanner.nextLine();
				System.out.println("Enter maxDistance:");
				double distance = scanner.nextDouble();
				List<SubwayStation> result = map.getStationsWithinDistance(station, distance);
				System.out.println(station + "，距离为" + distance + "的站点为" + result);
			}
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}