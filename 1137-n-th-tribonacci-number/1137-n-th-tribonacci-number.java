class Solution {
    public int tribonacci(int n) {
        // Base cases
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;
        
        // Initialize our 3-variable memory window
        int a = 0; 
        int b = 1; 
        int c = 1;         
        // Loop from 3 up to n to calculate the sequence
        for (int i = 3; i <= n; i++) {
            // Calculate the current Tribonacci number
            int next = a + b + c;
            
            // Shift our window one step to the right
            a = b;
            b = c;
            c = next;
        }
        // 'c' now holds the value for T_n
        return c;
    }
}