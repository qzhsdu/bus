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
    double speed =10;
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
            Stop temp = DataBase.getStop(s);
            if(temp==null){
                System.out.println(this.name+"-------"+s);
            }
            stops.add(temp);
        }
    }
}
class Price{
    int from,to,price,linecode;

    public Price(int from, int to, int price, int linecode) {
        this.from = from;
        this.to = to;
        this.price = price;
        this.linecode = linecode;
    }
}
