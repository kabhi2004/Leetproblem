// class Solution {
//     public int longestValidParentheses(String s) {
//         Stack<Character> st = new Stack<>();
//         if (s.length() == 0) {
//             return 0;
//         }
//         int longest = 0;
//         for (char c : s.toCharArray()) {
//             int count=0;
//             if (st.isEmpty()) {
//                 st.push(c);
//                 continue;
//             }
//             while(st.peek() != c && st.peek() == '(') {
//                 st.pop();
//                 count = count + 2;
//             } 
//                 st.push(c);
//             longest=Math.max(count,longest);

//         }
//         return longest;

//     }
// }
class Solution {
    public int longestValidParentheses(String s) {

        Stack<Integer> st = new Stack<>();
        st.push(-1);

        int longest = 0;

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                st.push(i);
            } else {

                st.pop();

                if (st.isEmpty()) {
                    st.push(i);
                } else {
                    longest = Math.max(longest, i - st.peek());
                }
            }
        }

        return longest;
    }
}