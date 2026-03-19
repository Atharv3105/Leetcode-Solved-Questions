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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> averages = new ArrayList<>();
        if (root == null) return averages;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            double levelSum = 0;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                levelSum += node.val;

                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            // Calculate the average for this level and add to result
            averages.add(levelSum / levelSize);
        }

        return averages;
    }
}