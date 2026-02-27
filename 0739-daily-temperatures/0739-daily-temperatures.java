class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        
        // Primitive array to act as our stack. 
        // We will store the INDICES of the temperatures here, not the values.
        int[] stack = new int[n];
        int top = 0; 
        
        for (int i = 0; i < n; i++) {
            int currentTemp = temperatures[i];
            
            // If the stack is not empty and the current temperature is warmer 
            // than the temperature at the index stored at the top of the stack...
            while (top > 0 && currentTemp > temperatures[stack[top - 1]]) {
                // We found a warmer day! Pop the previous day's index.
                int prevIndex = stack[--top];
                
                // The wait time is the difference between the current index and the previous index.
                result[prevIndex] = i - prevIndex;
            }
            
            // Push the current day's index onto the stack to wait for a warmer day.
            stack[top++] = i;
        }
        
        // Note: Any indices left in the stack will automatically correspond to 0 in the 
        // result array since `new int[n]` initializes all elements to 0 by default.
        return result;
    }
}