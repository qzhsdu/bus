import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.*;

public class Task {
    static void findCheap(String fromStop,String toStop){
        Map<String, Price> priceMatrix = new HashMap<>();
        Stop from = DataBase.getStop(fromStop);
        Stop to = DataBase.getStop(toStop);
        List<Integer> allStop = DataBase.allStop();
        int[] dis = new int[allStop.size()];
        int[] path = new int[allStop.size()];
        int[] line = new int[allStop.size()];
        boolean[] vis = new boolean[allStop.size()];
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
                    path[now] = e;
                    line[now] = l.linecode;
                    q.add(e);
                }
            }
        }
        System.out.println(dis[to.code]);
        int cur = to.code;
        while(cur!=0){
            System.out.println(cur+" line"+line[cur]);
            cur = path[cur];
        }
    }
}
