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
    public ListNode middleNode(ListNode head) {
        // Initialize two pointers, both starting at the head
        ListNode slow = head;
        ListNode fast = head;

        // Traverse until the fast pointer reaches the end
        // fast != null checks if we are at the last node (odd length lists)
        // fast.next != null checks if we are at the second to last node (even length lists)
        while (fast != null && fast.next != null) {
            slow = slow.next;       // Move slow 1 step
            fast = fast.next.next;  // Move fast 2 steps
        }

        // When fast reaches the end, slow is at the middle
        return slow;
    }
}