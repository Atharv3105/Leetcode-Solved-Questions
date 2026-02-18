import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // Base case: if input is empty
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Min-Heap to keep track of the smallest node among the k lists
        // Size is k, ordered by node value
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 1. Add the head of every list to the heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;

        // 2. Extract min, add to result, and insert next node from that list
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll(); // Get smallest node
            current.next = minNode;            // Add to result list
            current = current.next;

            // If the extracted node has a next node, push it into the heap
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        return dummyHead.next;
    }
}