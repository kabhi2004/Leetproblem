class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[] dp0 = new boolean[MAX];
        boolean[] dp1 = new boolean[MAX];
        boolean[] dp2 = new boolean[MAX];
        boolean[] dp3 = new boolean[MAX];

        dp0[0] = true;

        for (int v : nums) {
            boolean[] ndp0 = dp0.clone();
            boolean[] ndp1 = dp1.clone();
            boolean[] ndp2 = dp2.clone();
            boolean[] ndp3 = dp3.clone();

            // dp0 -> dp1, dp2, dp3
            for (int x = 0; x < MAX; x++) {
                if (!dp0[x]) continue;

                ndp1[x ^ v] = true; // use once
                ndp2[x] = true;     // use twice
                ndp3[x ^ v] = true; // use three times
            }

            // dp1 -> dp2, dp3
            for (int x = 0; x < MAX; x++) {
                if (!dp1[x]) continue;

                ndp2[x ^ v] = true; // use once
                ndp3[x] = true;     // use twice
            }

            // dp2 -> dp3
            for (int x = 0; x < MAX; x++) {
                if (!dp2[x]) continue;

                ndp3[x ^ v] = true; // use once
            }

            dp0 = ndp0;
            dp1 = ndp1;
            dp2 = ndp2;
            dp3 = ndp3;
        }

        int ans = 0;
        for (boolean b : dp3) {
            if (b) ans++;
        }
        return ans;
    }
}