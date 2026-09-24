class Solution {
    public int smallestIndex(int[] nums) {
        int len=nums.length;
        int minVal=1001;
        for(int i=0;i<len;i++)
        {
            int n=nums[i];
            int sum=0;
            while(n>0)
            {
             sum=sum+(n%10);
             n=n/10;
            }
            if(i==sum){
                minVal=Math.min(sum,minVal);
            }
        }
       return minVal==1001?-1:minVal;
        
    }
}