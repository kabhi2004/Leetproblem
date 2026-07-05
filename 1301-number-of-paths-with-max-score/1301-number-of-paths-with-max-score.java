class Solution {

    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();
        int MOD = 1000000007;

        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];

        // -1 means unreachable
        for (int i = 0; i < n; i++) {
            Arrays.fill(score[i], -1);
        }

        score[0][0] = 0;
        ways[0][0] = 1;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                char c = board.get(i).charAt(j);

                if (c == 'X')
                    continue;

                if (i == 0 && j == 0)
                    continue;

                int best = -1;
                int count = 0;

                // From Top
                if (i > 0 && score[i - 1][j] != -1) {
                    if (score[i - 1][j] > best) {
                        best = score[i - 1][j];
                        count = ways[i - 1][j];
                    } else if (score[i - 1][j] == best) {
                        count = (count + ways[i - 1][j]) % MOD;
                    }
                }

                // From Left
                if (j > 0 && score[i][j - 1] != -1) {
                    if (score[i][j - 1] > best) {
                        best = score[i][j - 1];
                        count = ways[i][j - 1];
                    } else if (score[i][j - 1] == best) {
                        count = (count + ways[i][j - 1]) % MOD;
                    }
                }

                // From Diagonal
                if (i > 0 && j > 0 && score[i - 1][j - 1] != -1) {
                    if (score[i - 1][j - 1] > best) {
                        best = score[i - 1][j - 1];
                        count = ways[i - 1][j - 1];
                    } else if (score[i - 1][j - 1] == best) {
                        count = (count + ways[i - 1][j - 1]) % MOD;
                    }
                }

                if (best == -1)
                    continue;

                int value = 0;
                if (c >= '1' && c <= '9')
                    value = c - '0';

                score[i][j] = best + value;
                ways[i][j] = count;
            }
        }

        if (ways[n - 1][n - 1] == 0)
            return new int[] {0, 0};

        return new int[] {score[n - 1][n - 1], ways[n - 1][n - 1]};
    }
}