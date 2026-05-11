class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {
        int totalTime = 0;
        
        for (int i = 0; i < tickets.length; i++) {
            // Rule 1: People at or in front of 'k'
            if (i <= k) {
                totalTime += Math.min(tickets[i], tickets[k]);
            } 
            // Rule 2: People strictly behind 'k'
            else {
                totalTime += Math.min(tickets[i], tickets[k] - 1);
            }
        }
        
        return totalTime;
    }
}
