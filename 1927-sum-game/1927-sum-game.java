class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int leftQ = 0, rightQ = 0;

        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);

            if (i < n / 2) {
                if (ch == '?') {
                    leftQ++;
                } else {
                    leftSum += ch - '0';
                }
            } else {
                if (ch == '?') {
                    rightQ++;
                } else {
                    rightSum += ch - '0';
                }
            }
        }

        int totalQ = leftQ + rightQ;

        // Alice gets the last move
        if (totalQ % 2 == 1) {
            return true;
        }

        // Bob wins only if exact balancing is possible
        return 2 * (leftSum - rightSum) != 9 * (rightQ - leftQ);
    }
}
