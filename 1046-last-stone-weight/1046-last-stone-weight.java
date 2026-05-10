import java.util.PriorityQueue;

class Solution {
    public int lastStoneWeight(int[] stones) {
        // Step 1: Create a Max-Heap. 
        // Java's PriorityQueue is a Min-Heap by default, so we use (a, b) -> b - a to reverse it.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        
        // Step 2: Load all stones into the Max-Heap.
        // It will automatically organize them so the heaviest is at the root.
        for (int stone : stones) {
            maxHeap.offer(stone);
        }
        
        // Step 3: Simulate the game greedily.
        // We must continue as long as there are at least 2 stones left to smash.
        while (maxHeap.size() > 1) {
            int heavy1 = maxHeap.poll(); // Extract the absolute heaviest stone
            int heavy2 = maxHeap.poll(); // Extract the second heaviest stone
            
            // If heavy1 == heavy2, they both turn to dust (we do nothing).
            // If heavy1 != heavy2, the remainder must go back into the pile.
            if (heavy1 != heavy2) {
                maxHeap.offer(heavy1 - heavy2);
            }
        }
        
        // Step 4: The game is over. 
        // If the heap is empty, all stones were destroyed. Otherwise, return the final survivor.
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}