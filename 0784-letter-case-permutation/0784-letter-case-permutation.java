class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        // Use a char array so we can modify it in-place
        backtrack(s.toCharArray(), 0, result);
        return result;
    }

    private void backtrack(char[] chars, int index, List<String> result) {
        // Base Case: We've reached the end of the string
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        // If the character is a digit, we can't change its case.
        // Just skip it and move to the next index.
        if (Character.isDigit(chars[index])) {
            backtrack(chars, index + 1, result);
            return;
        }

        // --- If it's a letter, we branch into two possibilities ---

        // Branch 1: Make it lowercase
        chars[index] = Character.toLowerCase(chars[index]);
        backtrack(chars, index + 1, result);

        // Branch 2: Make it uppercase
        chars[index] = Character.toUpperCase(chars[index]);
        backtrack(chars, index + 1, result);
    }
}