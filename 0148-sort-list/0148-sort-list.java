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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Get total length of the list
        int length = getLength(head);
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 2. Bottom-Up Merge Sort
        // step size: 1, 2, 4, 8... until step < length
        for (int step = 1; step < length; step *= 2) {
            ListNode prev = dummy;
            ListNode curr = dummy.next;
            
            while (curr != null) {
                // Split the list into two halves of size 'step'
                ListNode left = curr;
                ListNode right = split(left, step);
                
                // Move 'curr' to the start of the next pair for the next iteration
                curr = split(right, step);
                
                // Merge the two halves and attach them to the 'prev' tail
                // 'prev' becomes the tail of the newly merged segment
                prev = merge(left, right, prev);
            }
        }
        
        return dummy.next;
    }

    // Helper: Returns the length of the list
    private int getLength(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }

    // Helper: Splits the list into two parts. 
    // The first part contains 'size' nodes (or fewer if end is reached).
    // Returns the head of the *remaining* list.
    private ListNode split(ListNode head, int size) {
        if (head == null) return null;
        
        // Move 'size - 1' steps forward to find the end of the first part
        for (int i = 1; head.next != null && i < size; i++) {
            head = head.next;
        }
        
        ListNode rest = head.next;
        head.next = null; // Cut the connection
        return rest;
    }

    // Helper: Merges two sorted lists (l1, l2) and appends them to 'prev'.
    // Returns the tail of the newly merged list.
    private ListNode merge(ListNode l1, ListNode l2, ListNode prev) {
        ListNode curr = prev;
        
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        
        // Attach any remaining nodes
        if (l1 != null) curr.next = l1;
        else if (l2 != null) curr.next = l2;
        
        // Move curr to the very end of the merged list to return the new tail
        while (curr.next != null) {
            curr = curr.next;
        }
        
        return curr;
    }
}