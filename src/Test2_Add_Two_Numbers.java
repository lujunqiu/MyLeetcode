/**
 * Created by lujunqiu on 17/3/6.
 * Description:
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * Example:
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */


//  Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class Test2_Add_Two_Numbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode newHead = new ListNode(0);
        ListNode p3 = newHead;
        int val;// 存储每次求得的两个节点的和
        int carry = 0;//进位的信息

        while (p1 != null || p2 != null) {
            // 节点p1与p2均不为null
            if (p1 != null && p2 != null) {
                val = p1.val + p2.val + carry;
                carry = val / 10;
                p3.next = new ListNode(val % 10);
                p1 = p1.next;
                p2 = p2.next;

            } else if (p2 != null) {// p1为null,p2有值
                val = p2.val + carry;
                carry = val / 10;
                p3.next = new ListNode(val % 10);
                p2 = p2.next;

            } else /*if (p1 != null)*/ {// p2为null,p1有值.节约运行时间为了Accepted注释掉了这个多余的比较表达式.
                val = p1.val + carry;
                carry = val / 10;
                p3.next = new ListNode(val % 10);
                p1 = p1.next;
            }
            p3 = p3.next;
        }

        // 特殊情况,2个节点链一样长,并且最高位产生了进位
        if (p1 == null && p2 == null && carry == 1)
            p3.next = new ListNode(1);
        return newHead.next;
    }
}
