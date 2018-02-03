import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lujunqiu on 2018/1/18.
 * Description:
 * There are N network nodes, labelled 1 to N.
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node,
 * and w is the time it takes for a signal to travel from source to target.
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible,
 * return -1.
 */
public class Test743_Network_Delay_Time {
    //保存从源点到其他点的最短路径值
    Map<Integer, Integer> dist;
    /**
     * dijkstra算法(单源最短路径)的应用，使用贪心算法求解，求得图中某一个点到其他点的最短路径，注意dijkstra算法不允许存在负权值的边。
     *
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTime(int[][] times, int N, int K) {
        //初始化图结构
        Map<Integer, List<int[]>> graph = new HashMap();
        for (int[] edge : times) {
            if (!graph.containsKey(edge[0]))
                graph.put(edge[0], new ArrayList<int[]>());
            graph.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }
        dist = new HashMap();
        for (int node = 1; node <= N; ++node)
            dist.put(node, Integer.MAX_VALUE);

        //将源节点K加入结果集
        dist.put(K, 0);
        //记录节点是否已经找到最短路径
        boolean[] seen = new boolean[N + 1];

        while (true) {
            int candNode = -1;
            int candDist = Integer.MAX_VALUE;
            //找到待加入dist结果集的节点
            for (int i = 1; i <= N; ++i) {
                if (!seen[i] && dist.get(i) < candDist) {
                    candDist = dist.get(i);
                    candNode = i;
                }
            }
            //如果所有节点都已经找到最短路径，或者存在节点无法到达，就退出循环
            if (candNode < 0) break;
            //利用新加入的节点更新未加入节点到源节点的距离
            seen[candNode] = true;
            if (graph.containsKey(candNode))
                for (int[] info : graph.get(candNode))
                    dist.put(info[0],
                            Math.min(dist.get(info[0]), dist.get(candNode) + info[1]));
        }

        //完成dijkstra算法之后，找到从源点到其他点的最短路径，从这些最短路径中找到最大值即可。
        int ans = 0;
        for (int cand : dist.values()) {
            if (cand == Integer.MAX_VALUE) return -1;//说明存在节点不可达
            ans = Math.max(ans, cand);
        }
        return ans;
    }
}
