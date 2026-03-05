public class Solution {
    // Memoization map to store results of sub-expressions
    Map<String, List<Integer>> memo = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        // If we've already computed this expression, return it
        if (memo.containsKey(expression)) {
            return memo.get(expression);
        }

        List<Integer> res = new ArrayList<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            // Check if the character is an operator
            if (c == '+' || c == '-' || c == '*') {
                // Divide: Split the expression into two parts
                String part1 = expression.substring(0, i);
                String part2 = expression.substring(i + 1);
                
                // Conquer: Recursively solve each part
                List<Integer> leftResults = diffWaysToCompute(part1);
                List<Integer> rightResults = diffWaysToCompute(part2);
                
                // Combine: Merge results using the current operator
                for (int left : leftResults) {
                    for (int right : rightResults) {
                        if (c == '+') res.add(left + right);
                        else if (c == '-') res.add(left - right);
                        else if (c == '*') res.add(left * right);
                    }
                }
            }
        }
        
        // Base Case: If no operators were found, the expression is just a number
        if (res.isEmpty()) {
            res.add(Integer.parseInt(expression));
        }
        
        // Store in memo before returning
        memo.put(expression, res);
        return res;
    }
}