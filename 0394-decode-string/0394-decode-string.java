class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> resStack = new Stack<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Form the full number (e.g., '1', '2' -> 12)
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // Push the multiplier and the string built so far
                countStack.push(k);
                resStack.push(cur);
                // Reset for the new nested content
                cur = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                // Segment complete: pop the multiplier
                int repeatTimes = countStack.pop();
                StringBuilder decodedSegment = cur;
                
                // Get the string context before this bracket started
                cur = resStack.pop();
                
                // Append the current segment k times to the previous context
                while (repeatTimes-- > 0) {
                    cur.append(decodedSegment);
                }
            } else {
                // Normal character, just add to current string
                cur.append(ch);
            }
        }
        return cur.toString();
    }
}