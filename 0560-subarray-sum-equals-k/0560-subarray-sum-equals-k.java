import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int currentSum = 0;
        
        Map<Integer, Integer> map = new HashMap<>(nums.length + 1, 1.0f);
        
        map.put(0, 1);
        
        for (int num : nums) {
            currentSum += num;
           
            int target = currentSum - k;
            Integer freq = map.get(target);
            if (freq != null) {
                count += freq;
            }
            
            Integer currentFreq = map.get(currentSum);
            if (currentFreq == null) {
                map.put(currentSum, 1);
            } else {
                map.put(currentSum, currentFreq + 1);
            }
        }
        
        return count;
    }
}