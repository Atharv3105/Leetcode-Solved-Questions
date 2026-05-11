class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        
        // Base cases: We can start at step 0 or step 1 for free.
        int prev2 = 0; // Minimum cost to reach step i-2
        int prev1 = 0; // Minimum cost to reach step i-1
        
        // We iterate up to 'n' (inclusive) because 'n' represents the top of the floor,
        // which is exactly one step past the last element in the cost array.
        for (int i = 2; i <= n; i++) {
            // Option 1: Jump 1 step from i-1 (Cost to reach i-1 + cost to leave i-1)
            int jumpOneStep = prev1 + cost[i - 1];
            
            // Option 2: Jump 2 steps from i-2 (Cost to reach i-2 + cost to leave i-2)
            int jumpTwoSteps = prev2 + cost[i - 2];
            
            // The cost to reach the current step is the cheaper of the two options
            int current = Math.min(jumpOneStep, jumpTwoSteps);
            
            // Shift our memory window forward for the next iteration
            prev2 = prev1;
            prev1 = current;
        }
        
        // By the time the loop finishes, prev1 holds the cheapest cost to reach the top
        return prev1;
    }
}