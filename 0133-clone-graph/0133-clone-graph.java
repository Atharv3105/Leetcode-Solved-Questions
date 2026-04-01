/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // Map to keep track of visited nodes and their clones
    private HashMap<Node, Node> map = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // If we have already processed this node, return its clone
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Create a new node (clone)
        Node clone = new Node(node.val);
        // Put it in the map BEFORE recursing to handle cycles
        map.put(node, clone);

        // Iterate through the neighbors of the original node
        for (Node neighbor : node.neighbors) {
            // Recursively clone the neighbors and add to the clone's list
            clone.neighbors.add(cloneGraph(neighbor));
        }

        return clone;
    }
}