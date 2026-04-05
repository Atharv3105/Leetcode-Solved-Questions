class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        
        // Quick exit if the target word isn't even in the dictionary
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // Process all words at the current level
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                char[] wordChars = currentWord.toCharArray();
                
                // Try changing each character of the word
                for (int j = 0; j < wordChars.length; j++) {
                    char originalChar = wordChars[j];
                    
                    // Replace with every letter from 'a' to 'z'
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (wordChars[j] == c) continue; // Skip identical letter
                        
                        wordChars[j] = c;
                        String newWord = String.valueOf(wordChars);
                        
                        // If we found the target word, return the next level depth
                        if (newWord.equals(endWord)) {
                            return level + 1;
                        }
                        
                        // If it's a valid dictionary word, add to queue and mark as visited
                        if (wordSet.contains(newWord)) {
                            queue.offer(newWord);
                            wordSet.remove(newWord); // Visited
                        }
                    }
                    // Restore the original character before moving to the next position
                    wordChars[j] = originalChar;
                }
            }
            // Increment level after exploring all possibilities for the current step
            level++;
        }
        
        return 0; // No valid transformation sequence found
    }
}