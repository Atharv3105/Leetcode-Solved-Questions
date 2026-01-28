class Solution {
    public String reverseWords(String s) {
        // Use StringBuilder for efficient modification
        StringBuilder result = new StringBuilder();
        
        // Start from the end of the string
        int i = s.length() - 1;
        
        while (i >= 0) {
            // 1. Skip trailing spaces or multiple spaces between words
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            
            // If we went past the start of the string, break
            if (i < 0) break;
            
            // 2. Mark the end of the word
            int right = i;
            
            // 3. Move 'i' back to find the start of the word
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            
            // 4. Extract the word
            // substring is (start, end) exclusive, so we use i + 1
            String word = s.substring(i + 1, right + 1);
            
            // 5. Append to result
            if (result.length() == 0) {
                result.append(word);
            } else {
                // Add a space before appending the next word
                result.append(" ");
                result.append(word);
            }
        }
        
        return result.toString();
    }
}