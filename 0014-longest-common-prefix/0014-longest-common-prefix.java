class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        
        // Start by assuming the first string is the prefix
        String prefix = strs[0];
        
        for (int i = 1; i < strs.length; i++) {
            // while the current string does not start with the prefix...
            // (indexOf returns 0 if it is a prefix)
            while (strs[i].indexOf(prefix) != 0) {
                // Shorten the prefix from the end
                prefix = prefix.substring(0, prefix.length() - 1);
                
                // If prefix becomes empty, return immediately
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        
        return prefix;
    }
}