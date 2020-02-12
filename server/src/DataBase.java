import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    static Connection conn;
    static String findStopByName = "SELECT * FROM stops WHERE name = ?";
    static String findStopByCode = "SELECT * FROM stops WHERE code = ?";
    static String findAllLine = "SELECT * FROM  data";
    static String findAllStop = "SELECT * FROM stops order by code";
    static String findLineByCode = "SELECT * FROM data WHERE 编号 = ?";
    static void getConnection(){
        try {
            if(conn==null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bus?serverTimezone=Asia/Shanghai",
                        "root","root");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    static Stop getStop(String name){
        getConnection();
        try{
            PreparedStatement p = conn.prepareStatement(findStopByName);
            p.setString(1,name);
            ResultSet res =  p.executeQuery();
            if(res.next()){
                return new Stop(res.getString("name"),
                        res.getDouble("x"),res.getDouble("y"),res.getInt("code"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    static List<Line> getLines(){
        getConnection();
        List<Line> all = new ArrayList<Line>();
        try{
            PreparedStatement p = conn.prepareStatement(findAllLine);
            ResultSet res = p.executeQuery();
            while (res.next()){
                Line now = new Line(res.getString("线路名"),
                        res.getInt("编号"),res.getInt("价格"));
                now.setStops(res.getString("路线"));
                all.add(now);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return all;
    }
    static List<Integer> allStop(){
        getConnection();
        List<Integer> stops = new ArrayList<Integer>();
        try {
            PreparedStatement p = conn.prepareStatement(findAllStop);
            ResultSet res = p.executeQuery();
            while (res.next()){
                stops.add(res.getInt("code"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return stops;
    }

    static Stop getStopbyCode(int code){
        getConnection();
        try{
            PreparedStatement p = conn.prepareStatement(findStopByCode);
            p.setInt(1,code);
            ResultSet res =  p.executeQuery();
            if(res.next()){
                return new Stop(res.getString("name"),
                        res.getDouble("x"),res.getDouble("y"),res.getInt("code"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    static Line getLinebyCode(int code){
        getConnection();
        try{
            PreparedStatement p = conn.prepareStatement(findLineByCode);
            p.setInt(1,code);
            ResultSet res =  p.executeQuery();
            if(res.next()){
                Line now = new Line(res.getString("线路名"),
                        res.getInt("编号"),res.getInt("价格"));
                now.setStops(res.getString("路线"));
                return now;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
