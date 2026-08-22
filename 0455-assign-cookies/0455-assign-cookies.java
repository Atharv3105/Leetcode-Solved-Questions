class Solution {
    public int findContentChildren(int[] g, int[] s) {
        // Sort greed factors and cookie sizes
        Arrays.sort(g);
        Arrays.sort(s);

        int child = 0;  // pointer for children
        int cookie = 0; // pointer for cookies

        // Try to assign cookies
        while (child < g.length && cookie < s.length) {
            if (s[cookie] >= g[child]) {
                // Cookie satisfies this child
                child++;
            }
            // Move to next cookie regardless
            cookie++;
        }

        return child; 
    }    
}