import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

/**
 * Created by lujunqiu on 17/4/25.
 * Description:
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * But the following [1,2,2,null,3,null,3] is not:
 */
public class Test101_Symmetric_Tree {
    /*
    我们给定了一个树的根节点,判断是否以根节点为中心是镜像对称的.

    递归的做法:
    首先判断根节点的左右子节点的值是否相等,再判断左右子树是否是镜像对称的即可.
    我们设计的递归函数的输入值为2个"根节点",来判断2个"树"是否镜像对称.
     */
    static public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            return isSymmetric(root.left, root.right);
        }
    }

    static private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null && right != null) {
            return false;
        }
        if (left != null && right == null) {
            return false;
        }
        if (left.val == right.val) {//递归地判断,左子树的右子树与右子树的左子树,以及左子树的左子树与右子树的右子树是否镜像对称
            return isSymmetric(left.right, right.left) && isSymmetric(left.left, right.right);
        } else {
            return false;
        }
    }

    /*
    将上述递归的方法写成非递归的形式;
    思路是一样的,使用2个栈来再现上述递归的过程.但是我第一次实现的非递归的代码虽然AC了,里面有太多的if else(考虑了太多关于null的问题),看起来很别扭.....后面我会把Editorial Solution贴出来反思学习.
     */
    public boolean nonrecursive(TreeNode root) {
        Stack<TreeNode> leftstack = new Stack<>();
        Stack<TreeNode> rightstack = new Stack<>();
        if (root == null) {
            return true;
        } else if (root.left != null && root.right != null) {
            leftstack.push(root.left);
            rightstack.push(root.right);
        } else if (root.left == null && root.right == null) {
            return true;
        } else {
            return false;
        }
        while (true) {
            if (leftstack.empty() && rightstack.empty()) {
                return true;
            }
            if (leftstack.empty() && !rightstack.empty()) {
                return false;
            }
            if (!leftstack.empty() && rightstack.empty()) {
                return false;
            }
            if (leftstack.peek().val == rightstack.peek().val) {
                TreeNode a = leftstack.pop();
                TreeNode b = rightstack.pop();
                if (a.left != null) {
                    leftstack.push(a.left);
                } else {
                    if (b.right != null) {
                        return false;
                    }
                }
                if (a.right != null) {
                    leftstack.push(a.right);
                } else {
                    if (b.left != null) {
                        return false;
                    }
                }
                if (b.right != null) {
                    rightstack.push(b.right);
                }
                if (b.left != null) {
                    rightstack.push(b.left);
                }
            } else {
                return false;
            }
        }
    }
    /*
    Leetcode Editorial Solution给出的非递归算法,比我实现的好看一万倍....
    只用到了一个队列来实现,思路清晰!反观我的代码,考虑太多关于null的问题,导致代码if else很多.bad solution.
    这里将所有return false的情况都列出来,剩下的统一return true,比上面我实现的代码科学很多,至少思路清晰.
     */
    public boolean Editorial(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}

/*
 * Definition for a binary tree node.
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
