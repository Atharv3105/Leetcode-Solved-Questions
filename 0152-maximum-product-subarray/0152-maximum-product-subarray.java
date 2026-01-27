class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int maxProduct = nums[0];
        
        int leftProduct = 0;
        int rightProduct = 0;

        for (int i = 0; i < n; i++) {
            leftProduct = (leftProduct == 0 ? 1 : leftProduct) * nums[i];
            rightProduct = (rightProduct == 0 ? 1 : rightProduct) * nums[n - 1 - i];
            
            maxProduct = Math.max(maxProduct, Math.max(leftProduct, rightProduct));
        }

        return maxProduct;
    }
}