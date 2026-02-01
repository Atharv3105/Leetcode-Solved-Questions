class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // Phase 1: Add all intervals that come strictly BEFORE the newInterval
        // We check if the current interval's END is less than newInterval's START
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // Phase 2: Merge overlapping intervals
        // We are in the overlap zone as long as the current interval's START 
        // is less than or equal to the newInterval's END
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // Expand our newInterval to encompass the current one
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // Add the fully merged interval
        result.add(newInterval);

        // Phase 3: Add the remaining intervals that come strictly AFTER
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}