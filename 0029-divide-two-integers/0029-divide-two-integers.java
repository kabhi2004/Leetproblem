class Solution {
    public int divide(int dividend, int divisor) {

        // Overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        // Convert both to long to avoid overflow in abs
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        int result = 0;

        while (a >= b) {
            long temp = b;
            int multiple = 1;

            // Keep doubling until it's too large
            while (a >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            a -= temp;
            result += multiple;
        }

        return sign * result;
    }
}
