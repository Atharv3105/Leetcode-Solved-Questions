class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pascal = new ArrayList<>();

        // First row is always [1]
        pascal.add(Arrays.asList(1));

        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = pascal.get(i - 1);
            List<Integer> newRow = new ArrayList<>();

            // First element is always 1
            newRow.add(1);

            // Middle elements
            for (int j = 1; j < prevRow.size(); j++) {
                newRow.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            // Last element is always 1
            newRow.add(1);

            pascal.add(newRow);
        }

        return pascal;
    }
}
