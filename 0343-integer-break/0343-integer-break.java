class Solution {
    public int integerBreak(int n) {
        // The problem requires at least one break (k >= 2)
        // If n is 2 or 3, we are FORCED to break off a 1, which lowers the product.
        if (n == 2) return 1; // 1 + 1
        if (n == 3) return 2; // 2 + 1
        
        // Count how many 3s we can extract
        int numberOf3s = n / 3;
        int remainder = n % 3;
        
        // If the remainder is 1, it's better to combine it with one of the 3s
        // to make a 4 (because 3 * 1 < 4).
        if (remainder == 1) {
            numberOf3s--;
            remainder = 4;
        } 
        // If there's no remainder, we just multiply by 1 at the end to keep the product unchanged
        else if (remainder == 0) {
            remainder = 1;
        }
        
        // Multiply all the 3s together, then multiply by the leftover remainder (2 or 4)
        return (int) Math.pow(3, numberOf3s) * remainder;
    }
}