import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceMatrix {
    List<Line> all;
    Map<String, Price> price;

    public PriceMatrix() {
        this.all = DataBase.getLines();
        price = new HashMap<String, Price>();
        work();
    }

    void work() {
        for (Line line : all) {
            for (Stop stop : line.stops) {
                for (Stop to : line.stops) {

                    if (stop.code >= to.code) {
                        continue;
                    }
                    if(stop.code==353&&to.code==735){
                        System.out.println(line.name);
                    }
                    String key = stop.code + "-" + to.code;
                    Price value;
                    if (price.containsKey(key)) {
                        value = price.get(key);
                        if (value.price > line.price) {
                            value = new Price(stop.code, to.code, line.price, line.code);
                        }
                    } else {
                        value = new Price(stop.code, to.code, line.price, line.code);
                    }
                    price.put(key, value);
                }
            }
        }
    }
}

