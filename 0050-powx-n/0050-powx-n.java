class Solution {
    public double myPow(double x, int n) {
        // Cast n to a long to safely handle the Integer.MIN_VALUE edge case
        long N = n;
        
        // Handle negative exponents
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        return fastPow(x, N);
    }
    
    private double fastPow(double x, long n) {
        // Base Case
        if (n == 0) {
            return 1.0;
        }
        
        // 1. DIVIDE: Calculate the power of half the exponent
        double half = fastPow(x, n / 2);
        
        // 2. CONQUER: Combine the results
        if (n % 2 == 0) {
            // Even exponent
            return half * half;
        } else {
            // Odd exponent (needs one extra multiplication of x)
            return half * half * x;
        }
    }
}