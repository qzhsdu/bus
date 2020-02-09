import java.util.ArrayList;
import java.util.List;

class Stop {
    String name;
    double x,y;
    int code;
    public Stop(String name, double x, double y,int code) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.code = code;
    }
}
class Line{
    String name;
    int code;
    int price;
    List<Stop> stops;

    public Line(String name, int code, int price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public void setStops(String stop) {
        if(stops==null){
            stops = new ArrayList<Stop>();
        }
        String[] ss = stop.split("\\+");
        for(String s : ss){
            stops.add(DataBase.getStop(s));
        }
    }
}
class Price{
    int from,to,price;
    public Price(int from, int to, int price) {
        this.from = from;
        this.to = to;
        this.price = price;
    }
}
