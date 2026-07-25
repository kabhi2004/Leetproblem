class Solution {
    public int maxProduct(int n) {
        int first=Integer.MIN_VALUE;
        int second=Integer.MIN_VALUE;
        while(n>0)
        {
          int dig=n%10;
          if(dig>first)
          {
            second=first;
            first=dig;
          }
          else if(dig>second&&second<first)
          {
            second=dig;
          }
          n=n/10;

        }
        return first*second;

    }
}