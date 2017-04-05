/**
 * Created by lujunqiu on 17/4/5.
 * Description:
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */
public class Test83_Remove_Duplicates_from_Sorted_List {

    public ListNode deleteDuplicates(ListNode head) {
        ListNode guard = new ListNode(Integer.MAX_VALUE);//设置新的链表头节点作为哨兵节点
        guard.next = head;
        ListNode slow = guard;
        ListNode fast = head;
        while (fast.next != null) {
            while (slow.val == fast.val && fast.next != null) {
                fast = fast.next;
            }
            slow.next = fast;
            slow = slow.next;
            fast = fast.next;
        }

        return guard.next;
    }

    /*
    Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
