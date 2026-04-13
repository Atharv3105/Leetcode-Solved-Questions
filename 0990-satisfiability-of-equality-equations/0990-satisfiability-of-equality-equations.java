class Solution {
    // Array to store the parent/root of each lowercase letter
    private int[] parent;

    public boolean equationsPossible(String[] equations) {
        parent = new int[26];
        
        // Initially, every letter is its own parent (isolated)
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }

        // Phase 1: Process all '==' equations to build connected components
        for (String eq : equations) {
            if (eq.charAt(1) == '=') {
                int x = eq.charAt(0) - 'a';
                int y = eq.charAt(3) - 'a';
                union(x, y);
            }
        }

        // Phase 2: Process all '!=' equations to check for contradictions
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                int x = eq.charAt(0) - 'a';
                int y = eq.charAt(3) - 'a';
                
                // If they have the same root, they are mathematically equal.
                // This contradicts the '!=' operator.
                if (find(x) == find(y)) {
                    return false; 
                }
            }
        }

        return true;
    }

    // Find function with Path Compression
    private int find(int i) {
        if (parent[i] == i) {
            return i;
        }
        // Compress the path to keep the tree flat and operations fast
        return parent[i] = find(parent[i]);
    }

    // Union function to merge two sets
    private void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);
        if (rootI != rootJ) {
            parent[rootI] = rootJ;
        }
    }
}