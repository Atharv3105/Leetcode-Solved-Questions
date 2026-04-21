class TrieNode {
    TrieNode[] children;
    boolean isWord;

    public TrieNode() {
        children = new TrieNode[26];
        isWord = false;
    }
}

class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    
    // Standard Trie Insertion
    public void addWord(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            curr = curr.children[index];
        }
        curr.isWord = true;
    }
    
    // Wrapper for the recursive search
    public boolean search(String word) {
        return searchHelper(word, 0, root);
    }
    
    // Recursive DFS to handle the wildcard '.'
    private boolean searchHelper(String word, int index, TrieNode node) {
        // Base case: If we've processed all characters, check if we ended on a valid word
        if (index == word.length()) {
            return node.isWord;
        }
        
        char c = word.charAt(index);
        
        if (c == '.') {
            // Wildcard: Try all possible 26 children
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    // If any path returns true, the whole search returns true
                    if (searchHelper(word, index + 1, node.children[i])) {
                        return true;
                    }
                }
            }
            // If we tried all existing children and none worked, return false
            return false;
        } else {
            // Standard letter: Just follow the specific path
            int childIndex = c - 'a';
            if (node.children[childIndex] == null) {
                return false;
            }
            return searchHelper(word, index + 1, node.children[childIndex]);
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */