/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        // If checkHeight returns -1, the tree is unbalanced.
        return checkHeight(root) != -1;
    }
    
    private int checkHeight(TreeNode node) {
        // Base case: an empty tree has a height of 0
        if (node == null) {
            return 0;
        }
        
        // 1. Traverse Left
        int leftHeight = checkHeight(node.left);
        // If the left subtree is unbalanced, propagate the failure upwards instantly
        if (leftHeight == -1) return -1;
        
        // 2. Traverse Right
        int rightHeight = checkHeight(node.right);
        // If the right subtree is unbalanced, propagate the failure upwards instantly
        if (rightHeight == -1) return -1;
        
        // 3. Process Current Node: Check if the current subtree is balanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // Unbalanced! Return the red flag.
        }
        
        // Return the valid height of the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }
}