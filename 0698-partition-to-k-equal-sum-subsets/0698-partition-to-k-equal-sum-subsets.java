class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        // If the total sum isn't divisible by k, we can't form k equal subsets
        if (sum % k != 0) return false;
        
        int target = sum / k;
        
        // Sort the array in ascending order
        Arrays.sort(nums);
        
        // Reverse the array to make it descending. 
        // Trying to fit the largest elements first drastically prunes the search tree.
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
        
        // If the single largest element is strictly greater than the target, it's impossible
        if (nums[0] > target) return false;
        
        boolean[] used = new boolean[nums.length];
        
        // Start backtracking: (array, used_flags, remaining_k, current_sum, start_index, target)
        return backtrack(nums, used, k, 0, 0, target);
    }
    
    private boolean backtrack(int[] nums, boolean[] used, int k, int currentSum, int start, int target) {
        // Base Case: If we only need to form 1 more subset, the remaining unused 
        // elements are guaranteed to sum to the target.
        if (k == 1) return true;
        
        // If we filled the current subset, start looking for the next subset from index 0
        if (currentSum == target) {
            return backtrack(nums, used, k - 1, 0, 0, target);
        }
        
        for (int i = start; i < nums.length; i++) {
            // Skip already used elements or elements that exceed the target
            if (used[i] || currentSum + nums[i] > target) {
                continue;
            }
            
            // 1. CHOOSE
            used[i] = true;
            
            // 2. EXPLORE
            if (backtrack(nums, used, k, currentSum + nums[i], i + 1, target)) {
                return true;
            }
            
            // 3. UN-CHOOSE (Backtrack)
            used[i] = false;
            
            // --- ADVANCED PRUNING ---
            
            // If the current subset is completely empty, and the backtracking failed after 
            // placing nums[i] as the very first element, then no valid arrangement exists.
            // (Because all empty subsets are mathematically identical, trying this element 
            // as the first item in a "different" empty subset will yield the same failure).
            if (currentSum == 0) return false;
            
            // Skip duplicate elements if the current one failed
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
        }
        
        return false;
    }
}