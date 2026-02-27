class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        if (n == 0) return 0;

        // Manual stack to store indices
        int[] stack = new int[n + 1];
        int top = -1;
        int maxArea = 0;

        // We iterate up to n to handle the "cleanup" of the stack
        for (int i = 0; i <= n; i++) {
            // Treat the (n)th element as 0 height to force all remaining bars out
            int currentHeight = (i == n) ? 0 : heights[i];

            // While the current bar is shorter than the bar at the top of our stack
            while (top != -1 && currentHeight < heights[stack[top]]) {
                // The height of the rectangle is the bar we are popping
                int height = heights[stack[top--]];
                
                // The width is the distance between the current index 'i' 
                // and the index of the bar now at the top of the stack
                int width = (top == -1) ? i : i - stack[top] - 1;
                
                maxArea = Math.max(maxArea, height * width);
            }
            
            // Push the current index onto the stack
            stack[++top] = i;
        }

        return maxArea;
    }
}