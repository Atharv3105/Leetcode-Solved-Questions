import java.util.*;

class Solution {
    // Helper class to store node information for sorting
    class NodeInfo {
        int row, col, val;
        NodeInfo(int r, int c, int v) {
            this.row = r;
            this.col = c;
            this.val = v;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<NodeInfo> nodeList = new ArrayList<>();
        // 1. Perform DFS/BFS to collect all nodes with coordinates
        dfs(root, 0, 0, nodeList);

        // 2. Sort based on the 3 criteria
        Collections.sort(nodeList, (a, b) -> {
            if (a.col != b.col) return a.col - b.col; // Primary: Column
            if (a.row != b.row) return a.row - b.row; // Secondary: Row
            return a.val - b.val;                    // Tertiary: Value
        });

        // 3. Group the sorted results by column
        List<List<Integer>> result = new ArrayList<>();
        if (nodeList.isEmpty()) return result;

        int lastCol = nodeList.get(0).col;
        List<Integer> currentColumn = new ArrayList<>();
        
        for (NodeInfo node : nodeList) {
            if (node.col != lastCol) {
                result.add(currentColumn);
                currentColumn = new ArrayList<>();
                lastCol = node.col;
            }
            currentColumn.add(node.val);
        }
        result.add(currentColumn);

        return result;
    }

    private void dfs(TreeNode node, int r, int c, List<NodeInfo> list) {
        if (node == null) return;
        list.add(new NodeInfo(r, c, node.val));
        dfs(node.left, r + 1, c - 1, list);
        dfs(node.right, r + 1, c + 1, list);
    }
}