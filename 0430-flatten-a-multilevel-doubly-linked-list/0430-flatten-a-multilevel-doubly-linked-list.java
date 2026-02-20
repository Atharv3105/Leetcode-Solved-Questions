/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {
    public Node flatten(Node head) {
        if (head == null) return null;
        
        Node curr = head;
        
        while (curr != null) {
            // Case 1: No child, just keep moving
            if (curr.child == null) {
                curr = curr.next;
                continue;
            }
            
            // Case 2: Has a child, we need to splice it in
            Node child = curr.child;
            
            // Step A: Find the tail of the child level
            Node tail = child;
            while (tail.next != null) {
                tail = tail.next;
            }
            
            // Step B: Connect the tail of the child list to curr.next
            tail.next = curr.next;
            if (curr.next != null) {
                curr.next.prev = tail;
            }
            
            // Step C: Connect curr to the head of the child list
            curr.next = child;
            child.prev = curr;
            
            // Step D: Clean up the child pointer
            curr.child = null;
            
            // Move to the next node (which is now the former child)
            curr = curr.next;
        }
        
        return head;
    }
}