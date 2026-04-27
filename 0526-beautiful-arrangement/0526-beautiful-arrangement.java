class Solution {
    int count = 0;

    public int countArrangement(int n) {
        boolean[] visited = new boolean[n + 1];
        // Start filling from index 'n' down to 1 for maximum pruning efficiency
        backtrack(n, n, visited);
        return count;
    }

    private void backtrack(int n, int index, boolean[] visited) {
        // Base case: We successfully reached index 0, meaning all 1 to n slots are filled
        if (index == 0) {
            count++;
            return;
        }

        // Try placing every available number from 1 to n into the current index
        for (int i = 1; i <= n; i++) {
            // Check if the number is unvisited AND satisfies the beautiful condition
            if (!visited[i] && (i % index == 0 || index % i == 0)) {
                // 1. CHOOSE
                visited[i] = true;
                
                // 2. EXPLORE (move to the next index, going downwards)
                backtrack(n, index - 1, visited);
                
                // 3. UN-CHOOSE (Backtrack)
                visited[i] = false;
            }
        }
    }
}