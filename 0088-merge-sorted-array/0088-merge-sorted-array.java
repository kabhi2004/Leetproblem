import java.util.Arrays;

class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = m - 1;        // last valid index in nums1
        int j = n - 1;        // last index in nums2

        // Use for loop for i
        for (int i = m + n - 1; i >= 0; i--) {
            if (j < 0) {
                // No more elements in nums2, stop
                break;
            }

            if (k >= 0 && nums1[k] > nums2[j]) {
                nums1[i] = nums1[k];
                k--;
            } else {
                nums1[i] = nums2[j];
                j--;
            }
        }

        System.out.println(Arrays.toString(nums1));
    }
}
