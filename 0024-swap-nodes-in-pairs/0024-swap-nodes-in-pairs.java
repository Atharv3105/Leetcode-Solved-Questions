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
    public ListNode swapPairs(ListNode head) {
        // Edge case: Empty list or single node list requires no swap
        if (head == null || head.next == null) {
            return head;
        }

        // Dummy node acts as the anchor before the head
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode prev = dummy;

        // Traverse while there are at least two nodes left to swap
        while (prev.next != null && prev.next.next != null) {
            // Identify the two nodes to swap
            ListNode first = prev.next;
            ListNode second = prev.next.next;

            // Perform the swap
            // 1. Point prev to the second node (which becomes the new first)
            prev.next = second;
            
            // 2. Point the original first node to the rest of the list
            first.next = second.next;
            
            // 3. Point the second node back to the first node
            second.next = first;

            // Move prev to the next pair (first is now the second node in the pair)
            prev = first;
        }

        return dummy.next;
    }
}