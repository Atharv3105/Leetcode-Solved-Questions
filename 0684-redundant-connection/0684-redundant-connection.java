class Solution {
    // Array to store the parent/root of each node
    private int[] parent;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        parent = new int[n + 1]; // +1 because nodes are 1-indexed (1 to n)
        
        // Initially, every node is its own parent (isolated)
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
        
        // Process each edge
        for (int[] edge : edges) {
            int rootU = find(edge[0]);
            int rootV = find(edge[1]);
            
            // If they share the same root, a cycle is detected!
            if (rootU == rootV) {
                return edge;
            }
            
            // Otherwise, union the two sets by linking one root to the other
            parent[rootU] = rootV;
        }
        
        return new int[0]; // Should not be reached given problem constraints
    }
    
    // Find function with Path Compression
    private int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        // Path Compression: directly link the node to the absolute root 
        // to flatten the tree and massively speed up future lookups
        return parent[node] = find(parent[node]); 
    }
}