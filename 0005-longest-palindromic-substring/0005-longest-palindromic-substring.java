class Solution {
    public String longestPalindrome(String s) {
        // Edge case: Empty string or single character
        if (s == null || s.length() < 1) return "";
        
        int start = 0;
        int end = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Check for odd-length palindromes (center is at character i)
            int len1 = expandAroundCenter(s, i, i);
            
            // Check for even-length palindromes (center is between i and i+1)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // Take the maximum length found from this specific center
            int len = Math.max(len1, len2);
            
            // If we found a longer palindrome, update our global start and end pointers
            if (len > end - start) {
                // Math to calculate the exact start and end bounds from the center 'i'
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        
        // Return the longest palindromic substring
        return s.substring(start, end + 1);
    }
    
    // Helper function to expand outwards from a given left and right center
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}