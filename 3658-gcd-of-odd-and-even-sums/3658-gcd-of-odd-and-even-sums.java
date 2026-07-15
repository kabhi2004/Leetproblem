class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd=0;
        int sumEven=0;
        for(int i=1;i<=n+n;i++)
        {
            if(i%2==0)
            {
                sumEven=sumEven+i;
            }
            else
            {
                sumOdd=sumOdd+i;
            }
        }
        while(sumEven!=sumOdd)
        {
            if(sumEven<sumOdd)
            {
                sumOdd=sumOdd-sumEven;
            }
            else
            {
                sumEven=sumEven-sumOdd;
            }
            
        }
        return sumOdd;
        
    }
}