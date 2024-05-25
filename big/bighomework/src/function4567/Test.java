package function4567;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;


public class Test extends JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Route> routes = new ArrayList <Route> ();

    /**
     * create GUI
     */
    public Test() {
        setTitle("武汉地铁模拟系统-关于最短路径的各种问题");
        Container c = getContentPane();
        c.setLayout(new GridLayout(7, 1));

        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();

        JLabel tl3 = new JLabel("输入起点站和终点站，返回最短路径");
        JButton tr3 = new JButton("确定");
        JButton t4 = new JButton("路径以简洁的形式打印至标准输出");
        JButton t5 = new JButton("计算该最短路径对应的乘车费用(普通单程票)");
        JButton t6 = new JButton("计算使用武汉通的乘客的票价");
        JButton t7 = new JButton("计算使用日票的乘客的票价");
        JTextField t31 = new JTextField(10);
        JTextField t32 = new JTextField(10);
        Font f = new Font("微软雅黑", Font.PLAIN, 12);//set font

        tl3.setFont(f);
        tr3.setFont(f);
        t4.setFont(f);
        t5.setFont(f);
        t6.setFont(f);
        t7.setFont(f);
        //add interactivity event

        tr3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog d3 = new JDialog();
                d3.setTitle("最短路径");
                JTextArea a3 = new JTextArea(4, 10);
                String content = "";
                ArrayList<String> best = getBestPath(t31.getText(), t32.getText());
                int i = 0;
                for(i = 0; i<best.size()-1; i++) {
                    content = content + " " + best.get(i) + " " + "->";
                }
                content = content + best.get(i);
                a3.setText(content);
                a3.setFont(f);
                a3.setLineWrap(true);
                a3.setBackground(c.getBackground());
                a3.setBorder(new EmptyBorder(10, 10, 10, 10));
                a3.setEditable(false);
                d3.add(a3);
                d3.setSize(600, 300);
                d3.setVisible(true);
                d3.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                d3.setLocationRelativeTo(null);
            }
        });
        t4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog d4 = new JDialog();
                d4.setTitle("打印最短路径至标准输出");
                JTextArea a4 = new JTextArea(4,10);
                a4.setText(printBestPath(t31.getText(), t32.getText()));
                a4.setFont(f);
                a4.setLineWrap(true);
                a4.setBackground(c.getBackground());
                a4.setBorder(new EmptyBorder(10, 10, 10, 10));
                a4.setEditable(false);
                d4.add(a4);
                d4.setSize(400, 200);
                d4.setVisible(true);
                d4.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                d4.setLocationRelativeTo(null);
            }
        });
        t5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog d5 = new JDialog();
                d5.setTitle("路径对应的乘车费用(普通单程票)");
                JTextArea a5 = new JTextArea(2, 5);
                a5.setText(Double.valueOf(countPath(getBestPath(t31.getText(), t32.getText()), "UsualPay")).toString());
                a5.setFont(f);
                a5.setLineWrap(true);
                a5.setBackground(c.getBackground());
                a5.setBorder(new EmptyBorder(10, 10, 10, 10));
                a5.setEditable(false);
                d5.add(a5);
                d5.setSize(400, 200);
                d5.setVisible(true);
                d5.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                d5.setLocationRelativeTo(null);
            }
        });
        t6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog d6 = new JDialog();
                d6.setTitle("使用武汉通的乘客的票价");
                JTextArea a6 = new JTextArea(2, 5);
                a6.setText(Double.valueOf(countPath(getBestPath(t31.getText(),t32.getText()), "CardPay")).toString());
                a6.setFont(f);
                a6.setLineWrap(true);
                a6.setBackground(c.getBackground());
                a6.setBorder(new EmptyBorder(10, 10, 10, 10));
                a6.setEditable(false);
                d6.add(a6);
                d6.setSize(400, 200);
                d6.setVisible(true);
                d6.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                d6.setLocationRelativeTo(null);
            }
        });
        t7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JDialog d7 = new JDialog();
                d7.setTitle("使用日票的乘客的票价");
                JTextArea a7 = new JTextArea(2, 5);
                a7.setText(Double.valueOf(countPath(getBestPath(t31.getText(), t32.getText()), "RegularPay")).toString());
                a7.setFont(f);
                a7.setLineWrap(true);
                a7.setBackground(c.getBackground());
                a7.setBorder(new EmptyBorder(10, 10, 10, 10));
                a7.setEditable(false);
                d7.add(a7);
                d7.setSize(400, 200);
                d7.setVisible(true);
                d7.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                d7.setLocationRelativeTo(null);
            }
        });

        p3.add(tl3);
        p3.add(t31);
        p3.add(t32);
        p3.add(tr3);
        p4.add(t4);
        p5.add(t5);
        p6.add(t6);
        p7.add(t7);

        c.add(p3);
        c.add(p4);
        c.add(p5);
        c.add(p6);
        c.add(p7);
        setFont(f);
        setSize(800, 400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * read the subway.txt and get routes
     */
    private void readFile() {
        try {
            Scanner s = new Scanner(new File("C:\\Users\\dell\\Desktop\\subway.txt"));
            int rankLine = 0;
            while (s.hasNext()) {
                String line =s.nextLine();
                if(line.contains("线")) {
                    Route r  = new Route();
                    r.setName(line.substring(0,(line.indexOf("线")) + 1));
                    this.routes.add(r);
                    rankLine++;
                }
                if(line.contains("---")){
                    setIntervals(this.routes.get(rankLine-1), line);
                }
            }
            s.close();
        }catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    /**
     * set an interval from the read line
     * @param r the route
     * @param l the read string
     * @throws IOException
     */
    private void setIntervals(Route r, String l) throws IOException  {
        String[] s1 = l.split("---");
        String[] s2 = s1[1].split("\t");
        Interval in = new Interval(s1[0], s2[0], Double.valueOf(s2[1]));
        r.getIntevals().add(in);
    }

    /**
     * get all the routes that through the stop
     * @param stop
     * @return
     */
    private ArrayList<String> getAllRoutes(String stop) {
        ArrayList <String> all = new ArrayList<>();
        for(Route x: this.routes) {
            for(Interval i: x.getIntevals()) {
                if((i.getStops()[0].equals(stop))||(i.getStops()[1].equals(stop))) {
                    all.add(x.getName());
                    break;
                }
            }
        }
        return all;
    }

    /**
     * get the array of all the stops
     */
    private ArrayList<String> getAllStops() {
        ArrayList< String> all = new ArrayList<>();
        for(Route r: this.routes) {
            for(Interval i: r.getIntevals()) {
                for(String s: i.getStops()) {
                    boolean b = true;
                    for(int j = 0; j<all.size(); j++) {
                        if(all.get(j).equals(s)) {
                            b = false;
                        }
                    }
                    if(b == true) {
                        all.add(s);
                    }
                }
            }
        }
        return all;
    }

    /**
     * initial map before Dijkstra
     * @param map the distance matrix of stops
     * @param all array of all the stops
     * @param max as infinity
     */
    private void initialDistance(double[][] map, ArrayList<String> all, double max) {
        for(Route r : this.routes) {
            for(Interval interval : r.getIntevals()) {
                for(int i = 0; i<all.size(); i++) {
                    if(interval.getStops()[0].equals(all.get(i))) {
                        for(int j = 0; j<all.size(); j++) {
                            if(all.get(j).equals(interval.getStops()[1])) {
                                map[i][j] = interval.getDistance();
                                map[j][i] = interval.getDistance();
                            }
                        }
                        break;
                    }
                }
            }
        }
        for(int i = 0; i<map.length; i++) {
            for(int j = 0; j<map.length; j++) {
                if(map[i][j]==0.0 && i!=j) { //if can't reach
                    map[i][j] = max;
                }
            }
        }
    }

    /**
     * show the best path from the start stop to the end stop
     * @param start the start stop
     * @param end the end stop
     */
    private ArrayList<String> getBestPath(String start, String end) {
        ArrayList<String> best = new ArrayList<>();
        ArrayList<String> all = getAllStops();
        double max = 1000;
        double[][] map = new double[all.size()][all.size()];
        initialDistance(map, all, max);
        boolean[] isVisited = new boolean[map.length];//mark whether the stop is considered
        double[] minDistance = new double[map.length];//store the minimal distances from start to other stops
        for(int i = 0; i<map.length; i++) {  //initialize data
            isVisited[i] = false;
            minDistance[i] = max;
        }
        int startIndex = 0;
        int endIndex = 0;
        //mark the start stop and the end stop
        for(int i=0;i<all.size();i++) {
            if(all.get(i).equals(start)) {
                minDistance[i]=0;
                isVisited[i]=true;
                startIndex = i;
            }
            if(all.get(i).equals(end)) {
                endIndex = i;
            }
        }
        int unVisitedNum = map.length;  //record the number of unvisited stops
        int indexNow = startIndex;  //store the index of the stop of minimal distance
        int[] pre = new int[map.length];
        while(unVisitedNum > 0 && indexNow!=endIndex) {
            double min = max;
            //find the minimal in the group of unvisited stops
            for(int i = 0; i<map.length; i++) {
                if(!isVisited[i]) {
                    if(minDistance[i]<min) {
                        min = minDistance[i];
                        indexNow = i;
                    }
                }
            }
            isVisited[indexNow] = true;
            unVisitedNum--;
            //update the distance of the start stop to other stops
            for(int j=0; j<map.length; j++) {
                if(!isVisited[j]) {
                    if(minDistance[indexNow] + map[indexNow][j] < minDistance[j]) {
                        minDistance[j] = minDistance[indexNow] + map[indexNow][j];
                        pre[j] = indexNow;
                    }
                }
            }
        }
        returnBestRoute(startIndex, endIndex, all, pre, best);
        return best;
    }

    /**
     * get the route from the start to the end by recursion
     * @param startIndex index of the start stop
     * @param endIndex index of the end stop
     * @param all array of all stops
     * @param pre an array that records the stop before current stop
     * @param best the array of the best route from start to the end
     */
    private void returnBestRoute(int startIndex, int endIndex, ArrayList<String> all, int[] pre, ArrayList<String>best) {
        if(endIndex==startIndex) {
            best.add(all.get(endIndex));
            return;
        }
        returnBestRoute(startIndex, pre[endIndex], all, pre, best);
        best.add(all.get(endIndex));
    }

    /**
     * judge whether the two stops are in the same route
     * @param s1 stop1
     * @param s2 stop2
     * @return
     */
    private boolean inSameRoute(String s1 , String s2) {
        for(Route r : this.routes) {
            boolean b1 = false;
            boolean b2 = false;
            for(Interval i : r.getIntevals()) {
                for(String s : i.getStops()) {
                    if(s1.equals(s)) {
                        b1 = true;
                    }
                    else if(s2.equals(s)) {
                        b2 = true;
                    }
                }
            }
            if(b1 && b2) {
                return true;
            }
        }
        return false;
    }

    /**
     * get the route by two stops
     * @param s1 stop1
     * @param s2 stop2
     * @return
     */
    private String getRouteByTwoStops(String s1, String s2) {
        for(String r1 : getAllRoutes(s1)) {
            for(String r2 : getAllRoutes(s2)) {
                if(r1.equals(r2)) {
                    return r2;
                }
            }
        }
        return null;
    }

    /**
     * print the best route standardly
     * @param start the start stop
     * @param end the end stop
     */
    private String printBestPath(String start, String end) {
        ArrayList<String> path = getBestPath(start, end);
        String result = "乘" + getRouteByTwoStops(path.get(0), path.get(1)) + "[" + start + " , ";
        for(int i = 2; i<path.size(); i++) {
            if((!inSameRoute(path.get(i), path.get(i-2)))||(inSameRoute(path.get(i), path.get(i-2))&&!getRouteByTwoStops(path.get(i), path.get(i-2)).equals(path.get(i-2)))) {
                if(!getRouteByTwoStops(path.get(i), path.get(i-1)).equals(getRouteByTwoStops(path.get(i-1), path.get(i-2)))) {
                    result = result + path.get(i-1)+"] , 换乘" + getRouteByTwoStops(path.get(i-1), path.get(i)) + "[" + path.get(i-1) + " , ";
                }
            }
        }
        result = result + end + "]";
        return result;
    }

    /**
     * get the distance of two stops
     * @param s1 stop1
     * @param s2 stop2
     * @return
     */
    private double getDistance(String s1, String s2) {
        for(Route r : this.routes) {
            for(Interval i : r.getIntevals()) {
                if((i.getStops()[0].equals(s1) && i.getStops()[1].equals(s2)) || (i.getStops()[0].equals(s2) && i.getStops()[1].equals(s1))){
                    return i.getDistance();
                }
            }
        }
        return 0;
    }

    /**
     * get the distance of a path
     * @param path an array of all the stops of the path
     * @return
     */
    private double getDistance(ArrayList<String> path) {
        double dsum = 0;
        for(int i = 1; i<path.size(); i++) {
            dsum = dsum + getDistance(path.get(i), path.get(i-1));
        }
        return dsum;
    }

    /**
     * calculate the fee of the path
     * @param path an array of the stops of the path
     * @param type the type of payment
     * @return
     */
    private double countPath(ArrayList<String> path, String type) {
        Pay p = null;
        if(type.equals("RegularPay")) {
            return 0;
        }
        else if(type.equals("UsualPay")) {
            p = new UsualPay(getDistance(path));
        }
        else if(type.equals("CardPay")) {
            p = new CardPay(getDistance(path));
        }
        return p.count();
    }

    /**
     * test functions
     * @param args
     */
    public static void main(String[] args) {
        Test t = new Test();
        t.readFile();
    }

}