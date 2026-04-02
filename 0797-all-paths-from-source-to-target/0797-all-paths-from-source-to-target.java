class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        
        // Always start the path at node 0
        currentPath.add(0);
        
        // Start DFS from node 0, with target node as graph.length - 1
        dfs(graph, 0, graph.length - 1, currentPath, result);
        
        return result;
    }
    
    private void dfs(int[][] graph, int currentNode, int targetNode, List<Integer> currentPath, List<List<Integer>> result) {
        // Base Case: If we've reached the target node, add a copy of the path to the result
        if (currentNode == targetNode) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        
        // Iterate through all the neighbors of the current node
        for (int nextNode : graph[currentNode]) {
            // 1. Choose: Add the neighbor to the current path
            currentPath.add(nextNode);
            
            // 2. Explore: Move to the neighbor
            dfs(graph, nextNode, targetNode, currentPath, result);
            
            // 3. Un-choose (Backtrack): Remove the neighbor to explore other paths
            currentPath.remove(currentPath.size() - 1);
        }
    }
}