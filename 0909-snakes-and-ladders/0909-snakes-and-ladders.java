class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        
        boolean[] visited = new boolean[target + 1];
        visited[1] = true;
        
        int moves = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all squares at the current BFS level
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                
                // If we reached the end, return the number of moves
                if (curr == target) {
                    return moves;
                }
                
                // Roll the die (1 to 6)
                for (int next = curr + 1; next <= Math.min(curr + 6, target); next++) {
                    int[] pos = getCoordinates(next, n);
                    int r = pos[0];
                    int c = pos[1];
                    
                    // If there's a snake/ladder, the destination changes
                    int destination = board[r][c] != -1 ? board[r][c] : next;
                    
                    // If we haven't visited this destination yet, add it to the queue
                    if (!visited[destination]) {
                        visited[destination] = true;
                        queue.offer(destination);
                    }
                }
            }
            // Increment moves after finishing a full round of die rolls
            moves++;
        }
        
        return -1; // Target is unreachable
    }
    
    // Helper function to map a 1D square number to 2D board coordinates
    private int[] getCoordinates(int square, int n) {
        int zeroIndexed = square - 1;
        int rowFromBottom = zeroIndexed / n;
        
        int r = n - 1 - rowFromBottom;
        int c = zeroIndexed % n;
        
        // If the row from bottom is odd, the column numbering is reversed
        if (rowFromBottom % 2 == 1) {
            c = n - 1 - c;
        }
        
        return new int[]{r, c};
    }
}