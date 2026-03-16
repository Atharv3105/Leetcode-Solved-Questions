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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPaths(root, targetSum, currentPath, result);
        return result;
    }

    private void findPaths(TreeNode node, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) return;

        // 1. Add current node to the path
        currentPath.add(node.val);

        // 2. Check if it's a leaf and the sum matches
        if (node.left == null && node.right == null && targetSum == node.val) {
            // Must create a NEW list because currentPath is modified during backtracking
            result.add(new ArrayList<>(currentPath));
        } else {
            // 3. Continue exploring subtrees
            findPaths(node.left, targetSum - node.val, currentPath, result);
            findPaths(node.right, targetSum - node.val, currentPath, result);
        }

        // 4. Backtrack: remove the node before returning to parent
        currentPath.remove(currentPath.size() - 1);
    }
}