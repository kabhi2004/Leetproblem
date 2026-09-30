class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {

            if (seq.charAt(i) == '(') {
                depth++;

                // Odd depth -> B (1)
                // Even depth -> A (0)
                ans[i] = depth % 2;
            } 
            else {
                // Use current depth before decreasing
                ans[i] = depth % 2;

                depth--;
            }
        }

        return ans;
    }
}