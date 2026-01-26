class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int totalSum = 0;
        
        // standard Kadane's for max subarray
        int currMax = 0;
        int maxSum = nums[0];
        
        // Inverse Kadane's for min subarray
        int currMin = 0;
        int minSum = nums[0];

        for (int num : nums) {
            // 1. Calculate Total Sum
            totalSum += num;
            
            // 2. Standard Kadane's (Find Max Linear Subarray)
            currMax = Math.max(currMax + num, num);
            maxSum = Math.max(maxSum, currMax);
            
            // 3. Inverse Kadane's (Find Min Linear Subarray)
            currMin = Math.min(currMin + num, num);
            minSum = Math.min(minSum, currMin);
        }
        // Edge Case: If all numbers are negative, maxSum will be negative.
        // In this case, 'totalSum - minSum' would be 0 (empty subarray), which isn't allowed.
        // So we just return the maxSum (the largest single negative number).
        if (maxSum < 0) {
            return maxSum;
        }
        // Return the best of the two cases:
        // Case 1: The max subarray is in the middle (maxSum)
        // Case 2: The max subarray wraps around (totalSum - minSum)
        return Math.max(maxSum, totalSum - minSum);
    }
}