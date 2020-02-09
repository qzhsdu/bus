import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceMatrix {
    List<Line> all ;
    Map<String,Integer> price;
    public PriceMatrix(List<Line> all) {
        this.all = DataBase.getLines();
        price = new HashMap<String,Integer>();
    }
    void work(){
        for(Line line : all){
            for(Stop stop : line.stops){
                for(Stop to : line.stops){
                    if(stop.code>to.code){
                        continue;
                    }
                    String key= stop.code+"-"+to.code;
                    int pre=2;
                    if(price.containsKey(key)){
                        pre = price.get(key);
                    }
                    price.put(key,pre<line.price?pre:line.price);
                }
            }
        }
    }
}
