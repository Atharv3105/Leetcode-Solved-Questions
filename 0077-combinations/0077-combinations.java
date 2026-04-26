class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        // Start picking numbers from 1
        backtrack(result, new ArrayList<>(), n, k, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int n, int k, int start) {
        // Base case: If we've picked 'k' numbers, we have a valid combination
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // PRUNING: Only loop as long as there are enough elements left to reach length k.
        // E.g., if n=4, k=3, and current is empty, we only need to loop i up to 2. 
        // Starting at 3 would only leave us with [3, 4], failing to reach length 3.
        int limit = n - (k - current.size()) + 1;
        
        for (int i = start; i <= limit; i++) {
            // 1. CHOOSE
            current.add(i);
            
            // 2. EXPLORE (move to the next strictly larger number)
            backtrack(result, current, n, k, i + 1);
            
            // 3. UN-CHOOSE (Backtrack)
            current.remove(current.size() - 1);
        }
    }
}