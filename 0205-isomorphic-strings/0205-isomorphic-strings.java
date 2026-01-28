class Solution {
    public boolean isIsomorphic(String s, String t) {
        // Arrays to store the last seen index of each character.
        // Size 256 covers all standard ASCII characters.
        int[] indexS = new int[256];
        int[] indexT = new int[256];

        int len = s.length();

        for (int i = 0; i < len; i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            // Check if the last seen indices match
            if (indexS[charS] != indexT[charT]) {
                return false;
            }

            // Update the last seen index.
            // We use (i + 1) because the default value of int array is 0,
            // and 0 is also a valid index. We need to distinguish "never seen" from index 0.
            indexS[charS] = i + 1;
            indexT[charT] = i + 1;
        }

        return true;
    }
}