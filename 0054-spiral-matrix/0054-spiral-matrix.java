import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new ArrayList<>();
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        // OPTIMIZATION: Initialize with exact capacity to avoid resizing
        List<Integer> result = new ArrayList<>(rows * cols);
        
        int top = 0, bottom = rows - 1;
        int left = 0, right = cols - 1;
        
        while (top <= bottom && left <= right) {
            
            // 1. Traverse Right
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;
            
            // 2. Traverse Down
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;
            
            // 3. Traverse Left
            if (top <= bottom) {
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }
            
            // 4. Traverse Up
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        
        return result;
    }
}