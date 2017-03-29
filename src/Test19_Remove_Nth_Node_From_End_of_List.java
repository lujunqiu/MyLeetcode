/**
 * Created by lujunqiu on 17/3/22.
 * Description:
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example,
 * Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 */
public class Test19_Remove_Nth_Node_From_End_of_List {
    /*
     这题的思路很简单,但是需要考虑到所有可能的边界情况.
     保持2个指针,指针之间的距离为n,然后遍历链表后,其中一个指针就指向了我们要删除的元素.考虑到题目所给的是单向链表,为了方便删除,我们还保存了被删除元素的前驱.
     至此还没有考虑到边界情况,也就是上述思路不work的用例.显然只有一个元素的链表是无法使用上述算法的,需要单独处理.
     为了把所有的边界情况找全,推荐使用测试驱动开发(TDD)的思想,不断使用用例去测试代码,发现bug找到规律再来解决.放在leetcodeOJ上就是不断的submit直到Accepted.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;//指向被删除节点的前驱节点,方便删除操作
        ListNode remove = head; //指向被删除节点
        ListNode end = head;//遍历整个链表,与被删除节点的距离保持为n
        int i = 1;
        while (end.next != null) {
            end = end.next;
            i++;
            if (i > n) {
                remove = remove.next;
            }
            if (i > n + 1) {
                pre = pre.next;
            }
        }
        if (i == 1) {//链表只有1个节点的情况
            return null;
        }
        if (i == n) {//被删除节点为头节点的情况
            return head.next;
        }
        pre.next = remove.next;
        return head;
    }

    /*
    同样的思路,这是Leetcode Editorial Solution给出的答案,代码简洁多了...
    关键在于设置了一个哨兵头节点!
     */
    public ListNode EditorialSolution(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    /*
    Definition for singly-linked list.
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
