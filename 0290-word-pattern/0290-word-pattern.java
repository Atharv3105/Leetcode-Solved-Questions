import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public boolean wordPattern(String pattern, String s) {
        // Split the string into an array of words
        String[] words = s.split(" ");
        
        // If the lengths don't match, a bijection is mathematically impossible
        if (pattern.length() != words.length) {
            return false;
        }
        
        // Map to track which word a character is assigned to
        Map<Character, String> charToWord = new HashMap<>();
        // Set to track which words have already been claimed by a character
        Set<String> mappedWords = new HashSet<>();
        
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            
            // If we have seen this character before...
            if (charToWord.containsKey(c)) {
                // Check if it's mapped to the SAME word. If not, the pattern is broken.
                if (!charToWord.get(c).equals(w)) {
                    return false;
                }
            } else {
                // We haven't seen this character. 
                // But wait! Is this word already claimed by a DIFFERENT character?
                if (mappedWords.contains(w)) {
                    return false; // A different letter already maps to this word
                }
                
                // If it's a completely new character and an unclaimed word, map them!
                charToWord.put(c, w);
                mappedWords.add(w);
            }
        }
        
        // If we make it through the whole loop without conflicts, it's a match
        return true;
    }
}