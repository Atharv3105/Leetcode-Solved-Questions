class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;
        
        // Step 1: Initialize the queue with all rotten oranges and count fresh oranges
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }
        
        // If there are no fresh oranges to begin with, it takes 0 minutes
        if (freshCount == 0) return 0;
        
        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
        
        // Step 2: Spread the rot using BFS
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();
            minutes++; // Advance time by 1 minute
            
            // Process all oranges that rotted in the previous minute
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int r = current[0];
                int c = current[1];
                
                // Check all 4 adjacent directions
                for (int[] dir : directions) {
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];
                    
                    // If the neighbor is within bounds and is fresh
                    if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 2; // Rot the orange
                        freshCount--;             // Decrement fresh count
                        queue.offer(new int[]{newRow, newCol}); // Add to queue for the next minute
                    }
                }
            }
        }
        
        // Step 3: Check if all oranges rotted
        return freshCount == 0 ? minutes : -1;
    }
}