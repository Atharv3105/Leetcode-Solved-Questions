import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        
        // Initialize an empty N x N board
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        
        // Tracking arrays for O(1) attack checks
        boolean[] cols = new boolean[n];
        boolean[] posDiag = new boolean[2 * n - 1];
        boolean[] negDiag = new boolean[2 * n - 1];
        
        // Start backtracking from row 0
        backtrack(result, board, 0, cols, posDiag, negDiag);
        
        return result;
    }
    
    private void backtrack(List<List<String>> result, char[][] board, int row, 
                           boolean[] cols, boolean[] posDiag, boolean[] negDiag) {
        int n = board.length;
        
        // Base case: All queens placed successfully
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }
        
        // Try placing a queen in every column of the current row
        for (int col = 0; col < n; col++) {
            // Calculate diagonal indices
            int pDiag = row + col;
            int nDiag = row - col + (n - 1);
            
            // PRUNING: If the column or diagonals are attacked, skip this cell
            if (cols[col] || posDiag[pDiag] || negDiag[nDiag]) {
                continue;
            }
            
            // 1. CHOOSE
            board[row][col] = 'Q';
            cols[col] = true;
            posDiag[pDiag] = true;
            negDiag[nDiag] = true;
            
            // 2. EXPLORE (move to the next row)
            backtrack(result, board, row + 1, cols, posDiag, negDiag);
            
            // 3. UN-CHOOSE (Backtrack)
            board[row][col] = '.';
            cols[col] = false;
            posDiag[pDiag] = false;
            negDiag[nDiag] = false;
        }
    }
    
    // Helper function to convert the char[][] board into the required List<String> format
    private List<String> constructBoard(char[][] board) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            res.add(new String(board[i]));
        }
        return res;
    }
}