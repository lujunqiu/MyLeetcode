/**
 * Created by lujunqiu on 2017/12/18.
 * Description:
 * Given a binary tree where every node has a unique value, and a target key k, find the value of the closest leaf node to target k in the tree.
 * Here, closest to a leaf means the least number of edges travelled on the binary tree to reach any leaf of the tree. Also, a node is called a leaf if it has no children.
 * In the following examples, the input tree is represented in flattened form row by row. The actual root tree given will be a TreeNode object.
 * Example 1:
 * Input:
 * root = [1, 3, 2], k = 1
 * Diagram of binary tree:
 *   1
 *  / \
 * 3   2
 * Output: 2 (or 3)
 * Explanation: Either 2 or 3 is the closest leaf node to the target of 1.
 */

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */

public class Test742_Closest_Leaf_in_a_Binary_Tree {
    /**
     * 思路：利用深度优先搜索找到特定的k节点(入参给定)，在找到k节点之后，需要搜索离k节点最近的叶子节点，第一想法就是广度优先搜索，但是这是一个树结构，不是图，不能从child节点往回搜索parent节点。
     * 为了可以使用BFS寻找距离最近的叶子节点，我们需要知道从child节点到parent节点的路径信息。但是，这里我们又不需要完全知道所有的child到parent的路径信息，因为是搜索离特定节点最近的叶子节点，广度优先搜索每次往上搜索一层即可。
     * 我们只需要知道从当前k节点到树根节点的路径信息即可，这个信息完全可以在DFS搜索k节点的时候，一边搜索一边保存下来。
     * 于是利用BFS+DFS即可达到目的，搜索离特定节点最近的叶子节点。
     */
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> backmap = new HashMap<>();//通过保存在DFS过程中的路径，将tree变成一个"无向图"(可以通过child访问到parent)
        Queue<TreeNode> queue = new LinkedList<>();//BFS寻找最近的叶子节点，这是无向图中的操作，需要利用到DFS过程保存的路径信息(backmap)
        Set<TreeNode> set = new HashSet<>();//BFS的过程很有可能会访问到之前访问到的节点，用一个set保存访问过的节点，防止2次访问，造成死循环

        TreeNode kNode = DFS(root, k, backmap);
        queue.add(kNode);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            set.add(cur);
            if (cur != null && cur.left == null && cur.right == null) {
                return cur.val;
            }
            if (cur.right != null && !set.contains(cur.right)) {
                queue.add(cur.right);
            }
            if (cur.left != null && !set.contains(cur.left)) {
                queue.add(cur.left);
            }
            if (backmap.get(cur) != null && !set.contains(backmap.get(cur))) {//往上搜索，利用DFS得到的路径信息(无向图的概念)
                queue.add(backmap.get(cur));
            }
        }
        return -1;//找不到最近的叶子节点
    }

    private TreeNode DFS(TreeNode root, int k, Map backmap) {
        if (root == null) {
            return null;
        }
        if (root.val == k) {
            return root;
        }
        if (root.right != null) {
            backmap.put(root.right, root);//保存从child到parent节点的路径信息
            TreeNode right = DFS(root.right, k, backmap);
            if (right != null) {
                return right;
            }
        }
        if (root.left != null) {
            backmap.put(root.left, root);//保存从child到parent节点的路径信息
            TreeNode left = DFS(root.left, k, backmap);
            if (left != null) {
                return left;
            }
        }
        return null;
    }
}
