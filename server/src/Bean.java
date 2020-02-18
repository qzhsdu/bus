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
