/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        // Phase 1: Detect if a cycle exists
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            // If they meet, a cycle is detected
            if (slow == fast) {
                // Phase 2: Find the entry point
                slow = head; // Reset slow to head
                
                // Move both pointers 1 step at a time
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                
                return slow; // The start of the cycle
            }
        }
        
        return null; // No cycle found
    }
}
