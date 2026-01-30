class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // Loop runs until left meets right
        while (left < right) {
            int mid = left + (right - left) / 2;

            // If mid is greater than right, the min is to the right
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } 
            // If mid is less than or equal to right, the min is at mid or to the left
            else {
                right = mid;
            }
        }

        // When left == right, we have found the minimum
        return nums[left];
    }
}