class Solution {
    public int openLock(String[] deadends, String target) {
        // Use a HashSet for O(1) lookups. Treat deadends as already "visited".
        Set<String> visited = new HashSet<>(Arrays.asList(deadends));
        
        // Edge cases: start is blocked, or start is already the target
        if (visited.contains("0000")) return -1;
        if (target.equals("0000")) return 0;
        
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        visited.add("0000");
        
        int steps = 0;
        

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            
            // Process all combinations at the current depth level
            for (int i = 0; i < levelSize; i++) {
                String current = queue.poll();
                
                if (current.equals(target)) {
                    return steps;
                }
                
                // Generate all 8 possible next combinations
                for (int j = 0; j < 4; j++) {
                    char c = current.charAt(j);
                    
                    // Move wheel UP (+1)
                    // (c == '9' ? 0 : c - '0' + 1) handles the 9 -> 0 wrap around
                    String up = current.substring(0, j) + 
                                (c == '9' ? 0 : c - '0' + 1) + 
                                current.substring(j + 1);
                                
                    if (!visited.contains(up)) {
                        visited.add(up);
                        queue.offer(up);
                    }
                    
                    // Move wheel DOWN (-1)
                    // (c == '0' ? 9 : c - '0' - 1) handles the 0 -> 9 wrap around
                    String down = current.substring(0, j) + 
                                  (c == '0' ? 9 : c - '0' - 1) + 
                                  current.substring(j + 1);
                                  
                    if (!visited.contains(down)) {
                        visited.add(down);
                        queue.offer(down);
                    }
                }
            }
            // Increment steps after finishing a complete level
            steps++;
        }
        
        // If the queue empties and we haven't found the target
        return -1;
    }
}    