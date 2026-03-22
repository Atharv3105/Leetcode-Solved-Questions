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
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        
        while (curr != null || !stack.isEmpty()) {
            // 1. Go as far left as possible
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            
            // 2. Process the current node
            curr = stack.pop();
            k--;
            if (k == 0) return curr.val;
            
            // 3. Move to the right subtree
            curr = curr.right;
        }
        
        return -1; // Should not reach here given constraints
    }
}