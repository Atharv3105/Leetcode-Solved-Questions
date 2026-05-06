class Solution {
    public int fib(int n) {
        Integer[] memo = new Integer[31];
        
        return calculateFib(n, memo);
    }
    
    private int calculateFib(int n, Integer[] memo) {
        // 1. Base cases
        if (n == 0) return 0;
        if (n == 1) return 1;
        
        // 2. Check the cache! If we've seen this before, skip the math.
        if (memo[n] != null) {
            return memo[n];
        }
        
        // 3. Calculate the value recursively
        int result = calculateFib(n - 1, memo) + calculateFib(n - 2, memo);
        
        // 4. Store the result in the cache for future use
        memo[n] = result;
        
        return result;
    }
}