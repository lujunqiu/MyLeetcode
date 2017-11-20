/**
 * Created by lujunqiu on 17/11/19.
 * Description:
 * You are given a binary tree in which each node contains an integer value.
 * Find the number of paths that sum to a given value.
 * The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
 * The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
 * Example:

 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

       10
      /  \
     5   -3
    / \   \
   3   2   11
  / \   \
 3  -2   1

 Return 3. The paths that sum to 8 are:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3. -3 -> 11
 */
public class Test437_Path_Sum_III {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /**
    根据题意,我们需要找到所有满足条件的路径(路径的起点和终点不一定是根节点或者叶子节点),但是要求路径是可达的而且路径的方向是从上往下的(对于二叉树结构)
     对于二叉树这种数据结构的算法,递归求解是很常用的方法,因为每一个节点都可以单独作为一个二叉树的根节点

     对于这个题目,我们需要找到满足题目条件的路径(路径的和等于特定值)总共有多少个.我们可以深度优先搜索,搜索的标准是以当前节点为起点的,满足题目的路径有多少个,那么我们从所有可能的起点深度搜索就可以找到答案了.
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    /**
    深度优先搜索:找到当前节点为起点,和为sum的路径有多少个
     */
    private int pathSumFrom(TreeNode node, int sum) {
        if (node == null) return 0;
        //统计所有以当前节点为起点的,和为输入值的路径个数
        return (node.val == sum ? 1 : 0)
                + pathSumFrom(node.left, sum - node.val) + pathSumFrom(node.right, sum - node.val);
    }

    public static void main(String[] args) {
        Test437_Path_Sum_III test = new Test437_Path_Sum_III();
        TreeNode node1 = test.new TreeNode(10);
        TreeNode node2 = test.new TreeNode(5);
        TreeNode node3 = test.new TreeNode(-3);
        TreeNode node4 = test.new TreeNode(3);
        TreeNode node5 = test.new TreeNode(2);
        TreeNode node6 = test.new TreeNode(11);
        TreeNode node7 = test.new TreeNode(3);
        TreeNode node8 = test.new TreeNode(-2);
        TreeNode node9 = test.new TreeNode(1);
        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = null;
        node3.right = node6;

        node4.left = node7;
        node4.right = node8;

        node5.left = null;
        node5.right = node9;

        node6.left = null;
        node6.right = null;

        node7.left = null;
        node7.right = null;

        node8.left = null;
        node8.right = null;

        node9.left = null;
        node9.right = null;

        System.out.println(test.pathSum(node1, 8));
    }
}
