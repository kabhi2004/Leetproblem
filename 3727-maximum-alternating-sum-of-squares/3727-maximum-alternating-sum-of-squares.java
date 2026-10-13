
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        long[] squares = new long[nums.length];

        for (int i = 0; i < nums.length; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }

        Arrays.sort(squares);

        long ans = 0;
        int n = squares.length;
        int negativeCount = n / 2;

        for (int i = 0; i < n; i++) {
            if (i < negativeCount) {
                ans -= squares[i];
            } else {
                ans += squares[i];
            }
        }

        return ans;
    }
}
