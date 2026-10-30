import java.util.*;

class Solution {

    public int shortestPath(int n, int[][] edges, String labels, int k) {

        // Build adjacency list
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            graph[u].add(new int[]{v, w});
        }

        // dist[node][count]
        long[][] dist = new long[n][k + 1];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        // Priority Queue: {cost, node, consecutiveCount}
        PriorityQueue<long[]> pq = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // Start from node 0
        dist[0][1] = 0;

        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {

            long[] current = pq.poll();

            long cost = current[0];
            int u = (int) current[1];
            int count = (int) current[2];

            // Skip outdated states
            if (cost != dist[u][count]) {
                continue;
            }

            // Destination reached
            if (u == n - 1) {
                return (int) cost;
            }

            // Traverse outgoing edges
            for (int[] edge : graph[u]) {

                int v = edge[0];
                int weight = edge[1];

                int newCount;

                // Check consecutive identical characters
                if (labels.charAt(u) == labels.charAt(v)) {
                    newCount = count + 1;
                } else {
                    newCount = 1;
                }

                // Invalid path
                if (newCount > k) {
                    continue;
                }

                long newCost = cost + weight;

                // Relaxation
                if (newCost < dist[v][newCount]) {

                    dist[v][newCount] = newCost;

                    pq.offer(new long[]{
                        newCost,
                        v,
                        newCount
                    });
                }
            }
        }

        // No valid path exists
        return -1;
    }
}