class MinStack {

    private Stack<Long> stack;
    private long min;

    public MinStack() {
        stack = new Stack<>();
    }
    
    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push((long) val);
            min = val;
        } else {
            if (val < min) {
                stack.push(2L * val - min);
                min = val;
            } else {
                stack.push((long) val);
            }
        }
    }
    
    public void pop() {
        long top = stack.pop();
        
        if (top < min) {
            // Restore previous min
            min = 2 * min - top;
        }
    }
    
    public int top() {
        long top = stack.peek();
        
        if (top < min) {
            return (int) min;
        } else {
            return (int) top;
        }
    }
    
    public int getMin() {
        return (int) min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */