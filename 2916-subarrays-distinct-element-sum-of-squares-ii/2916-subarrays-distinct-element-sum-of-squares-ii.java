
class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] cnt;
    private long[] sq;
    private long[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        cnt = new long[4 * n];
        sq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        long ans = 0;

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]];

            update(1, 0, n - 1, left, i);

            ans = (ans + sq[1]) % MOD;

            last[nums[i]] = i + 1;
        }

        return (int) ans;
    }

    private void apply(int node, int start, int end, long val) {
        long len = end - start + 1L;

        long oldCnt = cnt[node];

        sq[node] = (
            sq[node]
            + 2L * val % MOD * oldCnt % MOD
            + val * val % MOD * len % MOD
        ) % MOD;

        cnt[node] = (cnt[node] + val * len) % MOD;

        lazy[node] = (lazy[node] + val) % MOD;
    }

    private void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;

        apply(node * 2, start, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, end, lazy[node]);

        lazy[node] = 0;
    }

    private void update(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            return;
        }

        if (l <= start && end <= r) {
            apply(node, start, end, 1);
            return;
        }

        push(node, start, end);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, l, r);
        update(node * 2 + 1, mid + 1, end, l, r);

        cnt[node] = (cnt[node * 2] + cnt[node * 2 + 1]) % MOD;
        sq[node] = (sq[node * 2] + sq[node * 2 + 1]) % MOD;
    }
}
