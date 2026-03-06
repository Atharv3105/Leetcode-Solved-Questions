public class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        // Prefix sum array (use long to prevent overflow)
        long[] P = new long[n + 1];
        for (int i = 0; i < n; i++) {
            P[i + 1] = P[i] + nums[i];
        }

        int minLen = n + 1;
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {
            // 1. Check if we found a valid subarray sum >= k
            while (!dq.isEmpty() && P[i] - P[dq.peekFirst()] >= k) {
                minLen = Math.min(minLen, i - dq.pollFirst());
            }

            // 2. Maintain monotonic increasing property of prefix sums in Deque
            while (!dq.isEmpty() && P[i] <= P[dq.peekLast()]) {
                dq.pollLast();
            }

            dq.offerLast(i);
        }

        return minLen <= n ? minLen : -1;
    }
}