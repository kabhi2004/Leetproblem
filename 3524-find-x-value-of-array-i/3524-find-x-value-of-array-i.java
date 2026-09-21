class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] ans = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {

            long[] newDp = new long[k];

            int value = num % k;

            // Start a new subarray with nums[i]
            newDp[value]++;

            // Extend all previous subarrays
            for (int r = 0; r < k; r++) {

                if (dp[r] == 0) {
                    continue;
                }

                int newRemainder = (r * value) % k;

                newDp[newRemainder] += dp[r];
            }

            // All subarrays ending at current index
            // contribute to the final answer
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}