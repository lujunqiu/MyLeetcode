/**
 * Created by lujunqiu on 17/3/13.
 * Description:
 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
 * Note: You may not slant the container and n is at least 2.
 */
public class Test11_Container_With_Most_Water {
    /*
     首先,我们先思考一下BruteForce应该如何处理:直接暴力搜索所有的容器,求出每个容器的面积,最后求得最优解.时间复杂度:O(n^2)
     显然,O(n^2)的复杂度是完全不能接受的.那么,应该如何优化?
     在BF算法中,我们是从数组左侧开始逐个向右遍历所有情况.我们可以考虑从2侧往中间靠近指针.
     container的area等于2个索引的距离与高度较短的索引的高度 乘积.那么,我们可以利用其中的数学性质来优化我们的搜索过程,忽略其中一部分解空间.

     假设在搜索的某个状态:L指针(i)和R指针(j)构成了一个container,并且 L.height < R.height,那么这时的area计算公式为:(R-L) * L.height;
     上述公式与R.height无关.那么意味着指针L与指针R-1,R-2,R-3 ...这些指针构成的面积是不可能大于指针L与指针R构成的面积的.
     因为,指针R-1,R-2,R-3 ...这些指针与L指针的距离减少了,并且R-1,R-2,R-3 ...这些指针的高度是不影响我们最优解的搜索的;
     所以我们可以忽略这部分解空间的搜索,直接将L指针右移一位.同样的道理,如果L.height > R.height,我们将R指针左移一位.
     最终我们2个指针重合就搜索完了优化过的解空间(将原来BF算法中的解空间在2侧指针向中间移动的同时逐步优化,每移动一次指针就优化一次解空间),得出最优解.时间复杂度:O(n).
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0, j = height.length - 1;
        while (i < j) {
            maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            if (height[i] > height[j]) {
                j--;
            } else {
                i++;
            }
        }
        return maxArea;
    }
}

