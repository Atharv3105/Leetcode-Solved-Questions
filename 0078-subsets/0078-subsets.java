class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // Start the backtracking process with an empty subset at index 0
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentSubset, int[] nums, int start) {
        // Add a copy of the current subset to our final result list
        result.add(new ArrayList<>(currentSubset));

        // Iterate through the remaining numbers
        for (int i = start; i < nums.length; i++) {
            // 1. CHOOSE: Add the current number to our subset
            currentSubset.add(nums[i]);
            
            // 2. EXPLORE: Move forward to the next index
            backtrack(result, currentSubset, nums, i + 1);
            
            // 3. UN-CHOOSE (Backtrack): Remove the last added number to explore other combinations
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}