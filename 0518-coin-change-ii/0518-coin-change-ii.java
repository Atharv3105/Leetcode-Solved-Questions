class Solution {
    public int change(int amount, int[] coins) {
        // dp[i] represents the number of combinations to make up amount i
        int[] dp = new int[amount + 1];
        
        // Base case: There is 1 way to make amount 0 (use zero coins)
        dp[0] = 1;
        
        // LOOP 1: Iterate through the coins FIRST to ensure combinations, not permutations
        for (int coin : coins) {
            // LOOP 2: Iterate through all amounts from the coin's value up to the target amount
            // We start at 'coin' because we can't use a coin to make an amount smaller than itself
            for (int i = coin; i <= amount; i++) {
                // The ways to make amount 'i' increases by the ways we can make 'i - coin'
                dp[i] = dp[i] + dp[i - coin];
            }
        }
        
        // The final index holds the total ways to make the target amount
        return dp[amount];
    }
}