class Solution {
    public String smallestSubsequence(String s) {
        Stack<Character> st=new Stack<>();
        int[] Last=new int[26];
        boolean[] vis= new boolean[26];
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<s.length();i++)
        {
            Last[s.charAt(i)-'a']=i;
        }
        for(int i=0;i<s.length();i++)
        {
            char ch=s.charAt(i);
            
            if(vis[ch-'a']==true)
            {
                continue;
            }
            while(!st.empty()&&st.peek()>ch&&Last[st.peek()-'a']>i)
            {
                char remove=st.pop();
                vis[remove-'a']=false;
            }
            st.push(ch);
            vis[ch-'a']=true;
        }
        while (!st.empty()) {
        ans.append(st.pop());
      }
       return ans.reverse().toString();
    }
}