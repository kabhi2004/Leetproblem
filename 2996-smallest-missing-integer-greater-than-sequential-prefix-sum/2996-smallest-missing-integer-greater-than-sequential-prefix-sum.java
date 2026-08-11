import java.util.*;

class Solution {
    public int missingInteger(int[] nums) {
        
        // Step 1: Find sum of longest sequential prefix
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }

        // Step 2: Store all elements in a HashSet
        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        // Step 3: Find smallest missing integer >= sum
        int x = sum;

        while (set.contains(x)) {
            x++;
        }

        return x;
    }
}
