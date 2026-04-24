class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // 1. Sort to group duplicates
        Arrays.sort(nums);
        
        // 2. Start backtracking
        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
        
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] nums, boolean[] used) {
        // Base case: permutation is complete
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if the element is already in our current path
            if (used[i]) {
                continue;
            }

            // PRUNING CONDITION: Skip duplicates at the same tree level
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // 1. CHOOSE
            used[i] = true;
            current.add(nums[i]);

            // 2. EXPLORE
            backtrack(result, current, nums, used);

            // 3. UN-CHOOSE (Backtrack)
            used[i] = false;
            current.remove(current.size() - 1);
        }
    }
}