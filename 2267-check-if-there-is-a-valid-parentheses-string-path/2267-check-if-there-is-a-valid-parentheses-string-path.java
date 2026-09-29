class Solution {
    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        // Valid parentheses string must have even length
        int len = m + n - 1;

        if (len % 2 == 1) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        // Last character must be ')'
        if (grid[m - 1][n - 1] == '(') {
            return false;
        }

        // dp[i][j][balance]
        boolean[][][] dp = new boolean[m][n][len + 1];

        // Starting cell '(' -> balance = 1
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Starting cell already initialized
                if (i == 0 && j == 0) {
                    continue;
                }

                // Current cell changes the balance
                int change = (grid[i][j] == '(') ? 1 : -1;

                for (int balance = 0; balance <= len; balance++) {

                    int prevBalance = balance - change;

                    if (prevBalance < 0 || prevBalance > len) {
                        continue;
                    }

                    // We can come from top
                    if (i > 0 && dp[i - 1][j][prevBalance]) {
                        dp[i][j][balance] = true;
                    }

                    // We can come from left
                    if (j > 0 && dp[i][j - 1][prevBalance]) {
                        dp[i][j][balance] = true;
                    }
                }
            }
        }

        // At the end balance must be 0
        return dp[m - 1][n - 1][0];
    }
}