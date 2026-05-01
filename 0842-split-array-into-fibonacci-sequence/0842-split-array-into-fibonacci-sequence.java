class Solution {
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> result = new ArrayList<>();
        backtrack(num, result, 0);
        return result;
    }

    private boolean backtrack(String num, List<Integer> result, int index) {
        // Base case: We reached the end of the string, and have at least 3 numbers
        if (index == num.length() && result.size() >= 3) {
            return true;
        }

        // Try slicing numbers of different lengths starting from 'index'
        for (int i = index; i < num.length(); i++) {
            // Pruning 1: Leading zero check
            if (num.charAt(index) == '0' && i > index) {
                break; // A number cannot start with '0' unless it is exactly "0"
            }

            // Extract the current number using long to easily check for 32-bit overflow
            long currentNum = Long.parseLong(num.substring(index, i + 1));
            
            // Pruning 2: Must fit in a 32-bit signed integer
            if (currentNum > Integer.MAX_VALUE) {
                break; 
            }

            int size = result.size();
            
            // Pruning 3: Fibonacci condition
            if (size >= 2) {
                long expectedSum = (long) result.get(size - 1) + result.get(size - 2);
                
                if (currentNum > expectedSum) {
                    break; // Too big! Adding more digits won't help, so stop exploring this branch
                } else if (currentNum < expectedSum) {
                    continue; // Too small! We need to add more digits to the slice
                }
            }

            // 1. CHOOSE
            result.add((int) currentNum);
            
            // 2. EXPLORE
            // If the recursive call returns true, it means we found the perfect sequence!
            // We pass 'true' all the way up the stack to stop searching.
            if (backtrack(num, result, i + 1)) {
                return true;
            }
            
            // 3. UN-CHOOSE (Backtrack)
            result.remove(result.size() - 1);
        }
        
        return false;
    }
}