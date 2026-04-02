class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. Initialize adjacency list and in-degree array
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        int[] inDegree = new int[numCourses];

        // 2. Build the graph and populate in-degrees
        // [course, prereq] -> prereq must be completed before course
        for (int[] pre : prerequisites) {
            int course = pre[0];
            int prereq = pre[1];
            adj.get(prereq).add(course);
            inDegree[course]++;
        }

        // 3. Find initial courses with no prerequisites
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 4. Process graph to build the topological order
        int[] order = new int[numCourses];
        int index = 0;
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            order[index++] = current; // Add to our final ordering
            
            // Reduce in-degree for neighbors
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--;
                
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // 5. If index == numCourses, we took all courses (no cycle).
        // Otherwise, a cycle exists, so return an empty array.
        if (index == numCourses) {
            return order;
        } else {
            return new int[0];
        }
    }
}