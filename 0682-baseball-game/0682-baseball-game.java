class Solution {
    public int calPoints(String[] operations) {
        // Primitive array acting as our stack
        int[] stack = new int[operations.length];
        int top = 0;
        int totalSum = 0;
        
        for (String op : operations) {
            if (op.equals("+")) {
                // Sum of the previous two scores
                stack[top] = stack[top - 1] + stack[top - 2];
                totalSum += stack[top];
                top++;
            } else if (op.equals("D")) {
                // Double the previous score
                stack[top] = stack[top - 1] * 2;
                totalSum += stack[top];
                top++;
            } else if (op.equals("C")) {
                // Invalidate the previous score
                top--;
                totalSum -= stack[top];
            } else {
                // Record a new integer score
                stack[top] = Integer.parseInt(op);
                totalSum += stack[top];
                top++;
            }
        }
        
        return totalSum;
    }
}