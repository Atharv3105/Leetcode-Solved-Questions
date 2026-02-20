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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // Pre-allocate arrays based on the maximum constraint size (100)
        int[] arr1 = new int[100];
        int[] arr2 = new int[100];
        
        int top1 = 0;
        int top2 = 0;
        
        // "Push" values onto our simulated stacks
        while (l1 != null) {
            arr1[top1++] = l1.val;
            l1 = l1.next;
        }
        
        while (l2 != null) {
            arr2[top2++] = l2.val;
            l2 = l2.next;
        }
        
        ListNode head = null;
        int carry = 0;
        
        // "Pop" values and calculate the sum
        while (top1 > 0 || top2 > 0 || carry > 0) {
            int sum = carry;
            
            if (top1 > 0) {
                sum += arr1[--top1];
            }
            if (top2 > 0) {
                sum += arr2[--top2];
            }
            
            // Create node and attach it to the front of the result list
            ListNode newNode = new ListNode(sum % 10);
            newNode.next = head;
            head = newNode;
            
            carry = sum / 10;
        }
        
        return head;
    }
}