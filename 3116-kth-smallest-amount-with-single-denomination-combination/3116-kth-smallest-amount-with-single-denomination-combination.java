class Solution {

    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;

        // Remove redundant coins.
        // If a coin is divisible by a smaller coin, all its multiples
        // are already covered by the smaller coin.
        Arrays.sort(coins);

        List<Integer> list = new ArrayList<>();

        for (int coin : coins) {
            boolean redundant = false;

            for (int prev : list) {
                if (coin % prev == 0) {
                    redundant = true;
                    break;
                }
            }

            if (!redundant) {
                list.add(coin);
            }
        }

        int m = list.size();
        int[] arr = new int[m];

        for (int i = 0; i < m; i++) {
            arr[i] = list.get(i);
        }

        // Upper bound:
        // k-th multiple of the smallest coin.
        long low = 1;
        long high = (long) arr[0] * k;

        while (low < high) {
            long mid = low + (high - low) / 2;

            if (count(mid, arr) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, int[] coins) {
        int n = coins.length;
        long total = 0;

        // Iterate through all non-empty subsets
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    lcm = getLCM(lcm, coins[i]);

                    // If LCM > x, this subset contributes 0
                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                continue;
            }

            long multiples = x / lcm;

            if (bits % 2 == 1) {
                total += multiples;
            } else {
                total -= multiples;
            }
        }

        return total;
    }

    private long getLCM(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}
