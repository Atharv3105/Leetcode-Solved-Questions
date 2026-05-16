class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // dp[i][j] = min operations to convert word1[0...i-1] to word2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Base case: word2 is empty (we must delete all characters of word1)
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        // Base case: word1 is empty (we must insert all characters of word2)
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                
                // Characters match: cost is the same as the prefixes before them
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                // Characters mismatch: 1 + minimum of Insert, Delete, or Replace
                else {
                    int insert  = dp[i][j - 1];
                    int delete  = dp[i - 1][j];
                    int replace = dp[i - 1][j - 1];
                    
                    dp[i][j] = 1 + Math.min(replace, Math.min(insert, delete));
                }
            }
        }
        
        // The bottom-right cell contains the answer for the full strings
        return dp[m][n];
    }
}