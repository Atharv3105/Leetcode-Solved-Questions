class Solution {
    // Make sure the method name is exactly 'combinationSum2'
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // 1. Sort the array. This is absolutely mandatory for Combination Sum II
        // so we can easily skip duplicates and prune dead branches.
        Arrays.sort(candidates);
        
        // 2. Start the backtracking process
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] candidates, int remain, int start) {
        // Base case: We hit exactly the target sum
        if (remain == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // PRUNING 1: Early termination. If the number is bigger than the remaining target,
            // we can stop immediately because the array is sorted.
            if (remain - candidates[i] < 0) {
                break; 
            }

            // PRUNING 2: Skip duplicates to avoid identical combinations.
            // i > start ensures we only skip duplicates at the SAME level of the tree.
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // 1. CHOOSE
            current.add(candidates[i]);
            
            // 2. EXPLORE
            // CRITICAL: We pass 'i + 1' here (unlike Combination Sum I). 
            // This guarantees we only use each specific element in the array exactly once.
            backtrack(result, current, candidates, remain - candidates[i], i + 1);
            
            // 3. UN-CHOOSE (Backtrack)
            current.remove(current.size() - 1);
        }
    }
}