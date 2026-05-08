class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // If the starting point is an obstacle, the robot is trapped instantly
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        
        // We only need a 1D array to keep track of the paths
        int[] dp = new int[n];
        dp[0] = 1; // Base case: There is 1 way to be at the starting square
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // RULE 1: If there is an obstacle, kill all paths through this cell
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } 
                // RULE 2: Otherwise, if we aren't stuck on the left wall, sum the paths
                else if (j > 0) {
                    // dp[j] is the path from above, dp[j-1] is the path from the left
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        
        // The last element contains the total paths to the bottom-right corner
        return dp[n - 1];
    }
}