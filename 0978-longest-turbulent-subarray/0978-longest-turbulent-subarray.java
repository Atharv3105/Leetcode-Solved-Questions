class Solution {
    public int maxTurbulenceSize(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 1;
        }
        // Initialize lengths. 
        // A single element is a turbulent subarray of length 1.
        int up = 1;
        int down = 1;
        int maxLen = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                // Current is 'up', so it extends a previous 'down'
                up = down + 1;
                down = 1;
            } else if (arr[i] < arr[i - 1]) {
                // Current is 'down', so it extends a previous 'up'
                down = up + 1;
                up = 1;
            } else {
                // Elements are equal, reset sequence
                up = 1;
                down = 1;
            }
            
            // Update the global maximum
            maxLen = Math.max(maxLen, Math.max(up, down));
        }
        return maxLen;
    }
}