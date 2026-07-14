class Solution {
    static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int max = 200;

        long[][] dp = new long[max + 1][max + 1];
        dp[0][0] = 1;

        for (int x : nums) {
            long[][] newDp = new long[max + 1][max + 1];

            for (int g1 = 0; g1 <= max; g1++) {
                for (int g2 = 0; g2 <= max; g2++) {
                    long val = dp[g1][g2];
                    if (val == 0) continue;

                    // Skip
                    newDp[g1][g2] = (newDp[g1][g2] + val) % MOD;

                    // Put in seq1
                    int ng1 = (g1 == 0) ? x : gcd(g1, x);
                    newDp[ng1][g2] = (newDp[ng1][g2] + val) % MOD;

                    // Put in seq2
                    int ng2 = (g2 == 0) ? x : gcd(g2, x);
                    newDp[g1][ng2] = (newDp[g1][ng2] + val) % MOD;
                }
            }

            dp = newDp;
        }

        long ans = 0;
        for (int g = 1; g <= max; g++) {
            ans = (ans + dp[g][g]) % MOD;
        }

        return (int) ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}