class Solution {
    // 1. Setup the mapping. Index 2 maps to "abc", Index 3 to "def", etc.
    // Indices 0 and 1 are empty since they don't have letters on a standard phone.
    private static final String[] KEYPAD = {
        "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        // Edge case: If the input is empty, return the empty list immediately
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        // Start the backtracking process
        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // Base case: If our current string is the same length as the digits string,
        // we have a complete combination.
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get the letters that the current digit represents
        int digit = digits.charAt(index) - '0'; // Convert char to int
        String letters = KEYPAD[digit];

        // Loop through all possible letters for this digit
        for (char c : letters.toCharArray()) {
            // 1. CHOOSE
            current.append(c);
            
            // 2. EXPLORE (move to the next digit)
            backtrack(result, current, digits, index + 1);
            
            // 3. UN-CHOOSE (Backtrack to try the next letter)
            current.deleteCharAt(current.length() - 1);
        }
    }
}