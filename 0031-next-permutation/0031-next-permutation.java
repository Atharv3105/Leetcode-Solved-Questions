class Solution {
    public void nextPermutation(int[] nums) {
        // Step 1: Find the pivot (first element from right that breaks descending order)
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        
        // If i >= 0, a pivot was found. If i == -1, the array is strictly descending.
        if (i >= 0) {
            // Step 2: Find the successor (first element from right greater than pivot)
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // Step 3: Swap pivot and successor
            swap(nums, i, j);
        }
        
        // Step 4: Reverse the subarray to the right of the pivot
        // (If i == -1, this reverses the whole array, satisfying the problem's edge case)
        reverse(nums, i + 1, nums.length - 1);
    }
    
    // Helper method to swap two elements
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    // Helper method to reverse a portion of the array
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}