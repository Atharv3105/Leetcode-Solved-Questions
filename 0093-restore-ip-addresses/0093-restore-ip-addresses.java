class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        
        // Initial Pruning: A valid IP has between 4 and 12 characters.
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        
        backtrack(result, new ArrayList<>(), s, 0);
        return result;
    }

    private void backtrack(List<String> result, List<String> current, String s, int start) {
        // Base Case: We have formed 4 segments
        if (current.size() == 4) {
            // If we also used up the entire string, it's a valid IP address!
            if (start == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }

        // Dynamic Pruning: Do we have too many or too few characters left?
        int remainingSegments = 4 - current.size();
        int remainingChars = s.length() - start;
        // E.g., if we need 2 segments, we need between 2 and 6 characters left.
        if (remainingChars < remainingSegments || remainingChars > remainingSegments * 3) {
            return;
        }

        // Try picking 1, 2, or 3 characters for the next segment
        for (int i = 1; i <= 3; i++) {
            // Prevent going out of bounds
            if (start + i > s.length()) {
                break;
            }

            String segment = s.substring(start, start + i);
            
            // Validation 1: No leading zeros (unless the segment is exactly "0")
            if (segment.length() > 1 && segment.startsWith("0")) {
                break; // Break because adding more chars will still result in a leading zero
            }
            
            // Validation 2: The segment value cannot exceed 255
            if (Integer.parseInt(segment) > 255) {
                continue; // Skip this one, maybe a shorter segment worked
            }

            // 1. CHOOSE
            current.add(segment);
            
            // 2. EXPLORE (Move the start pointer forward by 'i' characters)
            backtrack(result, current, s, start + i);
            
            // 3. UN-CHOOSE (Backtrack)
            current.remove(current.size() - 1);
        }
    }
}