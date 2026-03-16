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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        
        // If one child is null, we must take the path with the actual child
        if (root.left == null || root.right == null) {
            return left + right + 1; 
        }
        
        // If both children exist, take the minimum of the two
        return Math.min(left, right) + 1;
    }
}