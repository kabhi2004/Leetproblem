class Solution {

    private static final int MOD = 1_000_000_007;

    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        long[][] dp = new long[m][n];

        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1 || dp[i][j] == 0) {
                    continue;
                }
                long ways = dp[i][j];

                int[] right = move(grid, i, j + 1, 0);

                if (right != null) {
                    int x = right[0];
                    int y = right[1];

                    dp[x][y] = (dp[x][y] + ways) % MOD;
                }
                int[] down = move(grid, i + 1, j, 1);

                if (down != null) {
                    int x = down[0];
                    int y = down[1];

                    dp[x][y] = (dp[x][y] + ways) % MOD;
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }

   
    private int[] move(int[][] grid, int x, int y, int dir) {
        int m = grid.length;
        int n = grid[0].length;

        while (true) {

            if (x < 0 || x >= m || y < 0 || y >= n) {
                return null;
            }
            if (grid[x][y] == 0) {
                return new int[]{x, y};
            }
            if (dir == 0) {
                x++;
                dir = 1;
            } else {
                y++;
                dir = 0;
            }
        }
    }
}
