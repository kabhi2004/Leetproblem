class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder prefix = new StringBuilder();
        String answer = "";

        for (int i = 0; i < n; i++) {
            int targetChar = target.charAt(i) - 'a';

            for (int ch = targetChar + 1; ch < 26; ch++) {
                if (count[ch] > 0) {
                    int[] temp = count.clone();
                    temp[ch]--;

                    StringBuilder candidate = new StringBuilder(prefix);
                    candidate.append((char) ('a' + ch));

                    for (int k = 0; k < 26; k++) {
                        while (temp[k] > 0) {
                            candidate.append((char) ('a' + k));
                            temp[k]--;
                        }
                    }

                    answer = candidate.toString();
                    break;
                }
            }
            if (count[targetChar] == 0) {
                break;
            }

            count[targetChar]--;
            prefix.append(target.charAt(i));
        }

        return answer;
    }
}