class Solution {
    public int romanToInt(String s) {
        // Step 1: Map Roman symbols to values
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int total = 0;
        
        // Step 2: Loop through the string
        for (int i = 0; i < s.length(); i++) {
            int current = map.get(s.charAt(i));
            
            // Check if there is a next symbol and if it's larger
            if (i + 1 < s.length() && current < map.get(s.charAt(i + 1))) {
                total -= current; // subtract
            } else {
                total += current; // add
            }
        }
        
        return total;
    }
}
