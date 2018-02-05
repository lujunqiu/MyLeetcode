/**
 * Created by lujunqiu on 2018/2/3.
 * Description:
 * We have some permutation A of [0, 1, ..., N - 1], where N is the length of A.
 * The number of (global) inversions is the number of i < j with 0 <= i < j < N and A[i] > A[j].
 * The number of local inversions is the number of i with 0 <= i < N and A[i] > A[i+1].
 * Return true if and only if the number of global inversions is equal to the number of local inversions.
 * <p>
 * Example 1:
 * Input: A = [1,0,2]
 * Output: true
 * Explanation: There is 1 global inversion, and 1 local inversion.
 * Example 2:
 * Input: A = [1,2,0]
 * Output: false
 * Explanation: There are 2 global inversions, and 1 local inversion.
 */
public class Test775_Global_and_Local_Inversions {
    /**
     * local inversion 本来就属于global inversion的一部分，所以遍历数组找到每次出现local inversion的地方，判断是否会有其他的global inversion出现即可。
     * 可以画图理解，数组索引值为横坐标，数组值为纵坐标，画出升降关系。
     * @param A
     * @return
     */
    public boolean isIdealPermutation(int[] A) {
        boolean tag = true;//表明状态,有两种状态，一种是升后降，一种是降后升
        int tempMax = Integer.MIN_VALUE;//保存当前节点i的前i-2个节点的最大值
        if (A.length == 1) {
            return true;
        }
        for (int i = 1; i < A.length; i++) {
            int k = i - 2 >= 0 ? i - 2 : -1;
            if (k >= 0 && A[k] > tempMax) {
                tempMax = A[k];//tempMax保存的是当前节点i的前i-2个节点中的最大值
            }
            if (A[i] >= A[i - 1]) {
                if (tag) {//判断当前节点处于的状态
                    continue;
                } else {
                    if (A[i] >= tempMax) {
                        tag = true;
                        continue;
                    } else {
                        return false;
                    }
                }
            } else {
                tag = false;
                if (A[i] >= tempMax) {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
