class Solution {
    // Standard Union-Find (DSU) class
    class DSU {
        int[] parent;
        
        public DSU(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i; // Every index is initially its own parent
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }
    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        DSU dsu = new DSU(n);
        
        // 1. Build the connected components
        for (List<Integer> pair : pairs) {
            dsu.union(pair.get(0), pair.get(1));
        }
        
        // 2. Group characters by their root index
        // Map: Root Index -> Min-Heap of Characters
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).offer(s.charAt(i));
        }
        
        // 3. Reconstruct the string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            // Poll the smallest character available for this component
            sb.append(map.get(root).poll());
        }
        
        return sb.toString();
    }
}