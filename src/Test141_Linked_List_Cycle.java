/**
 * Created by lujunqiu on 17/7/15.
 * Given a linked list, determine if it has a cycle in it.
 */

import java.util.HashSet;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Test141_Linked_List_Cycle {
    /*
    我看到这个题目,首先想到的就是遍历所有节点,保存下来,如果在遍历得过程中发现重复的节点就判断有环,反之,则没有.
    这里可以用set集合来保存节点.
     */
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (!set.contains(head)) {
                set.add(head);
                head = head.next;
            } else {
                return true;
            }
        }
        return false;
    }

    /*
    上述方法需要额外的空间复杂度,如果单链表过长的话,集合元素就很多了,我们可以提出一个不需要额外空间的算法.
    基本思路是我们类比2个人赛跑,一个人跑的快,一个人跑的慢.如果是一条无环路线,那么2个人永远不会相遇;如果是有环的路径,那么跑得快的一定会追上跑得慢的,2个人就会相遇.
    这里,代码实现的时候,2个人就用2个指针来代替,一个指针快,一个指针慢,速度差是1.这样的化,如果单链表有环则一定相遇,如果没环则不会相遇.
    在实现的过程中,需要注意边界情况的处理,写出bug free 的代码.
     */
    public boolean race(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}
