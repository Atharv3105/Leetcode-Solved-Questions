class Solution {
    public ListNode sortList(ListNode head) {
        // Base Case: 0 or 1 node
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Find the middle using "Tortoise and Hare"
        // We use a helper to ensure we split strictly before the mid to avoid stack overflow
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. Cut the list into two halves
        if (prev != null) {
            prev.next = null;
        }

        // 3. Recursive calls
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);

        // 4. Merge the sorted halves
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Attach remaining nodes directly (no loop needed here)
        if (l1 != null) {
            current.next = l1;
        } else if (l2 != null) {
            current.next = l2;
        }

        return dummy.next;
    }
}