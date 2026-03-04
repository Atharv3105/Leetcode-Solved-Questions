public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0, rightRem = 0;

        // Step 1: Calculate the minimum number of ( and ) to remove
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }

        List<String> result = new ArrayList<>();
        dfs(s, 0, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return result;
    }

    private void dfs(String s, int index, int leftCount, int rightCount, 
                     int leftRem, int rightRem, StringBuilder sb, List<String> result) {
        
        // Base Case: Reached the end of the string
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                result.add(sb.toString());
            }
            return;
        }

        char c = s.charAt(index);
        int len = sb.length();

        // Option 1: Remove the current character (if possible)
        if (c == '(' && leftRem > 0) {
            // Optimization: Skip duplicate removals
            if (index == 0 || s.charAt(index - 1) != '(') {
                for (int i = 0; index + i < s.length() && s.charAt(index + i) == '(' && i < leftRem; i++) {
                     dfs(s, index + i + 1, leftCount, rightCount, leftRem - (i + 1), rightRem, sb, result);
                }
            }
        } 
        if (c == ')' && rightRem > 0) {
            // Optimization: Skip duplicate removals
            if (index == 0 || s.charAt(index - 1) != ')') {
                for (int i = 0; index + i < s.length() && s.charAt(index + i) == ')' && i < rightRem; i++) {
                    dfs(s, index + i + 1, leftCount, rightCount, leftRem, rightRem - (i + 1), sb, result);
                }
            }
        }

        // Option 2: Keep the current character
        sb.append(c);
        if (c != '(' && c != ')') {
            dfs(s, index + 1, leftCount, rightCount, leftRem, rightRem, sb, result);
        } else if (c == '(') {
            dfs(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, sb, result);
        } else if (rightCount < leftCount) {
            // Only keep ')' if it doesn't make the prefix invalid
            dfs(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, sb, result);
        }
        sb.setLength(len); // Backtrack
    }
}