class Solution {
    public int rob(int[] nums) {
        // Edge case: if there are no houses, there is nothing to rob
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // Track the maximum money we can rob up to the previous two houses
        int prev2 = 0; // Max money at house i-2
        int prev1 = 0; // Max money at house i-1
        
        // Walk down the street, evaluating each house
        for (int num : nums) {
            // Calculate the max money if we evaluate the current house
            int currentMax = Math.max(prev1, prev2 + num);
            
            // Shift our pointers forward for the next house
            prev2 = prev1;
            prev1 = currentMax;
        }
        
        // By the end of the street, prev1 holds the ultimate maximum
        return prev1;
    }
}