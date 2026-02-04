class Solution {
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> result = new ArrayList<>();
        int i = 0; // Pointer for firstList
        int j = 0; // Pointer for secondList

        while (i < firstList.length && j < secondList.length) {
            // Let's represent the intervals as [start1, end1] and [start2, end2]
            int start1 = firstList[i][0];
            int end1 = firstList[i][1];
            int start2 = secondList[j][0];
            int end2 = secondList[j][1];

            // Calculate the intersection boundaries
            // The intersection starts at the maximum of the two start times
            int intersectStart = Math.max(start1, start2);
            // The intersection ends at the minimum of the two end times
            int intersectEnd = Math.min(end1, end2);

            // Check if there is a valid overlap
            if (intersectStart <= intersectEnd) {
                result.add(new int[]{intersectStart, intersectEnd});
            }

            // Move the pointer for the interval that ends first
            if (end1 < end2) {
                i++;
            } else {
                j++;
            }
        }

        // Convert the ArrayList to a 2D array
        return result.toArray(new int[result.size()][]);
    }
}