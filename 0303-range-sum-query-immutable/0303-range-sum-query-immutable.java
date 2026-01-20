class NumArray {
    int[] prefixSum; // prefixSum[i] = sum of arr[0:i]

    public NumArray(int[] nums) {
        prefixSum = new int[nums.length + 1];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
    }
    
    public int sumRange(int left, int right) {
        // arr[left : right] = arr[0: right] - arr[0: left - 1]
        // = prefixSum[right] 
        //      - prefixSum[left - 1] if left - 1 >= 0 else 0
        return prefixSum[right] - (
            (left >= 1) ? prefixSum[left - 1] : 0
        );
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(left,right);
 */