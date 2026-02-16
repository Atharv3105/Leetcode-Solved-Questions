/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Boundary check
        if (headA == null || headB == null) return null;
        
        ListNode a = headA;
        ListNode b = headB;
        
        // If a & b have different len, then we will stop the loop after second iteration
        while (a != b) {
            // For the end of first iteration, we reset the pointer to the head of the other linked list
            a = (a == null) ? headB : a.next;
            b = (b == null) ? headA : b.next;    
        }
        
        return a;
    }
}