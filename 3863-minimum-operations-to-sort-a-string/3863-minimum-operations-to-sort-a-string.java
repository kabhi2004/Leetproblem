
class Solution {
    public int minOperations(String s) {
        int n = s.length();

        // Already sorted
        boolean sorted = true;
        for (int i = 1; i < n; i++) {
            if (s.charAt(i) < s.charAt(i - 1)) {
                sorted = false;
                break;
            }
        }

        if (sorted) return 0;

        // An unsorted string of length 2 cannot be sorted
        if (n == 2) return -1;

        char first = s.charAt(0);
        char last = s.charAt(n - 1);

        char minChar = 'z';
        char maxChar = 'a';

        for (int i = 0; i < n; i++) {
            minChar = (char) Math.min(minChar, s.charAt(i));
            maxChar = (char) Math.max(maxChar, s.charAt(i));
        }

        // If sorting the substring excluding the first
        // character is enough, return 1.
        if (first <= minChar) return 1;

        // If sorting the substring excluding the last
        // character is enough, return 1.
        if (last >= maxChar) return 1;

        // Special case: extremes occupy both endpoints.
        if (first == maxChar && last == minChar) {
            int maxCount = 0;
            int minCount = 0;

            for (int i = 0; i < n; i++) {
                if (s.charAt(i) == maxChar) maxCount++;
                if (s.charAt(i) == minChar) minCount++;
            }

            if (maxCount == 1 && minCount == 1) return 3;
        }

        return 2;
    }
}
