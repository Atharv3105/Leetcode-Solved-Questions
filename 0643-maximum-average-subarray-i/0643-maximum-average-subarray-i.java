class Solution {

    public double findMaxAverage(int[] nums, int k) {

        if (k == 1 && nums.length == 1)
            return nums[0];

        double maxValue = 0.0d;
        double sumValue = 0.0d;

        for (int i = 0; i < nums.length; i++) {

            if (i < k) {
                sumValue = sumValue + nums[i];
                maxValue = sumValue;
            } else {

                sumValue = sumValue + (nums[i]) - (nums[i - k]);
            }
            maxValue = Math.max(sumValue, maxValue);
        }
        return maxValue / k;
    }
}
