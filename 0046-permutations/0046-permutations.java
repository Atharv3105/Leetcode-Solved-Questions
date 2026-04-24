class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // We pass a boolean array to track which elements are active in the current path
        backtrack(result, new ArrayList<>(), nums, new boolean[nums.length]);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentPermutation, int[] nums, boolean[] used) {
        // Base case: We've used all numbers, so we have a valid permutation
        if (currentPermutation.size() == nums.length) {
            result.add(new ArrayList<>(currentPermutation));
            return;
        }

        // Always start from 0 to consider all available numbers for the next position
        for (int i = 0; i < nums.length; i++) {
            // If the number is already in our current permutation, skip it
            if (used[i]) {
                continue;
            }

            // 1. CHOOSE
            used[i] = true;
            currentPermutation.add(nums[i]);

            // 2. EXPLORE
            backtrack(result, currentPermutation, nums, used);

            // 3. UN-CHOOSE (Backtrack)
            used[i] = false;
            currentPermutation.remove(currentPermutation.size() - 1);
        }
    }
}