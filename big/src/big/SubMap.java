package big;

import java.util.*;

public class SubMap {
    private Map<String, Map<String, Double>> map;


    public SubMap() {
        this.map = new LinkedHashMap<>();
    }


    public void addLine(String lineName) {
        map.put(lineName, new LinkedHashMap<>());
    }

    public void addStation(String lineName, String stationName, double distance) {
        map.get(lineName).put(stationName, distance);
    }

    public Set<String> getTransferStations() {
        Map<String, Set<String>> stationLines = new HashMap<>();
        for (String line : map.keySet()) {
            for (String station : map.get(line).keySet()) {
                stationLines.putIfAbsent(station, new HashSet<>());
                stationLines.get(station).add(line);
            }
        }

        Set<String> transferStations = new HashSet<>();
        for (String station : stationLines.keySet()) {
            if (stationLines.get(station).size() > 1) {
                StringBuilder sb = new StringBuilder();
                sb.append("<").append(station).append(", <");
                for (String line : stationLines.get(station)) {
                    sb.append(line).append(" 号线、");
                }
                sb.setLength(sb.length() - 1); // Remove the last comma
                sb.append(">>");
                transferStations.add(sb.toString());
            }
        }

        return transferStations;
    }


    public String toString() {
        return this.map.values().toString();
    }

    public List<String> findStationsWithinDistance(String station, int distance) {
        List<String> results = new ArrayList<>();
       
        List<String> Line = new ArrayList<>();
        for (String i : map.keySet()) {
            Line.add(i);
        }
        int t = 0;
        for (Map<String, Double> M : map.values()) {
            

            String line = Line.get(t);
            ArrayList<String> stations = new ArrayList<String>();
            for (String i : M.keySet()) {
                stations.add(i);
            }


            if (stations.contains(station)) {


                int index = stations.indexOf(station);
               
                for (int i = Math.max(0, index - distance); i <= Math.min(stations.size() - 1, index + distance); i++) {
                   
                    int distanceFromStation = Math.abs(i - index);
                  
                    results.add("<<" + stations.get(i) + "," + line + "号线" + "," + distanceFromStation + ">>");
                }
            }
            t = t + 1;
        }
        return results;
    }


    public ArrayList<List<String>> findAllPaths(String startStation, String endStation) {
        ArrayList<List<String>> allPaths = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        List<String> currentPath = new ArrayList<>();
        currentPath.add(startStation);
        findAllPathsDFS(startStation, endStation, visited, currentPath, allPaths);
        return allPaths;
    }

