class Solution {
    public int lastStoneWeightII(int[] stones) {
        int totalSum = 0;
        for (int stone : stones) {
            totalSum += stone;
        }
        
        // Our knapsack capacity is half of the total sum
        int target = totalSum / 2;
        
        // dp[j] represents whether a subset of stones can sum exactly to j
        boolean[] dp = new boolean[target + 1];
        dp[0] = true; // Base case: weight 0 is always possible
        
        // Loop through every stone (these are our "items" for the knapsack)
        for (int stone : stones) {
            // Traverse BACKWARDS to avoid reusing the same stone in the same turn
            for (int j = target; j >= stone; j--) {
                // We can reach weight 'j' if we could already reach it, 
                // OR if we could reach 'j - stone' and we just add this stone to it.
                if (dp[j - stone]) {
                    dp[j] = true;
                }
            }
        }
        
        // Find the maximum weight we were able to pack into our knapsack
        int maxSubsetWeight = 0;
        for (int j = target; j >= 0; j--) {
            if (dp[j]) {
                maxSubsetWeight = j;
                break;
            }
        }
        
        // The remaining weight is Total - Pile A - Pile B
        // Since Pile A is (Total - maxSubsetWeight) and Pile B is (maxSubsetWeight)
        // Result = Total - 2 * maxSubsetWeight
        return totalSum - (2 * maxSubsetWeight);
    }
}