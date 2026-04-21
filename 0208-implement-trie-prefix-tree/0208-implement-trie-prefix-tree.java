class TrieNode {
    // Array to hold links to child nodes (26 lowercase letters)
    TrieNode[] children;
    // Flag to denote if a complete word ends at this node
    boolean isWord;

    public TrieNode() {
        children = new TrieNode[26];
        isWord = false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        // The root node itself doesn't hold a character, it just holds the links
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a'; // Map 'a'-'z' to 0-25
            
            // If the child node for this character doesn't exist, create it
            if (curr.children[index] == null) {
                curr.children[index] = new TrieNode();
            }
            // Move down the tree
            curr = curr.children[index];
        }
        // Mark the end of the word
        curr.isWord = true;
    }
    
    public boolean search(String word) {
        TrieNode node = findNode(word);
        // Word exists only if the node is found AND it's marked as the end of a word
        return node != null && node.isWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode node = findNode(prefix);
        // Prefix exists as long as we can traverse the path without hitting null
        return node != null;
    }
    
    // Helper function used by both search and startsWith to traverse the tree
    private TrieNode findNode(String str) {
        TrieNode curr = root;
        for (char c : str.toCharArray()) {
            int index = c - 'a';
            if (curr.children[index] == null) {
                return null; // Path breaks, string doesn't exist
            }
            curr = curr.children[index];
        }
        return curr;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */