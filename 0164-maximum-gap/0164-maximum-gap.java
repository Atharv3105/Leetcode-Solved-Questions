class Solution {
    public int maximumGap(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        // Find global min and max
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        if (min == max) return 0; // all elements are same

        // Bucket size and count
        int bucketSize = Math.max(1, (max - min) / (n - 1));
        int bucketCount = ((max - min) / bucketSize) + 1;

        // Initialize buckets
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        boolean[] bucketUsed = new boolean[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            bucketMin[i] = Integer.MAX_VALUE;
            bucketMax[i] = Integer.MIN_VALUE;
        }

        // Distribute numbers into buckets
        for (int num : nums) {
            int idx = (num - min) / bucketSize;
            bucketMin[idx] = Math.min(bucketMin[idx], num);
            bucketMax[idx] = Math.max(bucketMax[idx], num);
            bucketUsed[idx] = true;
        }

        // Scan buckets to find max gap
        int prevMax = min;
        int maxGap = 0;

        for (int i = 0; i < bucketCount; i++) {
            if (!bucketUsed[i]) continue;
            maxGap = Math.max(maxGap, bucketMin[i] - prevMax);
            prevMax = bucketMax[i];
        }

        return maxGap;
    }
}