import java.util.*;

/**
 * Created by lujunqiu on 17/9/30.
 * Description:
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
 * 1.0 represents the obstacle can't be reached.
 * 2.1 represents the ground can be walked through.
 * 3.The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.

 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first.
 * And after cutting, the original place has the tree will become a grass (value 1).
 * You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees.
 * If you can't cut off all the trees, output -1 in that situation.
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 */
public class Test675_Cut_Off_Trees_for_Golf_Event {
    /*
    题目要求按照树的升序来砍树,同时保证没有树高相同的树.我们首先给所有的树按照树高排序,确定了砍树的顺序之后,再来找遍历所有树所需的最小步数.
    显然,这个2d图中2点之间的最小距离可以用广度优先搜索来求得.
     */
    static public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) {
            return 0;
        }
        //使用一个int数组{i,j,h}来保存一个树节点,i,j分别表示树的下标,h表示树高
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        for (int i = 0; i < forest.size(); i++) {
            for (int j = 0; j < forest.get(i).size(); j++) {
                if (forest.get(i).get(j) > 1) {
                    priorityQueue.add(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }

        int[] start = new int[2];
        int sum = 0;//统计步数

        while (!priorityQueue.isEmpty()) {
            int[] tree = priorityQueue.poll();
            int step = step(forest, start, tree);
            if (step == -1) {
                return -1;
            } else {
                sum += step;
            }
            start[0] = tree[0];
            start[1] = tree[1];
        }
        return sum;
    }

    /**
     * 广度优先搜索2点之间的最短距离
     * @param forest
     * @param start
     * @param tree
     * @return
     */
    static private int step(List<List<Integer>> forest, int[] start, int[] tree) {
        int step = 0;
        int m = forest.size();
        int n = forest.get(0).size();
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            //循环遍历当前节点的下一个广度的所有节点
            for (int i = 0; i < size; i++) {
                int[] t = queue.poll();
                if (t[0] == tree[0] && t[1] == tree[1]) {
                    return step;
                }
                if (t[0] + 1 < m &&!visited[t[0] + 1][t[1]] && forest.get(t[0] + 1).get(t[1]) >= 1) {
                    queue.add(new int[]{t[0] + 1, t[1]});
                    visited[t[0] + 1][t[1]] = true;
                }
                if (t[0] - 1 >= 0 && !visited[t[0] - 1][t[1]] && forest.get(t[0] - 1).get(t[1]) >= 1) {
                    queue.add(new int[]{t[0] - 1, t[1]});
                    visited[t[0] - 1][t[1]] = true;
                }
                if (t[1] + 1 < n && !visited[t[0]][t[1] + 1] && forest.get(t[0]).get(t[1] + 1) >= 1) {
                    queue.add(new int[]{t[0], t[1] + 1});
                    visited[t[0]][t[1] + 1] = true;
                }
                if (t[1] - 1 >= 0 && !visited[t[0]][t[1] - 1] && forest.get(t[0]).get(t[1] - 1) >= 1) {
                    queue.add(new int[]{t[0], t[1] - 1});
                    visited[t[0]][t[1] - 1] = true;
                }
            }
            step++;
        }
        return -1;
    }

    /**
     * 单步调试用的测试用例
     * @param args
     */
    public static void main(String[] args) {
        List<List<Integer>> forest = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list2.add(0);
        list2.add(0);
        list2.add(4);
        list3.add(7);
        list3.add(6);
        list3.add(5);
        forest.add(list1);
        forest.add(list2);
        forest.add(list3);
        System.out.println(cutOffTree(forest));
    }
}
