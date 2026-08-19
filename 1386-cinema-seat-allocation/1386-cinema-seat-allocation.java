import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        
        // Store reserved seats for each affected row
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int seatNumber = seat[1];

            // Bit representation of reserved seats
            map.put(row, map.getOrDefault(row, 0) | (1 << seatNumber));
        }

        // Rows with no reserved seats can hold 2 families
        int result = (n - map.size()) * 2;

        for (int mask : map.values()) {

            boolean left = isAvailable(mask, 2, 3, 4, 5);
            boolean middle = isAvailable(mask, 4, 5, 6, 7);
            boolean right = isAvailable(mask, 6, 7, 8, 9);

            if (left && right) {
                // Two non-overlapping groups
                result += 2;
            } 
            else if (left || middle || right) {
                // Only one group can fit
                result += 1;
            }
        }

        return result;
    }

    private boolean isAvailable(int mask, int... seats) {
        for (int seat : seats) {
            if ((mask & (1 << seat)) != 0) {
                return false;
            }
        }
        return true;
    }
}