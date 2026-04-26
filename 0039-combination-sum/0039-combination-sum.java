class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        
        // 1. Sort the array to enable early loop termination (pruning)
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

        // Loop from the 'start' index to prevent backward duplicates
        for (int i = start; i < candidates.length; i++) {
            // PRUNING: If the current number is bigger than what we have left,
            // we can stop immediately. Because the array is sorted, all 
            // subsequent numbers will also be too big.
            if (remain - candidates[i] < 0) {
                break;
            }

            // 1. CHOOSE
            current.add(candidates[i]);
            
            // 2. EXPLORE
            // CRITICAL: We pass 'i' instead of 'i + 1' because we can reuse the same element
            backtrack(result, current, candidates, remain - candidates[i], i);
            
            // 3. UN-CHOOSE (Backtrack)
            current.remove(current.size() - 1);
        }
    }
}