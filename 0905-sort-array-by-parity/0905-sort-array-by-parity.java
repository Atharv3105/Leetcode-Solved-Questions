class Solution {
    public int[] sortArrayByParity(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            if (nums[left] % 2 > nums[right] % 2) {
                // Swap if left is odd (1) and right is even (0)
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }

            // Move left forward if it's pointing to an even number
            if (nums[left] % 2 == 0) left++;
            // Move right backward if it's pointing to an odd number
            if (nums[right] % 2 != 0) right--;
        }
        return nums;
    }
}