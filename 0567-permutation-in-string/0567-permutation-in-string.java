class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int k = s1.length(), n = s2.length();

        if (k > n)
            return false;

        HashMap<Character, Integer> s1Map = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();

        // Frequency map of s1
        for (char ch : s1.toCharArray())
            s1Map.put(ch, s1Map.getOrDefault(ch, 0) + 1);

        // Prepare initial window of size k-1
        for (int i = 0; i < k - 1; i++) {
            char ch = s2.charAt(i);
            window.put(ch, window.getOrDefault(ch, 0) + 1);
        }

        // Slide the window
        for (int i = k - 1; i < n; i++) {
            // Add new character
            char addChar = s2.charAt(i);
            window.put(addChar, window.getOrDefault(addChar, 0) + 1);

            // Check if window matches s1
            if (window.equals(s1Map))
                return true;

            // Remove leftmost character
            char removeChar = s2.charAt(i - k + 1);
            int freq = window.get(removeChar);
            if (freq > 1)
                window.put(removeChar, freq - 1);
            else
                window.remove(removeChar);
        }
        return false;
    }
}