import java.util.PriorityQueue;

class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        // In Java, PriorityQueue is a Min-Heap by default
        this.minHeap = new PriorityQueue<>();
        
        // Populate the heap with initial numbers
        for (int num : nums) {
            add(num);
        }
    }
    
    public int add(int val) {
        // Step 1: Add the new number to the heap
        minHeap.offer(val);
        
        // Step 2: If the heap has more than k elements, kick out the smallest one
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        
        // Step 3: The root of the Min-Heap is our Kth largest element
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */