class Solution {
    public boolean isValidSudoku(char[][] board) {
        // Use HashSets to track digits seen in each row, column, and box
        HashSet<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char number = board[i][j];
                
                if (number != '.') {
                    // Create unique strings to represent the number's presence
                    // If add() returns false, it means the string already exists
                    if (!seen.add(number + " in row " + i) ||
                        !seen.add(number + " in col " + j) ||
                        !seen.add(number + " in box " + i/3 + "-" + j/3)) {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}