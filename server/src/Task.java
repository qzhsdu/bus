import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.util.*;

public class Task {
    static int STOP_NUM = 1511;

    static void findCheap(String fromStop, String toStop){
        PriceMatrix matrixMaker = new PriceMatrix();
        matrixMaker.work();
        Map<String, Price> priceMatrix = matrixMaker.price;
        Stop from = DataBase.getStop(fromStop);
        Stop to = DataBase.getStop(toStop);
        List<Integer> allStop = DataBase.allStop();
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
//            System.out.print(DataBase.getStopbyCode(now).name+"\n-----");
            vis[now] = true;
            for(int e : allStop){
                String key = now>e?e+"-"+now:now+"-"+e;
                if(vis[e]||!priceMatrix.containsKey(key)){
                    continue;
                }//visited
                Price l = priceMatrix.get(key);
                if(dis[e]>dis[now]+l.price){
//                    System.out.println(DataBase.getStopbyCode(e).name);
//                    if(e==735){
//                        System.out.println(l.linecode);
//                        System.out.println(DataBase.getStopbyCode(now).name);
//                    }

                    dis[e] = dis[now]+l.price;
                    path[e] = now;
                    line[e] = l.linecode;
                    q.add(e);
                }
            }
//            System.out.println("------------");
        }
        System.out.println(dis[to.code]);
        int cur = to.code;
        while(cur!=0&&cur!=from.code){
            try{
                System.out.println(DataBase.getStopbyCode(cur).name+"\nline:"+DataBase.getLinebyCode(line[cur]).name);
            }catch (Exception e){
                System.out.println(cur);
                System.out.println(line[cur]);
            }

            cur = path[cur];
        }
    }
    static public void main(String[] args){
        Task.findCheap("齐鲁软件学院","大明湖");
    }
}
