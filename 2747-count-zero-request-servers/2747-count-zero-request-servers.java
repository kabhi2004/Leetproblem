
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // Sort logs by timestamp
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        // Store queries with their original indices
        int m = queries.length;
        int[][] q = new int[m][2];

        for (int i = 0; i < m; i++) {
            q[i][0] = queries[i];
            q[i][1] = i;
        }

        // Sort queries by timestamp
        Arrays.sort(q, (a, b) -> a[0] - b[0]);

        int[] ans = new int[m];

        // Frequency of requests for each server
        int[] freq = new int[n + 1];

        int left = 0;
        int right = 0;
        int activeServers = 0;

        for (int i = 0; i < m; i++) {

            int start = q[i][0] - x;
            int end = q[i][0];

            // Add logs within the right boundary
            while (right < logs.length &&
                   logs[right][1] <= end) {

                int server = logs[right][0];

                if (freq[server] == 0) {
                    activeServers++;
                }

                freq[server]++;
                right++;
            }

            // Remove logs outside the left boundary
            while (left < right &&
                   logs[left][1] < start) {

                int server = logs[left][0];

                freq[server]--;

                if (freq[server] == 0) {
                    activeServers--;
                }

                left++;
            }

            // Store answer using original query index
            ans[q[i][1]] = n - activeServers;
        }

        return ans;
    }
}
