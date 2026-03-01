class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1; // 1 represents '+', -1 represents '-'

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                // Handle multi-digit numbers: "123" -> 1*100 + 2*10 + 3
                number = 10 * number + (c - '0');
                
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
                
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
                
            } else if (c == '(') {
                // We hit a nested expression. 
                // Push the current result and sign to the stack to save 'context'.
                stack.push(result);
                stack.push(sign);
                
                // Reset for the new expression inside the parentheses
                result = 0;
                sign = 1;
                
            } else if (c == ')') {
                // Finish the last number inside the parentheses
                result += sign * number;
                number = 0;
                
                // Stack top is the sign before '(', second top is the result before '('
                result *= stack.pop(); // Apply the sign to the entire inner result
                result += stack.pop(); // Add to the previous accumulated result
            }
        }

        // Add the final number if the string doesn't end with ')'
        if (number != 0) {
            result += sign * number;
        }

        return result;
    }
}