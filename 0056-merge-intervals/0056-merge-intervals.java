class Solution {
    public int[][] merge(int[][] intervals) {
        // Edge case: if empty or single interval
        if (intervals.length <= 1) return intervals;

        // Step 1: Sort intervals by ascending start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();
        
        // Start with the first interval
        int[] currentInterval = intervals[0];
        result.add(currentInterval);

        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            if (currentEnd >= nextStart) { 
                // Overlap exists: Merge by updating the end time
                // We take the max because the second interval might be smaller (e.g., [1,5] and [2,3])
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                // No overlap: Move to the next interval
                currentInterval = interval;
                result.add(currentInterval);
            }
        }

        // Convert List<int[]> back to int[][]
        return result.toArray(new int[result.size()][]);
    }
}