/**
 * Created by lujunqiu on 17/4/5.
 * Description:
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */
public class Test83_Remove_Duplicates_from_Sorted_List {
    /*
    我给出的解法很直观,首先遇到链表的问题,最好设置一个哨兵guard节点,方便处理.
    利用一个slow指针,一个fast指针遍历链表,遇到相同的元素就地删除,遇到不同的元素继续遍历.
    值得注意的是2个边界情况,一种是所给链表为空;一种是在循环遍历结束后可能存在的重复元素的情况;
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode guard = new ListNode(Integer.MAX_VALUE);//设置新的链表头节点作为哨兵节点
        guard.next = head;

        ListNode slow = guard;
        ListNode fast = head;

        if (head == null) {//处理边界情况
            return null;
        }

        while (fast.next != null) {//利用一个slow指针,一个fast指针遍历链表,遇到相同的元素就地删除,遇到不同的元素继续遍历.
            if (slow.val != fast.val) {
                slow = slow.next;
                fast = fast.next;
            } else {
                slow.next = fast.next;
                fast = fast.next;
            }

        }

        if (slow.val == fast.val) {//处理上述循环结束后有可能出现的最后2个元素相同的情况
            slow.next = null;
        }
        return guard.next;
    }

    /*
    Leetcode给出的解法:思路方法和我写的大概一致,但是代码的写法很简洁,而且容易懂,值得好好学习...还是训练的太少了.
    Leetcode同时给出了该算法的证明:利用loop invariant (循环不变性)<算法导论中提及,在算法中循环的前后都保持不变的一种属性>:
        初始条件: 首次循环前不变性成立
        保持条件: 在循环的某个时刻(非最后时刻)一次循环前不变性如果成立，则下次循环开始前不变性成立
        终止条件: 循环结束后，循环不变性应能表明程序的正确性
     */
    public ListNode EditorialSolution(ListNode head) {
        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
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
