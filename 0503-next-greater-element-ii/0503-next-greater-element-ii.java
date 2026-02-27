class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // Initialize the result array with -1
        // This handles cases where an element has no next greater element
        Arrays.fill(result, -1);
        
        // Primitive array to act as our stack (storing indices, not values)
        int[] stack = new int[n];
        int top = 0;
        
        // Loop twice to simulate the circular nature of the array
        for (int i = 0; i < n * 2; i++) {
            // Use modulo to wrap around to the start of the array
            int currentIndex = i % n;
            
            // While stack is not empty AND current element is greater than the element at the index stored at stack top
            while (top > 0 && nums[stack[top - 1]] < nums[currentIndex]) {
                // We found the next greater element! Pop the index and record the result.
                result[stack[--top]] = nums[currentIndex];
            }
            
            // We only need to push indices onto the stack during the first pass (i < n).
            // The second pass is purely to resolve remaining items in the stack.
            if (i < n) {
                stack[top++] = currentIndex;
            }
        }
        
        return result;
    }
}