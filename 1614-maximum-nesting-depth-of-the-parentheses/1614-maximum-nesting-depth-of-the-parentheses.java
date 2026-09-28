class Solution {
    public int maxDepth(String s) {
        int n=s.length();
        int count=0;
        int max=0;
        for(int i=0;i<n;i++)
        {
          char c=s.charAt(i);
          if(c=='(')
          {
            count++;
            max=Math.max(max,count);
          }
          else if(c==')')
          {
            count--;
          }
        }
        return max;
        
    }
}