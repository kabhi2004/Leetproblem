class Solution {

    static class Node {
        int product;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int n;
    int k;
    int[] nums;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;
        this.nums = nums;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Update nums[index]
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            // Query range [start, n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            // Number of prefixes having product % k == x
            ans[i] = res.cnt[x];
        }

        return ans;
    }

    // Build Segment Tree
    private void build(int node, int left, int right) {

        if (left == right) {

            tree[node] = new Node(k);

            int rem = nums[left] % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // Merge two nodes
    private Node merge(Node a, Node b) {

        Node res = new Node(k);

        // Product of the complete segment
        res.product = (a.product * b.product) % k;

        // Prefixes completely inside left segment
        for (int r = 0; r < k; r++) {
            res.cnt[r] += a.cnt[r];
        }

        // Prefixes that continue from left into right
        for (int r = 0; r < k; r++) {

            int newRemainder = (a.product * r) % k;

            res.cnt[newRemainder] += b.cnt[r];
        }

        return res;
    }

    // Point Update
    private void update(int node, int left, int right,
                        int index, int value) {

        if (left == right) {

            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].product = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(
            tree[node * 2],
            tree[node * 2 + 1]
        );
    }

    // Range Query
    private Node query(int node, int left, int right,
                       int ql, int qr) {

        // Complete overlap
        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        // Completely in left
        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }

        // Completely in right
        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        // Split between left and right
        Node a = query(node * 2, left, mid, ql, qr);
        Node b = query(node * 2 + 1, mid + 1, right, ql, qr);

        return merge(a, b);
    }
}