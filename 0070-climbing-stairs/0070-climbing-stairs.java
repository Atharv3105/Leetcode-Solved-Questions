class Solution {
    public int climbStairs(int n) {
        // Base cases for small stairs
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        // We only need to track the last two states, not the whole array
        int prev2 = 1; // ways to reach step 1
        int prev1 = 2; // ways to reach step 2
        
        // Start climbing from step 3 up to n
        for (int i = 3; i <= n; i++) {
            // The ways to get here is the sum of the ways to reach the two steps below
            int current = prev1 + prev2;
            
            // Shift our pointers forward for the next iteration
            prev2 = prev1;
            prev1 = current;
        }
        
        // prev1 holds the final result for step n
        return prev1;
    }
}