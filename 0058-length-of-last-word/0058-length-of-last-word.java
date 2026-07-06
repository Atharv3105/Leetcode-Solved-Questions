class Solution {
    public int lengthOfLastWord(String s) {
        int n = s.length();
        int i = n - 1;

        // Step 1: Skip trailing spaces
        while(i>=0 && s.charAt(i)==' '){
            i--;
        }

        // Step 2: Count the length of the last word
        int length = 0;
        while(i>=0 && s.charAt(i) != ' '){
            length++;
            i--;
        }
        return length;
    }
}
