class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        
        // Edge Cases: If there is only 1 house, rob it. If 2, rob the richer one.
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);
        
        // Universe A: Evaluate houses from index 0 to n-2 (skip the last house)
        int maxA = robHelper(nums, 0, n - 2);
        
        // Universe B: Evaluate houses from index 1 to n-1 (skip the first house)
        int maxB = robHelper(nums, 1, n - 1);
        
        // Return the absolute maximum between the two universes
        return Math.max(maxA, maxB);
    }
    
    // This is the exact same 1D Space-Optimized DP from House Robber I
    private int robHelper(int[] nums, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;
        
        for (int i = start; i <= end; i++) {
            int currentMax = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = currentMax;
        }
        
        return prev1;
    }
}