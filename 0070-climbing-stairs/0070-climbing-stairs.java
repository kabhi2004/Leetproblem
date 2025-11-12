class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n; // base cases

        int a = 1; // ways(1)
        int b = 2; // ways(2)
        int c = 0;

        for (int i = 3; i <= n; i++) {
            c = a + b; // ways(i) = ways(i-1) + ways(i-2)
            a = b;
            b = c;
        }

        return b; // b holds ways(n)
    }
}
