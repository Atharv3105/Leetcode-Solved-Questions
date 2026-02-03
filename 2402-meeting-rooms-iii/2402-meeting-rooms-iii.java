class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // 1. Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // 2. Structures to manage rooms
        // counts: tracks how many meetings each room held
        int[] meetingCounts = new int[n];
        
        // freeRooms: Min-heap for available room indices (lowest index first)
        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            freeRooms.offer(i);
        }

        // busyRooms: Min-heap for occupied rooms.
        // Orders by: 1. Earliest finish time, 2. Lowest room index (tie-breaker)
        PriorityQueue<long[]> busyRooms = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Long.compare(a[0], b[0]);
            return Long.compare(a[1], b[1]); 
        });

        // 3. Process each meeting
        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            // Free up rooms that have finished before current meeting starts
            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= start) {
                freeRooms.offer((int) busyRooms.poll()[1]);
            }

            // Allocate a room
            if (!freeRooms.isEmpty()) {
                // Case 1: Room is available
                int room = freeRooms.poll();
                meetingCounts[room]++;
                busyRooms.offer(new long[]{end, room});
            } else {
                // Case 2: No room available, must wait for earliest finish
                long[] earliestRoom = busyRooms.poll();
                long finishTime = earliestRoom[0];
                int room = (int) earliestRoom[1];

                // Calculate new end time: previous finish + duration
                long duration = end - start;
                long newEnd = finishTime + duration;

                meetingCounts[room]++;
                busyRooms.offer(new long[]{newEnd, room});
            }
        }

        // 4. Find the room with max meetings
        int maxMeetings = -1;
        int resultRoom = -1;
        
        for (int i = 0; i < n; i++) {
            if (meetingCounts[i] > maxMeetings) {
                maxMeetings = meetingCounts[i];
                resultRoom = i;
            }
        }

        return resultRoom;
    }
}