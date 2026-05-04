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
    public TreeNode invertTree(TreeNode root) {
        // Base case: If the tree is empty, return null
        if (root == null) {
            return null;
        }
        
        // 1. Recursively invert the left subtree
        TreeNode invertedLeft = invertTree(root.left);
        
        // 2. Recursively invert the right subtree
        TreeNode invertedRight = invertTree(root.right);
        
        // 3. Swap the left and right children of the current node
        root.left = invertedRight;
        root.right = invertedLeft;
        
        // 4. Return the root of the newly inverted subtree
        return root;
    }
}