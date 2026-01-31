class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // Loop through layers (n / 2)
        // Loop through elements in the current layer
        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                
                // Save top-left
                int temp = matrix[i][j];
                
                // Move bottom-left to top-left
                matrix[i][j] = matrix[n - 1 - j][i];
                
                // Move bottom-right to bottom-left
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                
                // Move top-right to bottom-right
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                
                // Move top-left (saved in temp) to top-right
                matrix[j][n - 1 - i] = temp;
            }
        }
    }
}