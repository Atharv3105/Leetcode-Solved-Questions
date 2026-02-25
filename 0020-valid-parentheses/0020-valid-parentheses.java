class Solution {
    public boolean isValid(String s) {
        // Early exit: an odd length string can never be perfectly paired
        if (s.length() % 2 != 0) {
            return false;
        }

        // Using a primitive array as a stack for maximum performance
        char[] stack = new char[s.length()];
        int top = 0; 
        
        for (char c : s.toCharArray()) {
            // When we see an opening bracket, push its corresponding closing bracket
            if (c == '(') {
                stack[top++] = ')';
            } else if (c == '{') {
                stack[top++] = '}';
            } else if (c == '[') {
                stack[top++] = ']';
            } else {
                // If it's a closing bracket:
                // 1. Stack is empty (top == 0) -> no matching opening bracket
                // 2. Doesn't match the expected closing bracket we popped
                if (top == 0 || stack[--top] != c) {
                    return false;
                }
            }
        }
        
        // If the stack is empty at the end, all brackets were matched
        return top == 0;
    }
}