class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        // Deque will store indices of dp array
        Deque<Integer> deque = new ArrayDeque<>();
        int maxSum = nums[0];

        for (int i = 0; i < n; i++) {
            // 1. Remove indices that are out of the k-range window
            if (!deque.isEmpty() && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            // 2. Calculate dp[i]. If max dp in window is negative, just take nums[i]
            int maxInWindow = deque.isEmpty() ? 0 : Math.max(0, dp[deque.peekFirst()]);
            dp[i] = nums[i] + maxInWindow;
            maxSum = Math.max(maxSum, dp[i]);

            // 3. Maintain the deque in decreasing order (Monotonic Deque)
            // Remove elements from back that are smaller than current dp[i]
            while (!deque.isEmpty() && dp[deque.peekLast()] <= dp[i]) {
                deque.pollLast();
            }
            
            deque.offerLast(i);
        }

        return maxSum;
    }
}