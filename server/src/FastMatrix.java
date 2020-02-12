import static java.lang.StrictMath.*;

public class FastMatrix {
    double getValue(Stop from,Stop to,Line line){
        double radLng1 = radians(from.x);
        double radLat1 = radians(from.y);
        double radLng2 = radians(to.x);
        double radLat2 = radians(to.y);
        double a = radLat1 - radLat2;
        double b = radLng1 - radLng2;

        return 2 * asin(sqrt(sin(a / 2) * sin(a / 2) +
                cos(radLat1) * cos(radLat2) * sin(b / 2) * sin(b / 2))) * 6378.137 / line.speed;
    }
    public static double radians(double d) {
        return d * Math.PI / 180.0;
    }


}
