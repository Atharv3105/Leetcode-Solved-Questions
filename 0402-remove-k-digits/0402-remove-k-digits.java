class Solution {
    public String removeKdigits(String num, int k) {
        int n = num.length();
        if (k == n) return "0";
        
        // Use a Deque as a stack for O(1) removals and additions
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char digit : num.toCharArray()) {
            // While the current digit is smaller than the previous one,
            // remove the larger previous digit to minimize the number.
            while (k > 0 && !stack.isEmpty() && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }
        
        // If we still need to remove digits, remove from the end (the largest)
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        // Build the string and handle leading zeros
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast()); // Get from bottom of stack (left to right)
        }
        
        // Remove leading zeros
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
}