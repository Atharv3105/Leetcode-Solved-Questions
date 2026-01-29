class Solution {
    public boolean isAnagram(String s, String t) {
        // Step 1: Optimization - check lengths first
        if (s.length() != t.length()) {
            return false;
        }
        
        // Step 2: Frequency bucket for 'a'-'z'
        int[] counter = new int[26];
        
        // Step 3: Increment for s, Decrement for t
        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i) - 'a']++;
            counter[t.charAt(i) - 'a']--;
        }
        
        // Step 4: Check for any non-zero values
        for (int count : counter) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
}