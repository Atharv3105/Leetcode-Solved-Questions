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
    public boolean isSymmetric(TreeNode root) {
        // An empty tree is symmetric by definition
        if (root == null) {
            return true;
        }
        // Start the synchronized recursion on the left and right subtrees
        return isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode t1, TreeNode t2) {
        // 1. Both null: structural match at the leaves
        if (t1 == null && t2 == null) {
            return true;
        }
        
        // 2. One null, the other isn't: structural mismatch
        if (t1 == null || t2 == null) {
            return false;
        }
        
        // 3. Values don't match: data mismatch
        if (t1.val != t2.val) {
            return false;
        }
        
        // 4. Current nodes match! Cross-check the subtrees for symmetry
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}