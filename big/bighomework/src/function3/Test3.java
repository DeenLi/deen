package function3;


import java.io.*;
import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        SubwayMap subwayGraph = new SubwayMap();
        Scanner scanner = null;


        try {
            subwayGraph.loadFromFile("C:\\Users\\dell\\Desktop\\subway.txt");

            scanner = new Scanner(System.in);
            System.out.println("Enter start station:");
            String startStationName = scanner.nextLine();
            System.out.println("Enter end station:");
            String endStationName = scanner.nextLine();

            SubwayStation start = new SubwayStation(startStationName);
            SubwayStation end = new SubwayStation(endStationName);

            // 使用文件输出流创建PrintStream对象

            // 将标准输出重定向到文件

            List<List<SubwayStation>> paths = subwayGraph.findAllPaths(start, end);
            if (paths.isEmpty()) {
                System.out.println("No paths found between the given stations.");
            } else {
                System.out.println("show the paths:\n");
                for (int i = 0; i < paths.size(); i++) {
                    System.out.print("Path " + (i + 1) + ":\n");
                    boolean isFirst = true;
                    for (SubwayStation station : paths.get(i)) {
                        if (!isFirst) {
                            System.out.print(" -> ");
                        }
                        System.out.print(station.getName());
                        isFirst = false;
                    }
                    System.out.println('\n'); // 打印换行符，表示一条路径结束
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading subway graph: " + e.getMessage());
        } finally {
            scanner.close(); // 确保关闭Scanner，释放资源

                }
                }
            }