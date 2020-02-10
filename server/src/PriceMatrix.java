import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PriceMatrix {
    List<Line> all;
    Map<String, Price> price;

    public PriceMatrix(List<Line> all) {
        this.all = DataBase.getLines();
        price = new HashMap<String, Price>();
    }

    void work() {
        for (Line line : all) {
            for (Stop stop : line.stops) {
                for (Stop to : line.stops) {
                    if (stop.code > to.code) {
                        continue;
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

