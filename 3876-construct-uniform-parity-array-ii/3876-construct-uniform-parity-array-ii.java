class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for (int num : nums1) {
            min = Math.min(min, num);

            if (num % 2 != 0) {
                hasOdd = true;
            }
        }

        // All numbers are even
        if (!hasOdd) {
            return true;
        }

        // If the minimum element is odd,
        // all even elements can subtract it and become odd
        return min % 2 != 0;
    }
}