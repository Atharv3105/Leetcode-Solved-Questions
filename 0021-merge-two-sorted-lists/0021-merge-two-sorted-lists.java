/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy node to act as the start of the new list
        ListNode dummy = new ListNode(-1);
        
        // 'current' tracks the last node in our new merged list
        ListNode current = dummy;

        // Iterate while both lists have nodes remaining
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            // Move our pointer forward in the merged list
            current = current.next;
        }

        // Exact optimization: One list will be empty, the other might still have nodes.
        // Since the remaining nodes are already sorted, just attach the non-null list.
        current.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }
}