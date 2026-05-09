class Solution {
    public boolean isPerfectSquare(int num) {
        // We use a long to prevent integer overflow when subtracting
        long currentNum = num;
        long oddNumber = 1;
        
        // Continuously subtract the next odd number
        while (currentNum > 0) {
            currentNum -= oddNumber;
            oddNumber += 2; // Step to the next odd number
        }
        
        // If it lands perfectly on 0, it's a perfect square!
        return currentNum == 0;
    }
}