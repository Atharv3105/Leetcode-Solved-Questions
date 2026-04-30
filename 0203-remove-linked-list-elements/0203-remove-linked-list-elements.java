class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        // Handle head deletions first
        while (head != null && head.val == val) {
            head = head.next;
        }

        ListNode prev = head;
        ListNode temp = head;

        while (temp != null) {
            if (temp.val == val) {
                prev.next = temp.next;
            } else {
                prev = temp;
            }
            temp = temp.next;
        }

        return head;
    }
}