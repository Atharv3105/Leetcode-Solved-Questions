class Solution {
    public int uniquePaths(int m, int n) {
        // We only need a 1D array of size n to keep track of the row above us
        int[] dp = new int[n];
        
        // Base Case: The entire first row only has 1 path (going straight right)
        for (int j = 0; j < n; j++) {
            dp[j] = 1;
        }
        
        // Loop through the remaining rows (from row 1 to m-1)
        for (int i = 1; i < m; i++) {
            // Loop through the columns (from col 1 to n-1)
            // Note: dp[0] stays 1 because the first column is always 1
            for (int j = 1; j < n; j++) {
                // dp[j] is currently the value from the row above
                // dp[j-1] is the value from the left in our current row
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        
        // The last element in our array is the bottom-right corner
        return dp[n - 1];
    }
}