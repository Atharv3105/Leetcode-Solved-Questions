class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // no carry, done
            }
            digits[i] = 0; // set to 0 and continue carry
        }

        // If we reach here, all digits were 9
        int[] res = new int[n + 1];
        res[0] = 1; // e.g. 999 -> 1000
        return res;
    }
}
