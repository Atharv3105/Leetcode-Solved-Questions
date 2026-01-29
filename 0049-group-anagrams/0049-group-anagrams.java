import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            // Convert string to char array to sort it
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = new String(ca);
            
            // If key doesn't exist, initialize the list
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            
            // Add the original string to the grouping
            map.get(key).add(s);
        }
        
        return new ArrayList<>(map.values());
    }
}