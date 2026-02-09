class Solution {
    public void wiggleSort(int[] nums) {
int n = nums.length;

        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int mid = (n + 1) / 2;   // end of smaller half
        int i = mid - 1;         // pointer for smaller half
        int j = n - 1;           // pointer for larger half

        for (int k = 0; k < n; k++) {
            if (k % 2 == 0) {
                nums[k] = sorted[i--]; // even index → smaller elements
            } else {
                nums[k] = sorted[j--]; // odd index → larger elements
            }
        }
    }
}