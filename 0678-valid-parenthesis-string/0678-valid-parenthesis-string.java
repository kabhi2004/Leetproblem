class Solution {
    public boolean checkValidString(String s) {

        int low = 0;
        int high = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                low++;
                high++;
            } 
            else if (c == ')') {
                low--;
                high--;
            } 
            else { // '*'
                low--;      // '*' acts as ')'
                high++;     // '*' acts as '('
            }

            // Minimum cannot be negative
            low = Math.max(0, low);

            // Even maximum is negative -> impossible
            if (high < 0) {
                return false;
            }
        }

        return low == 0;
    }
}