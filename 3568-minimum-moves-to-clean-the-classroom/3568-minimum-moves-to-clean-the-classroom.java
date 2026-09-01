import java.util.*;

class Solution {

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0, startC = 0;
        int litterCount = 0;

        // Assign a bit number to every litter cell
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                } else if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int allCollected = (1 << litterCount) - 1;

        // No litter
        if (litterCount == 0) {
            return 0;
        }

        /*
         * best[r][c][mask] = maximum remaining energy
         * with which we have reached this state.
         */
        int[][][] best = new int[m][n][1 << litterCount];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(best[i][j], -1);
            }
        }

        Queue<int[]> queue = new ArrayDeque<>();

        // {row, col, collectedMask, remainingEnergy}
        queue.offer(new int[]{startR, startC, 0, energy});
        best[startR][startC][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] curr = queue.poll();

                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int remaining = curr[3];

                if (mask == allCollected) {
                    return moves;
                }

                // Cannot make another move without energy
                if (remaining == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    // Boundary check
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                        continue;
                    }

                    char cell = classroom[nr].charAt(nc);

                    // Cannot pass obstacle
                    if (cell == 'X') {
                        continue;
                    }

                    int newEnergy = remaining - 1;
                    int newMask = mask;

                    // Collect litter
                    if (cell == 'L') {
                        newMask |= (1 << litterId[nr][nc]);
                    }

                    // Reset energy
                    if (cell == 'R') {
                        newEnergy = energy;
                    }

                    // If already reached with equal or better energy, skip
                    if (best[nr][nc][newMask] >= newEnergy) {
                        continue;
                    }

                    best[nr][nc][newMask] = newEnergy;

                    queue.offer(new int[]{
                        nr,
                        nc,
                        newMask,
                        newEnergy
                    });
                }
            }

            moves++;
        }

        return -1;
    }
}