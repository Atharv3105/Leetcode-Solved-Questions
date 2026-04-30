class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // Convert list to a HashSet for O(1) lookups
        Set<String> dict = new HashSet<>(wordDict);
        
        // Find the maximum length of a word in the dictionary to optimize our inner loop
        int maxLength = 0;
        for (String word : wordDict) {
            maxLength = Math.max(maxLength, word.length());
        }
        
        // dp[i] represents whether s.substring(0, i) can be segmented
        boolean[] dp = new boolean[s.length() + 1];
        
        // Base case: an empty string is always valid
        dp[0] = true;
        
        // i is the length of the current prefix we are evaluating
        for (int i = 1; i <= s.length(); i++) {
            // j is the split point. We iterate backwards from i - 1.
            // Optimization: We don't need to look back further than the longest word in the dict!
            int limit = Math.max(0, i - maxLength);
            for (int j = i - 1; j >= limit; j--) {
                // If the prefix up to j is valid, AND the remaining substring is in the dictionary
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // We found a valid segmentation for length i, no need to keep checking j
                }
            }
        }
        
        // The answer for the entire string length
        return dp[s.length()];
    }
}