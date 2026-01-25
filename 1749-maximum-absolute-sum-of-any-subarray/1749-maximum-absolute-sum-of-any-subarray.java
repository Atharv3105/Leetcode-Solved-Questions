class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int maxSoFar = 0;
        int minSoFar = 0;
        int currentMax = 0;
        int currentMin = 0;

        for (int x : nums) {
            currentMax += x;
            maxSoFar = Math.max(maxSoFar, currentMax);
            if (currentMax < 0) currentMax = 0;

            currentMin += x;
            minSoFar = Math.min(minSoFar, currentMin);
            if (currentMin > 0) currentMin = 0;
        }

        return Math.max(maxSoFar, Math.abs(minSoFar));
    }
}