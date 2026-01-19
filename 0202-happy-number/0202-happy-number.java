class Solution {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = n;

        do {
            slow = sumOfSquares(slow);
            fast = sumOfSquares(sumOfSquares(fast));
        } while (slow != fast);
        return slow == 1;
    }

    private int sumOfSquares(int num) {
        int output = 0;
        while (num > 0) {
            int digit = num % 10;
            output += digit * digit;
            num /= 10;
        }
        return output;
    }
}