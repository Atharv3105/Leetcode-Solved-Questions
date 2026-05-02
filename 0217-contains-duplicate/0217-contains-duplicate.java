import java.util.Arrays;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums); // sort once
        int left = 0, right = 1;

        while (right < nums.length) {
            if (nums[left] == nums[right]) {
                return true; // duplicate found
            }
            left++;
            right++;
        }
        return false;
    }
}
