import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

class Stop {
    @JSONField(name = "stop_name")
    String name;

    @JSONField(name = "stop_code")
    int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    double x,y;
    public Stop(String name, double x, double y,int code) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.code = code;
    }
}
class Line{
    @JSONField(name = "name")
    String name;
    @JSONField(name = "code")
    int code;
    @JSONField(name = "price")
    int price;
    @JSONField(name = "stops")
    List<Stop> stops;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @JSONField(name ="speed")
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
class Fast{
    int from,to;int linecode;
    double time;

    public Fast(int from, int to, int linecode, double time) {
        this.from = from;
        this.to = to;
        this.linecode = linecode;
        this.time = time;
    }
}
class A{
    @JSONField(name = "price")
    double price;
    @JSONField(name = "time")
    double time;
    @JSONField(name = "lines")
    List<Line> lines;
    @JSONField(name = "stops")
    List<String> stops;

    public A(double price, double time) {
        this.price = price;
        this.time = time;
        lines = new ArrayList<>();
        stops = new ArrayList<>();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<String> getStops() {
        return stops;
    }

    public void setStops(List<String> stops) {
        this.stops = stops;
    }
}
