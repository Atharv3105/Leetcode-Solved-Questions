class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // 1. Check if the first row or first column need to be zeroed
        // We handle these separately to simplify the logic for the inner matrix
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // 2. Mark flags in the first row and first column
        // Start from 1 to avoid interfering with the flags we just calculated
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 3. Process the inner matrix using Short-Circuiting
        for (int i = 1; i < m; i++) {
            // OPTIMIZATION: If the row flag is set, zero the WHOLE row instantly.
            // This replaces 'n' conditional checks with a single bulk memory set.
            if (matrix[i][0] == 0) {
                Arrays.fill(matrix[i], 1, n, 0);
            } else {
                // If the row is not marked, we only check columns that are marked.
                for (int j = 1; j < n; j++) {
                    if (matrix[0][j] == 0) {
                        matrix[i][j] = 0;
                    }
                }
            }
        }

        // 4. Handle the first row and first column using our booleans
        if (firstRowZero) {
            Arrays.fill(matrix[0], 0);
        }
        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}