import java.util.Arrays;

class Solution {
    public int coinChange(int[] coins, int amount) {
        // dp[i] will store the minimum number of coins needed for amount i
        int[] dp = new int[amount + 1];
        
        // Fill the array with a "max" value to represent unreachable amounts.
        // We use (amount + 1) because the max possible coins is 'amount' (all 1s).
        // Using Integer.MAX_VALUE can cause integer overflow when we add 1 to it.
        int max = amount + 1;
        Arrays.fill(dp, max);
        
        // Base case: 0 coins are needed to make amount 0
        dp[0] = 0;
        
        // Build up the DP array from 1 to the target amount
        for (int i = 1; i <= amount; i++) {
            // Try every coin denomination
            for (int coin : coins) {
                // If the coin is not larger than the current amount we are trying to make
                if (i - coin >= 0) {
                    // Update dp[i] to be the minimum of what we already have, 
                    // or 1 (the current coin) + the optimal solution for the remaining amount
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        // If dp[amount] is still our initialized 'max', it means we couldn't make the amount
        return dp[amount] > amount ? -1 : dp[amount];
    }
}