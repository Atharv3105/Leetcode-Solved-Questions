import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums.length < 2) return false;
        
        Map<Integer, Integer> map = new HashMap<>(Math.min(nums.length, k));
        map.put(0, -1);
        
        int runningSum = 0;
        
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            int remainder = runningSum % k;
            
            Integer prevIndex = map.putIfAbsent(remainder, i);
            
            if (prevIndex != null) {
                if (i - prevIndex >= 2) {
                    return true;
                }
            }
        }
        return false;
    }
}