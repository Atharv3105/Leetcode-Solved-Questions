/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        
        // Start with the root node.
        Node leftmost = root;
        
        // Traverse down the tree, level by level. 
        // (Since it's a perfect binary tree, checking leftmost.left is sufficient)
        while (leftmost.left != null) {
            
            // Iterate across the current level
            Node head = leftmost;
            while (head != null) {
                // 1. Connect the left child to the right child
                head.left.next = head.right;
                
                // 2. Connect the right child to the next node's left child
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                
                // Move to the next node on the current level
                head = head.next;
            }
            
            // Move down to the start of the next level
            leftmost = leftmost.left;
        }
        
        return root;
    }
}