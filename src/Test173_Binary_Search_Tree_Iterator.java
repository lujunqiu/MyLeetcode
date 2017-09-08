import java.util.Stack;

/**
 * Created by lujunqiu on 17/9/6.
 * Description:
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 */
public class Test173_Binary_Search_Tree_Iterator {
    /**
     * Definition for binary tree
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode(int x) { val = x; }
     * }
     */

    public class BSTIterator {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        /*
        二叉排序树或者是一棵空树，或者是具有下列性质的二叉树：
       （1）若左子树不空，则左子树上所有结点的值均小于或等于它的根结点的值；
       （2）若右子树不空，则右子树上所有结点的值均大于或等于它的根结点的值；
       （3）左、右子树也分别为二叉排序树；
        题目要求升序遍历二叉查找树,每次找当前树的最小的值,相当于二叉查找树的中序遍历的过程.
        我们使用stack来完成中序遍历非递归实现.
         */
        public BSTIterator(TreeNode root) {
            while(root != null){
                stack.push(root);
                root = root.left;
            }
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /** @return the next smallest number */
        public int next() {
            TreeNode minCurrent = stack.pop();
            if(minCurrent.right != null){
                TreeNode rightNode = minCurrent.right;
                while(rightNode != null){
                    stack.push(rightNode);
                    rightNode = rightNode.left;
                }
            }
            return minCurrent.val;
        }
    }

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
