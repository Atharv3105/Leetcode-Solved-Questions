class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // 1. Sort the array to group duplicates together
        Arrays.sort(nums);
        
        // 2. Start the backtracking process
        backtrack(result, new ArrayList<>(), nums, 0);
        
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentSubset, int[] nums, int start) {
        // Add a copy of the current subset to the result
        result.add(new ArrayList<>(currentSubset));

        for (int i = start; i < nums.length; i++) {
            // PRUNING STEP: Skip duplicates to avoid identical subsets
            // i > start ensures we only skip duplicates at the SAME level of the tree
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            // CHOOSE
            currentSubset.add(nums[i]);
            
            // EXPLORE (move to the next index)
            backtrack(result, currentSubset, nums, i + 1);
            
            // UN-CHOOSE (Backtrack)
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}