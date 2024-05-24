package function3;

class Connection {
    SubwayStation from;
    SubwayStation to;
    double distance;

    Connection(SubwayStation from, SubwayStation to, double distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
}
