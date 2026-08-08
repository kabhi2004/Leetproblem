import java.util.*;

class Solution {
    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[] ans = new int[m];

        // last[j] = last index in word1 where word2[j] occurs
        // while maintaining the possibility of matching
        // word2[j+1], word2[j+2], ...
        int[] last = new int[m];
        Arrays.fill(last, -1);

        // Build from right to left
        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {

            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }

            i--;
        }

        // We are allowed to use one mismatch
        boolean mismatchAvailable = true;

        j = 0;

        // Greedily choose the smallest possible index
        for (i = 0; i < n; i++) {

            if (j == m) {
                break;
            }

            // Case 1: exact match
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;
            }

            // Case 2: use our one mismatch
            else if (
                mismatchAvailable &&
                (
                    j == m - 1 ||
                    i < last[j + 1]
                )
            ) {

                ans[j] = i;
                j++;

                mismatchAvailable = false;
            }
        }

        if (j == m) {
            return ans;
        }

        return new int[0];
    }
}