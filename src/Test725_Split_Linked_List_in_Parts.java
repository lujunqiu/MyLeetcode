/**
 * Created by lujunqiu on 17/11/16.
 * Description:
 * Given a (singly) linked list with head node root, write a function to split the linked list into k consecutive linked list "parts".
 * The length of each part should be as equal as possible: no two parts should have a size differing by more than 1. This may lead to
 * some parts being null.
 * The parts should be in order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal
 * parts occurring later.
 * Return a List of ListNode's representing the linked list parts that are formed.

 * Examples 1->2->3->4, k = 5 // 5 equal parts [ [1], [2], [3], [4], null ]
 * Example 1:
 Input:  root = [1, 2, 3], k = 5 Output: [[1],[2],[3],[],[]] Explanation: The input and each element of the output are ListNodes, not arrays.
 For example, the input root has root.val = 1, root.next.val = 2, \root.next.next.val = 3, and root.next.next.next = null.
 The first element output[0] has output[0].val = 1, output[0].next = null. The last element output[4] is null, but it's string representation as a ListNode is [].

 Example 2:
 Input:
 root = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], k = 3
 Output: [[1, 2, 3, 4], [5, 6, 7], [8, 9, 10]]
 Explanation:
 The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
 */
public class Test725_Split_Linked_List_in_Parts {
    /**
     根据题目要求,需要将单链表分成k个单链表,并且k个单链表的长度为x或x+1,保持原来的单链表的顺序不变,如果同时存在长度为x+1和x的情况,那么较长的单链表在前面.
     由于只有可能存在2种可能的长度,那么上述的x值为length(原单链表长度)除以k(需要划分的个数)向下取整的数值.同时长度为x+1的个数为length除以k的余数.
     知道了划分的个数k,以及划分的长度x和x+1,以及长度为x+1的个数,我们遍历原单链表,在原单链表的基础上进行划分的操作即可.
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        int length = 0;
        ListNode node = root;
        //得到表长度
        while (node != null) {
            length++;
            node = node.next;
        }

        int a = length / k;
        int b = length % k;

        node = null;
        ListNode temp = root;
        ListNode[] result = new ListNode[k];

        //划分为k个单链表
        for (int i = 0; i < k; i++) {
            ListNode node1 = temp;
            for (int j = 0; j < a + (b > 0 ? 1 : 0) - 1; j++) {
                node1 = node1.next;
            }
            if (temp != null) {
                result[i] = temp;
                temp = node1.next;
                node1.next = null;
                b--;
            } else {
                result[i] = null;
            }
        }
        return result;
    }
}
