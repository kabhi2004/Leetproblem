class Solution {
public:
    int reverse(int x)
    {
        int rev=0;
            while(x!=0)
            {
                int dig=x%10;
                if((INT_MIN/10)<=rev&&rev<=(INT_MAX/10))
                {
                    rev=rev*10+dig;
                    x=x/10;
                }
                else
                {
                    return 0;
                }
            }
        return rev;
    }
};