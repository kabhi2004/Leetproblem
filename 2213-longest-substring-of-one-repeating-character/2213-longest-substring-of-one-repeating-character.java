class Solution {

    static class Node {
        int len;
        int prefix;
        int suffix;
        int max;
        char leftChar;
        char rightChar;

        Node() {}

        Node(char c) {
            len = 1;
            prefix = 1;
            suffix = 1;
            max = 1;
            leftChar = c;
            rightChar = c;
        }
    }

    Node[] tree;
    char[] s;

    public int[] longestRepeating(String str, String queryCharacters, int[] queryIndices) {

        s = str.toCharArray();

        int n = s.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];
            char newChar = queryCharacters.charAt(i);

            s[index] = newChar;

            update(1, 0, n - 1, index, newChar);

            ans[i] = tree[1].max;
        }

        return ans;
    }

    private void build(int node, int start, int end) {

        if (start == end) {
            tree[node] = new Node(s[start]);
            return;
        }

        int mid = start + (end - start) / 2;

        build(node * 2, start, mid);
        build(node * 2 + 1, mid + 1, end);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int start, int end, int index, char c) {

        if (start == end) {
            tree[node] = new Node(c);
            return;
        }

        int mid = start + (end - start) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index, c);
        } else {
            update(node * 2 + 1, mid + 1, end, index, c);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node left, Node right) {

        Node res = new Node();

        res.len = left.len + right.len;

        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        // Prefix
        res.prefix = left.prefix;

        if (left.prefix == left.len &&
            left.rightChar == right.leftChar) {

            res.prefix = left.len + right.prefix;
        }

        // Suffix
        res.suffix = right.suffix;

        if (right.suffix == right.len &&
            left.rightChar == right.leftChar) {

            res.suffix = right.len + left.suffix;
        }

        // Maximum
        res.max = Math.max(left.max, right.max);

        // Repeating substring crosses the boundary
        if (left.rightChar == right.leftChar) {
            res.max = Math.max(
                res.max,
                left.suffix + right.prefix
            );
        }

        return res;
    }
}