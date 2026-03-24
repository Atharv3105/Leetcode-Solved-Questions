/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;

        while (current != null) {
            // Case 1: Both nodes are in the left subtree
            if (p.val < current.val && q.val < current.val) {
                current = current.left;
            } 
            // Case 2: Both nodes are in the right subtree
            else if (p.val > current.val && q.val > current.val) {
                current = current.right;
            } 
            // Case 3: We found the split point (the LCA)
            else {
                return current;
            }
        }
        return null;
    }
}