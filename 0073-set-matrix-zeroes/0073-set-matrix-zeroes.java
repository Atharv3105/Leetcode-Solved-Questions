class Solution {
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;    // Rows
        int m = matrix[0].length; // Columns
        int col0 = 1;             // Flag to track the status of the first column

        // Step 1: Traverse the matrix and mark the first row & first col
        for (int i = 0; i < n; i++) {
            // Check if the 0th column itself contains any 0
            if (matrix[i][0] == 0) col0 = 0;

            // Check the rest of the row (starting from j=1)
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // Mark the row header
                    matrix[0][j] = 0; // Mark the col header
                }
            }
        }

        // Step 2: Traverse from bottom-up and right-to-left
        // We go backwards to preserve the 'markers' in the first row/col 
        // until we are done processing the inner submatrix.
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 1; j--) {
                // If the row marker OR col marker is 0, set cell to 0
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            // Finally, update the first column for this row based on col0 flag
            if (col0 == 0) {
                matrix[i][0] = 0;
            }
        }
    }
}