    private void findAllPathsDFS(String currentStation, String endStation, Set<String> visited, List<String> currentPath, List<List<String>> allPaths) {
        visited.add(currentStation);
       
        if (currentStation.equals(endStation)) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
          
            List<String> connectedStations = getConnectedStations(currentStation);
            for (String nextStation : connectedStations) {
                if (!visited.contains(nextStation)) {
                    currentPath.add(nextStation);
                    findAllPathsDFS(nextStation, endStation, visited, currentPath, allPaths);
                    currentPath.remove(currentPath.size() - 1); 
                }
            }
        }
        visited.remove(currentStation);
    }

    private List<String> getConnectedStations(String station) {
        List<String> connectedStations = new ArrayList<>();

        for (Map<String, Double> M : map.values()) {


            ArrayList<String> stations = new ArrayList<String>();
            for (String i : M.keySet()) {
                stations.add(i);
            }


            if (stations.contains(station)) {
                int index = stations.indexOf(station);
                if (index > 0) {
                    connectedStations.add(stations.get(index - 1)); 
                }
                if (index < stations.size() - 1) {
                    connectedStations.add(stations.get(index + 1)); 
                }
            }
        }
        return connectedStations;
    }

    public void test3() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入起点站名称：");
        String startStation = scanner.nextLine();
        System.out.print("请输入终点站名称：");
        String endStation = scanner.nextLine();

        List<List<String>> allPaths = this.findAllPaths(startStation, endStation);
        if (allPaths.isEmpty()) {
            System.out.println("未找到起点站和终点站之间的路径。");
        } else {
            System.out.println("连接起点站和终点站的所有路径为：");
            for (List<String> path : allPaths) {
                System.out.println(path);
            }
        }

        ArrayList<Double> distances = new ArrayList<Double>();
        for (List<String> path : allPaths) {
            distances.add(this.getpathdistance((ArrayList<String>) path));
        }
        double shortestdistance = this.shortestdistance(distances);
        int indix = distances.indexOf(shortestdistance);
        ArrayList<String> shortestestpath = (ArrayList<String>) allPaths.get(indix);
        System.out.println("最短路径为：");
        System.out.println(shortestestpath);
        System.out.println("最短距离为");
        System.out.println(shortestdistance);
        System.out.print("请输入你选择的路线：");
        int linenumber=scanner.nextInt();
        this.showpath((ArrayList<String>) allPaths.get(linenumber));

        System.out.println("距离为"+distances.get(linenumber));
        System.out.println("请选择你的支付方式");
        String payway=scanner.next();

        if(payway.equals("单程票"))
        {System.out.println("您选择单程票支付");
            singlewayticket singlewayticket=new singlewayticket();
            System.out.println("价格为"+singlewayticket.getprice(distances.get(linenumber)));
        }
        if (payway.equals("武汉通"))
        {System.out.println("您选择武汉通支付");
            wuhantong wuhantong=new wuhantong();
            System.out.println("价格为"+wuhantong.getprice(distances.get(linenumber)));
        }
        if (payway.equals("定期票"))
        {System.out.println("您选择定期票支付");
            System.out.println("价格为"+0);
        }
        scanner.close();

    }

    public double Connnecteddistance(String s1, String s2) {
        Double distance = (double) 0;
        for (Map<String, Double> M : map.values()) {
            // 检查输入的站点是否在当前线路中

            ArrayList<Double> distances = new ArrayList<Double>();
            ArrayList<String> stations = new ArrayList<String>();
            for (String i : M.keySet()) {
                stations.add(i);
            }
            for (Double d : M.values()) {
                distances.add(d);
            }
            if ((stations.contains(s1)) && (stations.contains(s2))) {
                int index1 = stations.indexOf(s1);
                int index2 = stations.indexOf(s2);
                int q = index1 - index2;
                if (q == 1) {
                    distance = distances.get(index2);
                } else {
                    distance = distances.get(index1);


                }


            }
        }
        return distance;
    }

    public Double getpathdistance(ArrayList<String> list) {
        Double d = (double) 0;
        for (int i = 0; i < list.toArray().length - 1; i++) {
            d = d + this.Connnecteddistance(list.get(i), list.get(i + 1));


        }
        return d;
    }

    public Double shortestdistance(ArrayList<Double> list) {
        double d = list.get(0);
        for (int i = 0; i <= list.toArray().length - 1; i++) {
            double temp = list.get(i);
            if (d > temp) {
                d = temp;
            }
        }
        return d;
    }

    public String getline(String s1, String s2) {
        String nowline=null;
        ArrayList<String> lines=new ArrayList<String>();
        for(String i: map.keySet())

        {lines.add(i);}
        int i=0;

        for (Map<String, Double> M : map.values()) {


               String line= lines.get(i);
            ArrayList<String> stations = new ArrayList<String>();
            for (String s : M.keySet()) {
                stations.add(s);
            }

            if ((stations.contains(s1)) && (stations.contains(s2))) {
                  nowline=line;
            }

            i++;
        }
return nowline;
}

    
    public void  showpath(ArrayList<String> path)
    {String firststation=path.get(0);
        String laststation=path.get(path.toArray().length-1);
        String firstline=this.getline(path.get(0), path.get(1));
        System.out.println("从"+firststation+"出发"+"延"+firstline+"号线");
        for (int i=1;i<path.toArray().length-1;i++)
        {String previouspath=this.getline(path.get(i-1),path.get(i));
            String nowpath=this.getline(path.get(i),path.get(i+1));
            if(!previouspath.equals(nowpath))
            {System.out.println("到"+path.get(i));
        System.out.println("转乘"+nowpath+"号线");
        }
        }
System.out.println("到"+laststation);}

}
