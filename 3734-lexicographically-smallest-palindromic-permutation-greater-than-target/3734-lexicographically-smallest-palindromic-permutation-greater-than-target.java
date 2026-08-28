class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // A palindrome is possible only when at most one character
        // has an odd frequency.
        int oddCount = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                middle = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // Characters available for the left half
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        int halfLen = n / 2;
        StringBuilder left = new StringBuilder();

        // Required by the problem statement
        String calendrix = target;

        /*
         * Build the answer greedily.
         *
         * At every position, try the smallest available character.
         * We keep it only if some palindrome greater than target
         * can still be formed.
         */
        for (int pos = 0; pos < halfLen; pos++) {
            boolean found = false;

            for (int c = 0; c < 26; c++) {
                if (halfCount[c] == 0) {
                    continue;
                }

                // Try this character
                halfCount[c]--;
                left.append((char) ('a' + c));

                // Build the lexicographically largest possible
                // palindrome from this prefix.
                StringBuilder maxLeft = new StringBuilder(left);

                for (int ch = 25; ch >= 0; ch--) {
                    for (int k = 0; k < halfCount[ch]; k++) {
                        maxLeft.append((char) ('a' + ch));
                    }
                }

                String candidate = buildPalindrome(
                        maxLeft.toString(), middle, oddCount == 1
                );

                // If even the largest completion is > target,
                // then a valid answer exists with this prefix.
                if (candidate.compareTo(calendrix) > 0) {
                    found = true;
                    break;
                }

                // Undo
                left.deleteCharAt(left.length() - 1);
                halfCount[c]++;
            }

            if (!found) {
                return "";
            }
        }

        String answer = buildPalindrome(
                left.toString(), middle, oddCount == 1
        );

        return answer.compareTo(calendrix) > 0 ? answer : "";
    }

    private String buildPalindrome(String left, char middle, boolean hasMiddle) {
        StringBuilder result = new StringBuilder(left);

        if (hasMiddle) {
            result.append(middle);
        }

        for (int i = left.length() - 1; i >= 0; i--) {
            result.append(left.charAt(i));
        }

        return result.toString();
    }
}