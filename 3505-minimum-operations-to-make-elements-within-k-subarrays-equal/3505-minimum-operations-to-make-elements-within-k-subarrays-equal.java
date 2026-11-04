import java.util.*;

class Solution {

    static class Fenwick {
        int n;
        long[] tree;

        Fenwick(int n) {
            this.n = n;
            tree = new long[n + 1];
        }

        void update(int i, long delta) {
            while (i <= n) {
                tree[i] += delta;
                i += i & -i;
            }
        }

        long query(int i) {
            long sum = 0;

            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }

            return sum;
        }

        // Find the smallest index whose prefix sum >= k
        int findKth(long k) {
            int idx = 0;

            for (int step = Integer.highestOneBit(n);
                 step > 0;
                 step >>= 1) {

                int next = idx + step;

                if (next <= n && tree[next] < k) {
                    idx = next;
                    k -= tree[next];
                }
            }

            return idx + 1;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] temp = new int[n];
        int m = 0;

        for (int v : sorted) {
            if (m == 0 || temp[m - 1] != v) {
                temp[m++] = v;
            }
        }

        int[] values = Arrays.copyOf(temp, m);

        int[] id = new int[n];

        for (int i = 0; i < n; i++) {
            id[i] = Arrays.binarySearch(values, nums[i]) + 1;
        }

        // Step 2: Two Fenwick Trees
        Fenwick countBIT = new Fenwick(m);
        Fenwick sumBIT = new Fenwick(m);

        long[] cost = new long[n - x + 1];

        long totalSum = 0;

        // Add an element to the current window
        // (implemented directly below)

        for (int i = 0; i < x; i++) {
            countBIT.update(id[i], 1);
            sumBIT.update(id[i], nums[i]);
            totalSum += nums[i];
        }

        // Step 3: Calculate the cost of each window
        for (int start = 0; start <= n - x; start++) {

            // Find median (lower median)
            int medianIndex = countBIT.findKth((x + 1) / 2);
            long median = values[medianIndex - 1];

            // Left side: elements <= median
            long leftCount = countBIT.query(medianIndex);
            long leftSum = sumBIT.query(medianIndex);

            // Right side: elements > median
            long rightCount = x - leftCount;
            long rightSum = totalSum - leftSum;

            // Minimum operations for this window
            cost[start] =
                median * leftCount - leftSum
                + rightSum - median * rightCount;

            // Slide window
            if (start < n - x) {

                // Remove outgoing element
                countBIT.update(id[start], -1);
                sumBIT.update(id[start], -nums[start]);
                totalSum -= nums[start];

                // Add incoming element
                int next = start + x;

                countBIT.update(id[next], 1);
                sumBIT.update(id[next], nums[next]);
                totalSum += nums[next];
            }
        }

        // Step 4: Dynamic Programming
        long INF = Long.MAX_VALUE / 4;

        long[] prev = new long[n + 1];
        long[] curr = new long[n + 1];

        // Select exactly k non-overlapping windows
        for (int j = 1; j <= k; j++) {

            Arrays.fill(curr, INF);

            for (int i = 1; i <= n; i++) {

                // Option 1: Skip current element
                curr[i] = curr[i - 1];

                // Option 2: Select window ending at i-1
                if (i >= x && prev[i - x] != INF) {

                    long take = prev[i - x] + cost[i - x];

                    curr[i] = Math.min(curr[i], take);
                }
            }

            // Move to next DP state
            long[] swap = prev;
            prev = curr;
            curr = swap;
        }

        return prev[n];
    }
}