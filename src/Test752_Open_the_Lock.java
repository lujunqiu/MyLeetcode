import java.util.*;

/**
 * Created by lujunqiu on 2018/1/11.
 * Description:
 * You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'.
 * The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'.
 * Each move consists of turning one wheel one slot.
 * The lock initially starts at '0000', a string representing the state of the 4 wheels.
 * You are given a list of deadends dead ends, meaning if the lock displays any of these codes,
 * the wheels of the lock will stop turning and you will be unable to open it.
 * Given a target representing the value of the wheels that will unlock the lock,
 * return the minimum total number of turns required to open the lock, or -1 if it is impossible.

 * Example 1:
 * Input: deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * Output: 6
 * Explanation:
 * A sequence of valid moves would be "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202".
 * Note that a sequence like "0000" -> "0001" -> "0002" -> "0102" -> "0202" would be invalid,
 * because the wheels of the lock become stuck after the display becomes the dead end "0102".

 * Example 2:
 * Input: deadends = ["8888"], target = "0009"
 * Output: 1
 * Explanation:
 * We can turn the last wheel in reverse to move from "0000" -> "0009".

 * Example 3:
 * Input: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * Output: -1
 * Explanation:
 * We can't reach the target without getting stuck.

 * Example 4:
 * Input: deadends = ["0000"], target = "8888"
 * Output: -1
 */
public class Test752_Open_the_Lock {
    /**
    广度优先搜索,还有一种更加快的搜索A*算法(当A*算法中预估距离小于等于实际距离的时候,A*算法可以找到最短路径,有空可以在博客中介绍这种解法)
     */
    public int openLock(String[] deadends, String target) {
        Set<String> dead = new HashSet<>();//无法访问的节点
        for (String s : deadends) dead.add(s);
        if (dead.contains("0000")) return -1;
        if ("0000".equals(target)) return 0;
        Set<String> v = new HashSet<>();//已经访问过的节点
        Queue<String> q = new LinkedList<>();//广度优先搜索，等待访问的节点
        q.add("0000");

        for (int d = 1;!q.isEmpty();d++) {//d表示广度优先搜索的当前层数
            for (int i = q.size(); i > 0; i--) {//访问当前层的所有节点
                String cur = q.poll();
                for (int j = 0; j < 4; j++) {//将当前节点的领域节点加入待访问节点队列中，加入的节点不能是已经访问过的节点也不能是无法到达的节点
                    char[] n1 = cur.toCharArray();
                    char[] n2 = cur.toCharArray();

                    n1[j] = (char) ((n1[j] - '0' + 1) % 10 + '0');
                    n2[j] = (char) ((n2[j] - '0' + 10 - 1) % 10 + '0');

                    String s1 = new String(n1);
                    String s2 = new String(n2);
                    if (s1.equals(target) || s2.equals(target)) {
                        return d;
                    }
                    if (!v.contains(s1)&&!dead.contains(s1)) {
                        q.add(s1);
                    }
                    if (!v.contains(s2)&&!dead.contains(s2)) {
                        q.add(s2);
                    }
                    v.add(s1);
                    v.add(s2);
                }
            }
        }
        return -1;
    }
}
