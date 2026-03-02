class Solution {
    public String simplifyPath(String path) {
        // Use Deque as a stack for O(1) add/remove operations
        Deque<String> stack = new ArrayDeque<>();
        int n = path.length();
        
        for (int i = 0; i < n; i++) {
            // Skip multiple slashes
            if (path.charAt(i) == '/') continue;
            
            // Extract the directory or command name
            int start = i;
            while (i < n && path.charAt(i) != '/') {
                i++;
            }
            String part = path.substring(start, i);
            
            if (part.equals("..")) {
                // Go up one level if possible
                if (!stack.isEmpty()) {
                    stack.removeLast();
                }
            } else if (!part.equals(".")) {
                // It's a valid folder name (including "...")
                stack.addLast(part);
            }
        }
        
        // Reconstruct the path using StringBuilder for efficiency
        if (stack.isEmpty()) return "/";
        
        StringBuilder sb = new StringBuilder();
        for (String s : stack) {
            sb.append("/").append(s);
        }
        
        return sb.toString();
    }
}