import java.util.*;

class Solution {

    static class State {
        long weight;
        int[] indices;

        State(long weight, int[] indices) {
            this.weight = weight;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);

            if (a[1] != b[1])
                return Integer.compare(a[1], b[1]);

            return Integer.compare(a[3], b[3]);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = findNext(arr, i);
        }

        State[][] dp = new State[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = new State(0, new int[0]);

            for (int k = 1; k <= 4; k++) {
                State skip = dp[i + 1][k];

                State nextState = dp[next[i]][k - 1];

                int[] chosen = new int[nextState.indices.length + 1];
                chosen[0] = arr[i][3];

                for (int j = 0; j < nextState.indices.length; j++) {
                    chosen[j + 1] = nextState.indices[j];
                }

                Arrays.sort(chosen);

                State take = new State(
                    arr[i][2] + nextState.weight,
                    chosen
                );

                dp[i][k] = better(skip, take);
            }
        }

        return dp[0][4].indices;
    }

    private State better(State a, State b) {
        if (a.weight != b.weight) {
            return a.weight > b.weight ? a : b;
        }

        return compare(a.indices, b.indices) <= 0 ? a : b;
    }

    private int compare(int[] a, int[] b) {
        int n = Math.min(a.length, b.length);

        for (int i = 0; i < n; i++) {
            if (a[i] != b[i]) {
                return Integer.compare(a[i], b[i]);
            }
        }

        return Integer.compare(a.length, b.length);
    }

    private int findNext(int[][] arr, int i) {
        int target = arr[i][1];

        int left = i + 1;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid][0] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}