
import java.util.*;

class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        PriorityQueue<int[]> busy = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        TreeSet<Integer> available = new TreeSet<>();

        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        int[] count = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {

            int time = arrival[i];

            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                int[] server = busy.poll();
                available.add(server[1]);
            }

            if (available.isEmpty()) {
                continue;
            }

            int target = i % k;

            Integer server = available.ceiling(target);

            if (server == null) {
                server = available.first();
            }

          
            available.remove(server);

            count[server]++;
            maxRequests = Math.max(maxRequests, count[server]);

            
            int endTime = arrival[i] + load[i];

            busy.offer(new int[]{endTime, server});
        }

      
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                result.add(i);
            }
        }

        return result;
    }
}
