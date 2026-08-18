class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // Number of subarrays that contain each value
        int[] count = new int[51];

        // Check every subarray of size k
        for (int i = 0; i <= n - k; i++) {

            // Avoid counting the same value twice
            // inside the same subarray
            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {
                if (!seen[nums[j]]) {
                    seen[nums[j]] = true;
                    count[nums[j]]++;
                }
            }
        }
        for (int i = 50; i >= 0; i--) {
            if (count[i] == 1) {
                return i;
            }
        }

        return -1;
    }
}