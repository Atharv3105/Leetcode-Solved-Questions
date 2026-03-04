public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        // Use StringBuilder for better performance over String concatenation
        backtrack(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, int open, int close, int max) {
        // Base case: If the current string length is n * 2, we found a valid combination
        if (current.length() == max * 2) {
            result.add(current.toString());
            return;
        }

        // Rule 1: We can add an opening bracket if we haven't reached the limit 'n'
        if (open < max) {
            current.append("(");
            backtrack(result, current, open + 1, close, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }

        // Rule 2: We can add a closing bracket if it won't exceed the number of open brackets
        if (close < open) {
            current.append(")");
            backtrack(result, current, open, close + 1, max);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }
}