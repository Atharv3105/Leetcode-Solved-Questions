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
    int postIndex;
    Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // Start from the last element of postorder
        postIndex = postorder.length - 1;
        inorderMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return build(postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] postorder, int left, int right) {
        if (left > right) return null;

        // The root is the current last element in postorder
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);

        // Find where this root splits the inorder array
        int mid = inorderMap.get(rootVal);

        // IMPORTANT: Build the right subtree first!
        // This is because postIndex moves backwards from Root -> Right -> Left
        root.right = build(postorder, mid + 1, right);
        root.left = build(postorder, left, mid - 1);

        return root;
    }
}