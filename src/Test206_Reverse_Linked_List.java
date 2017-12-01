/**
 * Created by lujunqiu on 2017/12/1.
 * Description:
 * Reverse a singly linked list.
 */


public class Test206_Reverse_Linked_List {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */

    /**
    单链表的就地逆置：每次逆置一个节点，需要3个指针，一个指针指向当前节点，一个指针指向当前节点的前一个节点，一个指针指向当前节点的后一个节点。
     动手在纸上比划一下，很容易理解，注意边界的情况即可。
     */
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode post = null;

        while (cur != null) {
            post = cur.next;
            cur.next = pre;
            pre = cur;
            cur = post;
        }

        return pre;
    }
}
