class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;

        // 1. Fill lesser elements from the start
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < pivot) {
                result[left] = nums[i];
                left++;
            }
        }

        // 2. Fill greater elements from the end (backwards to find position)
        // Count how many "equals" we need to skip
        int equalCount = 0;
        for (int x : nums) {
            if (x == pivot) equalCount++;
        }
        
        int greaterStart = left + equalCount;
        
        // 3. Fill the array again
        int equalIndex = left;
        
        for (int num : nums) {
            if (num < pivot) {

            } else if (num == pivot) {
                result[equalIndex++] = num;
            } else {
                result[greaterStart++] = num;
            }
        }
        
        return result;
    }
}