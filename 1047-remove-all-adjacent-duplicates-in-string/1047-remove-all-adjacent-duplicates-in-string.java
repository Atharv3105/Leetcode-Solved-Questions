class Solution {
    public String removeDuplicates(String s) {
        StringBuilder sb = new StringBuilder();
        
        for (char ch : s.toCharArray()) {
            int size = sb.length();
            
            // If the stack (StringBuilder) is not empty 
            // and the last character matches the current one
            if (size > 0 && sb.charAt(size - 1) == ch) {
                // Remove the last character (Pop)
                sb.deleteCharAt(size - 1);
            } else {
                // Otherwise, add the current character (Push)
                sb.append(ch);
            }
        }
        
        return sb.toString();
    }
}