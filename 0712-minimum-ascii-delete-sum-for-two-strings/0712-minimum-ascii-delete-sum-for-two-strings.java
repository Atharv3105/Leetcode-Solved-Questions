class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // dp[i][j] = min ASCII delete sum for s1[0...i-1] and s2[0...j-1]
        int[][] dp = new int[m + 1][n + 1];
        
        // Base case: s2 is empty. We must delete all characters in s1.
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + (int) s1.charAt(i - 1);
        }
        
        // Base case: s1 is empty. We must delete all characters in s2.
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + (int) s2.charAt(j - 1);
        }
        
        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                
                // Characters match: cost is the same as the prefixes before them
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } 
                // Characters mismatch: Take the minimum of deleting from s1 or deleting from s2
                else {
                    int deleteS1 = dp[i - 1][j] + (int) s1.charAt(i - 1);
                    int deleteS2 = dp[i][j - 1] + (int) s2.charAt(j - 1);
                    
                    dp[i][j] = Math.min(deleteS1, deleteS2);
                }
            }
        }
        
        // The bottom-right cell contains the answer for the full strings
        return dp[m][n];
    }
}