class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. Initialize the adjacency list and in-degree array
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];

        // 2. Build the graph
        // A prerequisite pair [course, prereq] means an edge from prereq -> course
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course);
            inDegree[course]++; // Increment in-degree for the dependent course
        }

        // 3. Find all courses with no prerequisites (in-degree == 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 4. Process the courses using BFS
        int completedCourses = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            completedCourses++;
            
            // For every course that depends on the current one, reduce its dependency count
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                
                // If it has no more prerequisites, we can now take it
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // 5. If we were able to complete all courses, there was no cycle
        return completedCourses == numCourses;
    }
}