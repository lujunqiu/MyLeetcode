/**
 * Created by lujunqiu on 2017/12/5.
 * Description:
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.
 * For example:
 * Given 1->2->3->4->5->NULL, m = 2 and n = 4,
 * return 1->4->3->2->5->NULL.
 */
public class Test92_Reverse_Linked_List_II {
    /**
     * 此题是单链表就地逆置的扩展，不要求逆置整个单链表，只逆置链表中的一部分。逆置整个单链表可以看作是此题的特例。
     * 核心思想是找到需要逆置的部分单链表的头，然后每次将一个节点逆置，同时在逆置的过程中保存好上下文信息，这样逆置n-m次即可。
     * 在纸上比划一下逆置的过程，就很好理解代码的工作流程了。
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);//哨兵节点，可以帮助我们统一处理边界情况
        dummy.next = head;
        ListNode pre = dummy;

        //pre指向需要逆置的第一个节点的前驱节点，由于存在dummy节点，所以pre节点不可能为null
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
        }

        ListNode cur = pre.next;
        ListNode post = cur.next;

        for (int i = 0; i < n - m; i++) {//每次逆置一个节点，同时保存必要的上下文信息，方便下次逆置使用
            cur.next = post.next;
            post.next = pre.next;
            pre.next = post;
            post = cur.next;
        }
        return dummy.next;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
