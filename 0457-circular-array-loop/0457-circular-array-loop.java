class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;

        // Iterate through each index as a potential start of a cycle
        for (int i = 0; i < n; i++) {
            
            // If we have already processed this node (marked as 0), skip it
            if (nums[i] == 0) {
                continue;
            }

            int slow = i;
            int fast = getNext(nums, i);
            
            // Move pointers while direction is consistent
            // nums[slow], nums[fast], and nums[next(fast)] must all share the same sign
            while (nums[fast] * nums[i] > 0 && 
                   nums[getNext(nums, fast)] * nums[i] > 0) {
                
                if (slow == fast) {
                    // Cycle detected. Check if cycle length > 1
                    // If slow points to itself, it's a 1-element cycle (invalid)
                    if (slow == getNext(nums, slow)) {
                        break;
                    }
                    return true;
                }
                
                slow = getNext(nums, slow);
                fast = getNext(nums, getNext(nums, fast));
            }

            // If a valid cycle wasn't found, mark the path as visited (0)
            // so we don't process these indices again.
            int curr = i;
            int val = nums[i];
            while (nums[curr] * val > 0) {
                int next = getNext(nums, curr);
                nums[curr] = 0;
                curr = next;
            }
        }

        return false;
    }

    // Helper to calculate the next index with circular wrapping
    private int getNext(int[] nums, int i) {
        int n = nums.length;
        return ((i + nums[i]) % n + n) % n;
    }
}