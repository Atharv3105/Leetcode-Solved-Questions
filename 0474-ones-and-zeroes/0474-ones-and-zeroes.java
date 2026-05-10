class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        // Step 1: Define the DP table (Capacity constraints)
        // dp[i][j] represents the maximum items we can fit with i zeros and j ones.
        int[][] dp = new int[m + 1][n + 1];

        // Step 2: Iterate through each "Item" (0/1 Knapsack Rule)
        for (String s : strs) {
            int[] count = countZerosOnes(s);
            int zeros = count[0];
            int ones = count[1];

            // Step 3: Iterate through capacities BACKWARDS
            // This is the core of the 0/1 Knapsack pattern. 
            // Going backwards ensures we don't use the same string more than once 
            // for the current capacity state.
            for (int i = m; i >= zeros; i--) {
                for (int j = n; j >= ones; j--) {
                    // State Transition: 
                    // Max(Don't take current string, Take current string + solve for remaining capacity)
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i - zeros][j - ones]);
                }
            }
        }

        return dp[m][n];
    }

    // Helper to count zeros and ones in a string
    private int[] countZerosOnes(String s) {
        int[] count = new int[2];
        for (char c : s.toCharArray()) {
            count[c - '0']++;
        }
        return count;
    }
}