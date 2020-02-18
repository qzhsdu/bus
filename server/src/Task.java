import com.alibaba.fastjson.JSON;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.*;

public class Task {
    static int STOP_NUM = 1511;

    static A findCheap(String fromStop, String toStop){
        PriceMatrix matrixMaker = new PriceMatrix();
        matrixMaker.work();
        Map<String, Price> priceMatrix = matrixMaker.price;
        Stop from = DataBase.getStop(fromStop);
        Stop to = DataBase.getStop(toStop);
        List<Integer> allStop = DataBase.allStopCode();
        int[] dis = new int[STOP_NUM];
        int[] path = new int[STOP_NUM];
        int[] line = new int[STOP_NUM];
        boolean[] vis = new boolean[STOP_NUM];
        Arrays.fill(dis,1000);
        dis[from.code] = 0;
        Queue<Integer> q =new LinkedList<Integer>();
        q.add(from.code);
        while (!q.isEmpty()){
            int now = q.poll();
            if(vis[now]){
                continue;
            }
            vis[now] = true;
            for(int e : allStop){
                String key = now>e?e+"-"+now:now+"-"+e;
                if(vis[e]||!priceMatrix.containsKey(key)){
                    continue;
                }//visited
                Price l = priceMatrix.get(key);
                if(dis[e]>dis[now]+l.price){
                    dis[e] = dis[now]+l.price;
                    path[e] = now;
                    line[e] = l.linecode;
                    q.add(e);
                }
            }
        }
        System.out.println(dis[to.code]);
        int cur = to.code;
        Stack<Integer> stops = new Stack<>();
        Stack<Integer> lines = new Stack<>();
        while(cur!=0&&cur!=from.code){
            try{
                System.out.println(DataBase.getStopbyCode(cur).name+"\nline:"+DataBase.getLinebyCode(line[cur]).name);
                stops.push(cur);
                lines.push(line[cur]);
            }catch (Exception e){
                System.out.println(cur);
                System.out.println(line[cur]);
            }
            cur = path[cur];
        }
        stops.push(from.code);
        A cheap = new A(dis[to.code],-1);
        while(!lines.empty()){
            int now_x = stops.pop();
            cheap.stops.add(DataBase.getStopbyCode(now_x).name);
//            int now_y = stops.peek();
//            cheap.stops.add(DataBase.getStopbyCode(now_y).name);
            cheap.lines.add(DataBase.getLinebyCode(lines.pop()));
//            System.out.println(DataBase.getStopbyCode(now_x).name+
//                    DataBase.getStopbyCode(now_y).name);
//            Line temp = DataBase.getLinebyCode(lines.pop());
//            Boolean f = false;
//            System.out.println(temp.name);
//            for(Stop s : temp.stops){
//                if(s.code==now_x||s.code==now_y){
//                    f = !f;
//                    System.out.println(s.name);
//                    continue;
//                }
//                if(f){
//                    System.out.println(s.name);
//                }
//            }
        }
        return cheap;
    }

    static A findFast(String fromStop, String toStop,int interval){
        FastMatrix timeMatrixMaker = new FastMatrix();
        Map<String, Fast> m = timeMatrixMaker.map;
        Stop from = DataBase.getStop(fromStop);
        Stop to = DataBase.getStop(toStop);
        List<Integer> allStop = DataBase.allStopCode();
        double[] dis = new double[STOP_NUM];
        int[] path = new int[STOP_NUM];
        int[] line = new int[STOP_NUM];
        boolean[] vis = new boolean[STOP_NUM];
        Arrays.fill(dis,99999999);
        dis[from.code] = 0;
        Queue<K> q =new LinkedList<>();
        q.add(new K(from.code,0));
        while (!q.isEmpty()){
            K now = q.poll();
            if(vis[now.stop]){
                continue;
            }
            vis[now.stop] = true;
            for(int e : allStop){
                String key = now.stop>e?e+"-"+now.stop:now.stop+"-"+e;
                if(vis[e]||!m.containsKey(key)){
                    continue;
                }//visited
                Fast l = m.get(key);
                double distnew = dis[now.stop]+l.time+l.linecode==now.line?0:interval;
                if(dis[e]>distnew){
                    dis[e] = dis[now.stop]+l.time;
                    path[e] = now.stop;
                    line[e] = l.linecode;
                    q.add(new K(e,l.linecode));
                }
            }
        }

        int cur = to.code;
        A a = new A(-1,dis[to.code]);
//        a.stops.add(to.name);
        while(cur!=0&&cur!=from.code){
            try{
                a.stops.add(DataBase.getStopbyCode(cur).name);
                a.lines.add(DataBase.getLinebyCode(line[cur]));
                System.out.println(DataBase.getStopbyCode(cur).name+"\nline:"+DataBase.getLinebyCode(line[cur]).name);
            }catch (Exception e){
                e.printStackTrace();
            }
            cur = path[cur];
        }
        a.stops.add(from.name);
        Collections.reverse(a.stops);
        return a;
    }
    static public void main(String[] args){
        Task.findFast("齐鲁软件学院","东仓",2000);
        String json = JSON.toJSONString(DataBase.getStop("大明湖"));
        System.out.println(json);
    }
}
class K{
    int stop,line;

    public K(int stop, int line) {
        this.stop = stop;
        this.line = line;
    }
}
