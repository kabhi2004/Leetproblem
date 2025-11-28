import java.util.HashSet;

class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int n : nums) {
            if (set.contains(n)) {
                return true;     // duplicate found
            }
            set.add(n);
        }
        return false;            // no duplicates
    }
}

