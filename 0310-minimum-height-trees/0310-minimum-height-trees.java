class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        // Base case: If there is only 1 node, it is the root of the MHT
        if (n == 1) return Collections.singletonList(0);
        
        // Build the adjacency list and degree array
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        
        int[] degree = new int[n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            degree[u]++;
            degree[v]++;
        }
        
        // Initialize the first layer of leaves
        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 1) {
                leaves.offer(i);
            }
        }
        
        // Peel the onion: remove leaves level by level
        int remainingNodes = n;
        while (remainingNodes > 2) {
            int leavesCount = leaves.size();
            remainingNodes -= leavesCount;
            
            // Process all leaves at the current level
            for (int i = 0; i < leavesCount; i++) {
                int leaf = leaves.poll();
                
                // Remove the leaf and update its neighbors
                for (int neighbor : adj.get(leaf)) {
                    degree[neighbor]--;
                    // If a neighbor becomes a leaf, add it to the queue for the next level
                    if (degree[neighbor] == 1) {
                        leaves.offer(neighbor);
                    }
                }
            }
        }
        
        // The remaining nodes are the centroids
        return new ArrayList<>(leaves);
    }
}