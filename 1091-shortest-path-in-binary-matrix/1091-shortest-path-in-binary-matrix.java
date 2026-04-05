class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        
        // 1. Check if start or end is blocked
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        
        // Array to easily loop through the 8 possible directions
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1}, 
            {0, -1},           {0, 1}, 
            {1, -1},  {1, 0},  {1, 1}
        };
        
        // Queue stores arrays of {row, col, currentDistance}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 1});
        
        // Mark start as visited by changing it to 1
        grid[0][0] = 1;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int distance = current[2];
            
            // 2. Check if we reached the target
            if (row == n - 1 && col == n - 1) {
                return distance;
            }
            
            // 3. Explore all 8 neighbors
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                // If the neighbor is within grid bounds and is an unvisited clear path (0)
                if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 0) {
                    queue.offer(new int[]{newRow, newCol, distance + 1});
                    // Mark as visited immediately upon adding to the queue
                    grid[newRow][newCol] = 1; 
                }
            }
        }
        
        // If the queue empties and we never reached (n-1, n-1)
        return -1;
    }
}