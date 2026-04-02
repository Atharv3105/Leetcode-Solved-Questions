class Solution {
    // Array to help navigate north, south, east, west easily
    private int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> result = new ArrayList<>();
        if (heights == null || heights.length == 0 || heights[0].length == 0) {
            return result;
        }

        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pacific = new boolean[m][n];
        boolean[][] atlantic = new boolean[m][n];

        // 1. Traverse left and right borders
        for (int i = 0; i < m; i++) {
            dfs(heights, pacific, i, 0, heights[i][0]);             // Pacific (left edge)
            dfs(heights, atlantic, i, n - 1, heights[i][n - 1]);    // Atlantic (right edge)
        }

        // 2. Traverse top and bottom borders
        for (int j = 0; j < n; j++) {
            dfs(heights, pacific, 0, j, heights[0][j]);             // Pacific (top edge)
            dfs(heights, atlantic, m - 1, j, heights[m - 1][j]);    // Atlantic (bottom edge)
        }

        // 3. Find the intersection of both reachable oceans
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }

        return result;
    }

    private void dfs(int[][] heights, boolean[][] reachable, int r, int c, int prevHeight) {
        // Base cases: out of bounds, already visited, or water can't flow uphill to this cell
        if (r < 0 || r >= heights.length || c < 0 || c >= heights[0].length 
            || reachable[r][c] 
            || heights[r][c] < prevHeight) {
            return;
        }

        // Mark current cell as reachable by the respective ocean
        reachable[r][c] = true;

        // Recursively check all 4 directions
        for (int[] dir : directions) {
            int newRow = r + dir[0];
            int newCol = c + dir[1];
            dfs(heights, reachable, newRow, newCol, heights[r][c]);
        }
    }
}