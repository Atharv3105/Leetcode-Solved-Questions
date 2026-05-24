class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // dp[i][j] will store the length of the Longest Common Subsequence (LCS)
        int[][] dp = new int[m + 1][n + 1];
        
        // Build the DP table bottom-up
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                
                // If characters match, the LCS grows by 1
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } 
                // If they don't match, carry forward the best previous LCS
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        // The bottom-right cell holds the final length of the LCS
        int lcs = dp[m][n];
        
        // Calculate the minimum deletions required
        return (m + n) - (2 * lcs);
    }
}