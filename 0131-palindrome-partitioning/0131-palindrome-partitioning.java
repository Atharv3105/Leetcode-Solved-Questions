class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        // Start backtracking from index 0
        backtrack(result, new ArrayList<>(), s, 0);
        return result;
    }

    private void backtrack(List<List<String>> result, List<String> current, String s, int start) {
        // Base case: We've successfully reached the end of the string
        if (start == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try every possible ending position for the current substring
        for (int end = start; end < s.length(); end++) {
            // PRUNING: Only branch if the current slice is a palindrome
            if (isPalindrome(s, start, end)) {
                // 1. CHOOSE the palindromic substring
                current.add(s.substring(start, end + 1));
                
                // 2. EXPLORE the rest of the string
                backtrack(result, current, s, end + 1);
                
                // 3. UN-CHOOSE (Backtrack)
                current.remove(current.size() - 1);
            }
        }
    }

    // Standard 2-pointer palindrome check
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}