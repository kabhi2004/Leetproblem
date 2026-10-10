
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long operations = (long) k1 + k2;
        int[] diff = new int[n];

        long total = 0;
        int maxDiff = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            total += diff[i];
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        // If all differences can become zero
        if (operations >= total) {
            return 0;
        }

        // Binary search for the smallest possible maximum difference
        int low = 0, high = maxDiff;

        while (low < high) {
            int mid = low + (high - low) / 2;
            long required = 0;

            for (int d : diff) {
                if (d > mid) {
                    required += d - mid;
                }
            }

            if (required <= operations) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        int limit = low;
        long answer = 0;
        long remaining = operations;
        for (int d : diff) {
            if (d > limit) {
                remaining -= d - limit;
                d = limit;
            }
            answer += (long) d * d;
        }
        answer = 0;

        for (int d : diff) {
            int finalDiff = Math.min(d, limit);
            answer += (long) finalDiff * finalDiff;
        }
        answer -= remaining * (2L * limit - 1);

        return answer;
    }
}
