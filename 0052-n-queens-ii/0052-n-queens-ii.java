class Solution {
    // Global variable to keep track of valid solutions
    private int count = 0;

    public int totalNQueens(int n) {
        // Tracking arrays for O(1) attack checks
        boolean[] cols = new boolean[n];
        boolean[] posDiag = new boolean[2 * n - 1];
        boolean[] negDiag = new boolean[2 * n - 1];
        
        // Start backtracking from row 0
        backtrack(0, n, cols, posDiag, negDiag);
        
        return count;
    }
    
    private void backtrack(int row, int n, boolean[] cols, boolean[] posDiag, boolean[] negDiag) {
        // Base case: We successfully placed a queen in every row
        if (row == n) {
            count++;
            return;
        }
        
        for (int col = 0; col < n; col++) {
            int pDiag = row + col;
            int nDiag = row - col + (n - 1);
            
            // PRUNING: If the column or diagonals are attacked, skip this cell
            if (cols[col] || posDiag[pDiag] || negDiag[nDiag]) {
                continue;
            }
            
            // 1. CHOOSE (Mark the attack lines)
            cols[col] = true;
            posDiag[pDiag] = true;
            negDiag[nDiag] = true;
            
            // 2. EXPLORE (Move to the next row)
            backtrack(row + 1, n, cols, posDiag, negDiag);
            
            // 3. UN-CHOOSE (Backtrack and clear the attack lines)
            cols[col] = false;
            posDiag[pDiag] = false;
            negDiag[nDiag] = false;
        }
    }
}