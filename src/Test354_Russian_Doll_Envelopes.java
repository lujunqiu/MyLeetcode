import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by lujunqiu on 17/9/13.
 * Description:
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and
 * only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class Test354_Russian_Doll_Envelopes {
    /*
    我们知道最终结果的序列肯定是按照两个索引(w,h)升序排列的,所以我们可以先以第一个索引w为标准将这个二维数组排序,排序之后再按照第二个索引h为标准,找它的最长升序子序列.
    求解数组的最长升序子序列是一个dp问题,状态定义:dp[i]表示以数组第i个元素结尾的最长升序子序列长度.
    这里有一个trick:我们在将二维数组按照每行第一个元素排序的时候,需要自定义一个比较器,其输入参数为一维数组对象.
     */
    static public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {//如果第一个索引相等,则按照第二个索引降序排列
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });
        int max = 0;
        int[] dp = new int[envelopes.length + 1];//动态规划求解数组的最长升序子序列,复杂度为O(n^2)
        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            max = max > dp[i] ? max : dp[i];
        }
        return max;
    }

    /*
    上述代码虽然AC了,但是提交之后的效率不理想,还有优化的空间.
    思路同上,我们只需要改进的是求解数组最长升序子序列的部分,将原来O(n^2)的复杂度提高为O(nlgn).
    dp[i]状态的定义:长度为i的升序子序列最后一个数值(长度相同的子序列取较小的值),这样定义状态就可以保证dp[i]数组是非降序的,可以用二分查找了.
    遍历数组元素,对于每个元素都在dp[]数组中寻找第一个大于该元素的索引位置,替换它即可,若没有找到的话,就插入末尾,长度加1.
     */
    public int maxEnvelopesNlgN(int[][] envelopes) {
        if(envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });
        int dp[] = new int[envelopes.length];
        int len = 0;
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if(index < 0)
                index = -(index + 1);
            dp[index] = envelope[1];
            if(index == len)
                len++;
        }
        return len;
    }
}
