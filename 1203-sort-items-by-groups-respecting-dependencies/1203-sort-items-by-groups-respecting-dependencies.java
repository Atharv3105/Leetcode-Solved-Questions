class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 1. Assign unique group IDs to items that have no group (-1)
        int groupId = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = groupId++;
            }
        }
        
        // 2. Initialize graphs and in-degree arrays
        Map<Integer, List<Integer>> itemGraph = new HashMap<>();
        Map<Integer, Integer> itemInDegree = new HashMap<>();
        Map<Integer, List<Integer>> groupGraph = new HashMap<>();
        Map<Integer, Integer> groupInDegree = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            itemGraph.putIfAbsent(i, new ArrayList<>());
            itemInDegree.putIfAbsent(i, 0);
        }
        for (int i = 0; i < groupId; i++) {
            groupGraph.putIfAbsent(i, new ArrayList<>());
            groupInDegree.putIfAbsent(i, 0);
        }
        
        // 3. Build the graphs based on dependencies
        for (int v = 0; v < n; v++) {
            for (int u : beforeItems.get(v)) {
                // Item Dependency: u -> v
                itemGraph.get(u).add(v);
                itemInDegree.put(v, itemInDegree.get(v) + 1);
                
                // Group Dependency: group[u] -> group[v]
                int groupU = group[u];
                int groupV = group[v];
                if (groupU != groupV) {
                    groupGraph.get(groupU).add(groupV);
                    groupInDegree.put(groupV, groupInDegree.get(groupV) + 1);
                }
            }
        }
        
        // 4. Topologically sort both groups and items
        List<Integer> sortedGroups = topoSort(groupGraph, groupInDegree, groupId);
        List<Integer> sortedItems = topoSort(itemGraph, itemInDegree, n);
        
        // If either topological sort fails (cycle detected), return empty array
        if (sortedGroups.isEmpty() || sortedItems.isEmpty()) {
            return new int[0];
        }
        
        // 5. Organize sorted items by their group
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for (int item : sortedItems) {
            groupToItems.computeIfAbsent(group[item], x -> new ArrayList<>()).add(item);
        }
        
        // 6. Construct final answer based on sorted groups
        int[] result = new int[n];
        int index = 0;
        for (int g : sortedGroups) {
            List<Integer> itemsInThisGroup = groupToItems.getOrDefault(g, new ArrayList<>());
            for (int item : itemsInThisGroup) {
                result[index++] = item;
            }
        }
        
        return result;
    }
    
    // Helper function for Topological Sort using Kahn's Algorithm (BFS)
    private List<Integer> topoSort(Map<Integer, List<Integer>> graph, Map<Integer, Integer> inDegree, int count) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        
        // Add all nodes with in-degree 0 to the queue
        for (int key : inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            result.add(curr);
            
            for (int neighbor : graph.getOrDefault(curr, new ArrayList<>())) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        // If we sorted all elements, return result. Otherwise, there's a cycle.
        return result.size() == count ? result : new ArrayList<>();
    }
}