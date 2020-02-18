import java.net.SocketOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.StrictMath.*;

public class FastMatrix {
    static List<Line> all;
    static Map<String,Fast> map;
    public FastMatrix() {
        this.all = DataBase.getLines();
        map = new HashMap<String,Fast>();
        for(Line l : all){
            getSingleLine(l);
        }
    }

    static double getDistant(Stop a, Stop b){
         double x = a.x-b.x;
         double y = a.y-b.y;
         return sqrt(x*x+y*y)*10000;
    }
    static void update(Stop a, Stop b, int code,double t){
        String key;
        Fast now = new Fast(a.code,b.code,code,t);
        if(a.code<=b.code){
            key = a.code+"-"+b.code;
        }
        else {
            key = b.code+"-"+a.code;
        }
        if(map.containsKey(key)){
            Fast temp = map.get(key);
            if(temp.time>t){
                map.put(key,now);
            }
        }else {
            map.put(key,now);
        }
    }

    static void getSingleLine(Line line){
        double[] sum = new double[line.stops.size()+10];
        int n = line.stops.size();
        for(int i=0;i<n-1;i++){
            sum[i+1] = sum [i]+ getDistant(line.stops.get(i),
                    line.stops.get(i+1));
        }
        for(int i = 0;i<n;i++){
            for(int j = i;j<n;j++){
                double t = sum[j]-sum[i];
                t = t/line.speed;
                update(line.stops.get(i),line.stops.get(j),line.code,t);
            }
        }
    }
}
