class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        // Sort abilities and capacities
        Arrays.sort(players);
        Arrays.sort(trainers);

        int p = 0; // pointer for players
        int t = 0; // pointer for trainers

        // Try to match players with trainers
        while (p < players.length && t < trainers.length) {
            if (players[p] <= trainers[t]) {
                // Trainer can handle this player
                p++;
            }
            // Move to next trainer regardless
            t++;
        }

        return p; // number of matched players
    }  
}