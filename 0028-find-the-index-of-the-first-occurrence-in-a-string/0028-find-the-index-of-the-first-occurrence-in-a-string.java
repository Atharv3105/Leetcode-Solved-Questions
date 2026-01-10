class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;  // edge case

        int n = haystack.length();
        int m = needle.length();

        for (int start = 0; start <= n - m; start++) {
            String match = haystack.substring(start, start + m);
            if (match.equals(needle)) {
                return start;
            }
        }
        return -1;
    }
}