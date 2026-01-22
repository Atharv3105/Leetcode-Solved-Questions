import java.util.Arrays;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int k = s1.length();
        int n = s2.length();

        if (k > n) return false;

        int[] s1Count = new int[26];
        int[] windowCount = new int[26];

        for (int i = 0; i < k; i++) {
            s1Count[s1.charAt(i) - 'a']++;
            windowCount[s2.charAt(i) - 'a']++;
        }

        if (Arrays.equals(s1Count, windowCount)) return true;

        for (int i = k; i < n; i++) {
            windowCount[s2.charAt(i) - 'a']++;
            
            windowCount[s2.charAt(i - k) - 'a']--;

            if (Arrays.equals(s1Count, windowCount)) return true;
        }

        return false;
    }
}