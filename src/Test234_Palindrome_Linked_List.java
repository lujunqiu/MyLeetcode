/**
 * Created by lujunqiu on 2017/12/4.
 * Description:
 * Given a singly linked list, determine if it is a palindrome.
 */
public class Test234_Palindrome_Linked_List {

      public class ListNode {
          int val;
          ListNode next;
          ListNode(int x) { val = x; }
      }

    /**
     * 时间复杂度O(n),空间复杂度O(1)
     * 首先利用2个指针，slow指针，fast指针，来定位单链表的中间节点。
     * 每次fast移动2个节点，slow移动1个节点，那么fast指针到底链表尾部的时候，slow指针指向链表的中间节点
     * 然后，反转原单链表的后半部分(用到206题的方法,就地逆置单链表)。最后逐个比较2半链表节点的值即可判断是否回文.
     *
     */
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        if (fast != null) {//单链表的节点数为奇数个
            slow = slow.next;
        }

        slow = reverseList(slow);//逆置后半链表
        fast = head;

        while (slow != null) {//逐个比较前半部分和后半部分链表的节点值
            if (fast.val != slow.val) {
                return false;
            }
            fast = fast.next;
            slow = slow.next;
        }
        return true;


    }

    /**
     * 单链表的就地逆置
     * @param head
     * @return
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
