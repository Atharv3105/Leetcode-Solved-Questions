class Solution {
    public int numDecodings(String s) {
        // Edge case: empty string or starting with a '0' makes the whole string invalid
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int n = s.length();
        int[] dp = new int[n + 1];
        
        // Base cases
        dp[0] = 1; 
        dp[1] = 1; 
        
        // i represents the length of the prefix we are checking
        for (int i = 2; i <= n; i++) {
            // Extract the single digit and double digit values
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            
            // If the single digit is valid (1-9), add the ways from 1 step back
            if (oneDigit >= 1) {
                dp[i] += dp[i - 1];
            }
            
            // If the double digit is valid (10-26), add the ways from 2 steps back
            if (twoDigits >= 10 && twoDigits <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        
        // The last element contains the number of ways to decode the full string
        return dp[n];
    }
}