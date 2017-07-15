/**
 * Created by lujunqiu on 17/7/4.
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * For example, the following two linked lists:
 * A:          a1 → a2
                        ↘
                        c1 → c2 → c3
                         ↗
   B:       b1 → b2 → b3
   begin to intersect at node c1.

 Notes:

 If the two linked lists have no intersection at all, return null.
 The linked lists must retain their original structure after the function returns.
 You may assume there are no cycles anywhere in the entire linked structure.
 Your code should preferably run in O(n) time and use only O(1) memory.
 */
public class Test160_Intersection_of_Two_Linked_Lists {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) {
     *         val = x;
     *         next = null;
     *     }
     * }
     */
        /*
        理解题意很关键,题目的意思是2个链表交叉点之后的所有节点均相同.这样的话,题目就很好解决了.
        我们得到2个链表长度,取它们的长度差.假设2个链表长度相等,那么我们就可以同步遍历2个链表.
        若长度不相等,我们知道长度差,较长的链表先行遍历,然后在2个链表后续长度相等之后同步开始遍历即可.
         */
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            int A = getLength(headA);
            int B = getLength(headB);
            int n = A - B;
            ListNode hA = headA;
            ListNode hB = headB;
            for (int i = 0; i < Math.abs(n); i++) {
                if (n > 0) {
                    hA = hA.next;
                } else {
                    hB = hB.next;
                }
            }

            while (hA != null && hB != null) {
                if (hA.val == hB.val) {
                    return hA;
                } else {
                    hA = hA.next;
                    hB = hB.next;
                }
            }

            return null;
        }

        static private int getLength(ListNode a) {
            int length = 0;
            while (a != null) {
                length = length + 1;
                a = a.next;
            }
            return length;
        }

        /*
         leetcode 提供的Editorial Solution 思路和我的思路是一样的,取长度差,然后同步遍历.但是做法有所不同,只用到了2个指针,在不记录长度数据的情况下,得到长度差.
         这种做法也值得学习.2个指针同时遍历2个链表,先遍历完成的指针重新指向另外一个链表头节点,继续同步遍历,然后随后遍历完的指针指向另外一个链表头即可.
         */
        public ListNode Editorial(ListNode headA, ListNode headB) {
            ListNode a = headA;
            ListNode b = headB;
            int count = 0;
            if(a == null || b == null)
                return null;

            while (a != b)
            {
                a = a.next;
                b = b.next;
                if (a == null)
                {
                    a = headB;
                    count ++;
                }
                if (b == null)
                    b = headA;
                if (count == 2)
                    return null;
            }
            return a;
        }
    }

