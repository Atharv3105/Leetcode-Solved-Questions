class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        // Start backtracking from the number 1
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int k, int remain, int start) {
        // Base case: We have picked exactly 'k' numbers
        if (current.size() == k) {
            // If the sum is perfectly exactly 'n' (meaning remain is 0)
            if (remain == 0) {
                result.add(new ArrayList<>(current));
            }
            return; // Regardless of whether the sum was right or wrong, stop exploring.
        }

        // Iterate through valid numbers from 'start' to 9
        for (int i = start; i <= 9; i++) {
            // PRUNING: If the current number is bigger than the remaining target,
            // we can stop immediately. All numbers after 'i' will also be too big.
            if (remain - i < 0) {
                break;
            }

            // 1. CHOOSE
            current.add(i);
            
            // 2. EXPLORE (move to the next number: i + 1)
            backtrack(result, current, k, remain - i, i + 1);
            
            // 3. UN-CHOOSE (Backtrack)
            current.remove(current.size() - 1);
        }
    }
}