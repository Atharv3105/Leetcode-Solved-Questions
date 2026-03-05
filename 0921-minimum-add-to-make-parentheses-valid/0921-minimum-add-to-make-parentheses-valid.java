public class Solution {
    public int minAddToMakeValid(String s) {
        int openNeeded = 0;
        int moves = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // We have an opening bracket, increment potential matches
                openNeeded++;
            } else {
                // We have a closing bracket ')'
                if (openNeeded > 0) {
                    // It matches a previous opening bracket
                    openNeeded--;
                } else {
                    // No opening bracket to match, we MUST add one '('
                    moves++;
                }
            }
        }

        // Total moves = unmatched ')' (moves) + unmatched '(' (openNeeded)
        return moves + openNeeded;
    }
}