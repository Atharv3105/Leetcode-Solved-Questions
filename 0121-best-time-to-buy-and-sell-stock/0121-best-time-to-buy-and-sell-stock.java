class Solution {
    public int maxProfit(int[] prices) {
        // Initialize minPrice to the highest possible value
        int minPrice = Integer.MAX_VALUE;
        // Initialize maxProfit to 0
        int maxProfit = 0;
        
        for (int i = 0; i < prices.length; i++) {
            // Update minPrice if the current price is lower
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } 
            // Calculate potential profit and update maxProfit if it's higher
            else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        
        return maxProfit;
    }
}