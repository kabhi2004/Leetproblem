class Solution {

    long LIMIT;

    public String smallestPalindrome(String s, int k) {
        LIMIT = k;

        int[] cnt = new int[26];
        for (char c : s.toCharArray())
            cnt[c - 'a']++;

        String mid = "";

        int[] half = new int[26];
        int len = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = cnt[i] / 2;
            len += half[i];

            if ((cnt[i] & 1) == 1)
                mid = String.valueOf((char) ('a' + i));
        }

        if (countWays(half, len) < k)
            return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < len; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half, len - pos - 1);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        StringBuilder right = new StringBuilder(left).reverse();

        return left.toString() + mid + right.toString();
    }

    private long countWays(int[] freq, int total) {

        long ans = 1;
        int remain = total;

        for (int f : freq) {
            if (f == 0)
                continue;

            ans = multiplyCap(ans, comb(remain, f));

            if (ans >= LIMIT)
                return LIMIT;

            remain -= f;
        }

        return ans;
    }

    private long comb(int n, int r) {

        if (r > n)
            return 0;

        r = Math.min(r, n - r);

        long ans = 1;

        for (int i = 1; i <= r; i++) {

            ans = ans * (n - r + i) / i;

            if (ans >= LIMIT)
                return LIMIT;
        }

        return ans;
    }

    private long multiplyCap(long a, long b) {

        if (a >= LIMIT || b >= LIMIT)
            return LIMIT;

        if (a > LIMIT / Math.max(1, b))
            return LIMIT;

        long x = a * b;

        return Math.min(x, LIMIT);
    }
}