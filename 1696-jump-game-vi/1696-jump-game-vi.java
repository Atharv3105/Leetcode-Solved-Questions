public class Solution {
    public int maxResult(int[] nums, int k) {
        int n = nums.length;
        // dp[i] stores the max score to reach index i
        int[] dp = new int[n];
        dp[0] = nums[0];
        
        // Deque will store indices, maintaining a decreasing order of dp values
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offerLast(0);
        
        for (int i = 1; i < n; i++) {
            // 1. Remove indices that are out of the k-step jump range
            if (!dq.isEmpty() && dq.peekFirst() < i - k) {
                dq.pollFirst();
            }
            
            // 2. The front of the deque has the index with the max dp value in range
            dp[i] = nums[i] + dp[dq.peekFirst()];
            
            // 3. Maintain the monotonic decreasing property
            // Remove indices from the back whose dp values are less than current dp[i]
            while (!dq.isEmpty() && dp[dq.peekLast()] <= dp[i]) {
                dq.pollLast();
            }
            
            dq.offerLast(i);
        }
        
        return dp[n - 1];
    }
}