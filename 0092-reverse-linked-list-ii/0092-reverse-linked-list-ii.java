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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // Base case: If list is empty or left == right, no change needed
        if (head == null || left == right) {
            return head;
        }

        // Dummy node simplifies edge cases (like reversing from head/left=1)
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Step 1: Reach the node just before the reversal starts (left - 1)
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // 'start' will be the first node of the sub-list to be reversed
        // 'curr' is the node that will be moved to the front of the reversed part
        ListNode start = prev.next;
        ListNode curr = start.next;

        // Step 2: Reverse the sub-list
        // We do this by repeatedly moving the 'curr' node to the position right after 'prev'
        for (int i = 0; i < right - left; i++) {
            start.next = curr.next; // 1. Detach curr
            curr.next = prev.next;  // 2. Point curr to the current front of sub-list
            prev.next = curr;       // 3. Move curr to the front (after prev)
            curr = start.next;      // 4. Move curr pointer to the next node to be processed
        }

        return dummy.next;
    }
}