class Solution {

    class Node {
        long cnt;
        long sum;

        Node(long cnt, long sum) {
            this.cnt = cnt;
            this.sum = sum;
        }
    }

    int[] digits;
    Node[][][][] dp;
    boolean[][][][] vis;

    private Node dfs(int pos, int prev2, int prev1, int started, boolean tight) {

        if (pos == digits.length) {
            return new Node(1, 0);
        }

        if (!tight && vis[pos][prev2][prev1][started]) {
            return dp[pos][prev2][prev1][started];
        }

        int limit = tight ? digits[pos] : 9;

        long totalCount = 0;
        long totalSum = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nextTight = tight && (d == limit);

            if (started == 0 && d == 0) {

                Node next = dfs(pos + 1, 10, 10, 0, nextTight);

                totalCount += next.cnt;
                totalSum += next.sum;

            } else if (started == 0) {

                Node next = dfs(pos + 1, 10, d, 1, nextTight);

                totalCount += next.cnt;
                totalSum += next.sum;

            } else {

                int add = 0;

                if (prev2 != 10) {
                    if ((prev1 > prev2 && prev1 > d) ||
                        (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                Node next = dfs(pos + 1, prev1, d, 1, nextTight);

                totalCount += next.cnt;
                totalSum += next.sum + (long) add * next.cnt;
            }
        }

        Node ans = new Node(totalCount, totalSum);

        if (!tight) {
            vis[pos][prev2][prev1][started] = true;
            dp[pos][prev2][prev1][started] = ans;
        }

        return ans;
    }

    private long solve(long x) {

        if (x <= 0) return 0;

        String s = Long.toString(x);

        digits = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            digits[i] = s.charAt(i) - '0';
        }

        dp = new Node[20][11][11][2];
        vis = new boolean[20][11][11][2];

        return dfs(0, 10, 10, 0, true).sum;
    }

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }
}