import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int totalOnes = 0;
        
        // Count total '1's in the string
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') totalOnes++;
        }

        // 1. Identify all blocks of '0's and store their [start, end] indices
        List<int[]> blockList = new ArrayList<>();
        int idx = 0;
        while (idx < n) {
            if (s.charAt(idx) == '0') {
                int start = idx;
                while (idx < n && s.charAt(idx) == '0') idx++;
                blockList.add(new int[]{start, idx - 1});
            } else {
                idx++;
            }
        }

        int m = blockList.size();
        int[] L_arr = new int[m];
        int[] R_arr = new int[m];
        int[] lengths = new int[m];
        
        for (int i = 0; i < m; i++) {
            L_arr[i] = blockList.get(i)[0];
            R_arr[i] = blockList.get(i)[1];
            lengths[i] = R_arr[i] - L_arr[i] + 1;
        }

        // 2. Build a Sparse Table (RMQ) for the sums of adjacent '0'-blocks
        int k = m - 1;
        int[][] st = null;
        int[] log2 = null;
        
        if (m >= 2) {
            int[] P = new int[k];
            for (int i = 0; i < k; i++) {
                P[i] = lengths[i] + lengths[i + 1];
            }
            
            log2 = new int[k + 1];
            for (int i = 2; i <= k; i++) {
                log2[i] = log2[i / 2] + 1;
            }
            
            int maxLog = log2[k] + 1;
            st = new int[maxLog][k];
            
            for (int i = 0; i < k; i++) {
                st[0][i] = P[i];
            }
            
            for (int j = 1; j < maxLog; j++) {
                for (int i = 0; i + (1 << j) <= k; i++) {
                    st[j][i] = Math.max(st[j - 1][i], st[j - 1][i + (1 << (j - 1))]);
                }
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        
        // 3. Process Queries
        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];
            
            // Find the index of the first block that reaches at or past `l`
            int a = bisectLeft(R_arr, l);
            // Find the index of the last block that begins at or before `r`
            int b = bisectRight(L_arr, r) - 1;
            
            // If less than 2 distinct '0'-blocks intersect the query, no trade is possible
            if (a >= b) {
                ans.add(totalOnes);
                continue;
            }
            
            // Account for string boundaries truncating the outermost intersecting '0'-blocks
            int zA = Math.min(R_arr[a], r) - Math.max(L_arr[a], l) + 1;
            int zB = Math.min(R_arr[b], r) - Math.max(L_arr[b], l) + 1;
            
            int gain = 0;
            if (a + 1 == b) {
                // Only two blocks intersect
                gain = zA + zB;
            } else {
                // 3 or more blocks intersect, query internal fixed pairwise-sums
                gain = Math.max(zA + lengths[a + 1], lengths[b - 1] + zB);
                int midMax = queryP(st, log2, a + 1, b - 2);
                gain = Math.max(gain, midMax);
            }
            
            ans.add(totalOnes + gain);
        }
        
        return ans;
    }
    
    // O(1) Range Maximum Query using the Sparse Table
    private int queryP(int[][] st, int[] log2, int L, int R) {
        if (L > R || st == null) return 0;
        int j = log2[R - L + 1];
        return Math.max(st[j][L], st[j][R - (1 << j) + 1]);
    }
    
    // Custom binary search implementations
    private int bisectLeft(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private int bisectRight(int[] arr, int target) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}