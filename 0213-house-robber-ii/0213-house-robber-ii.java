class Solution {
    public int func(int n,int start,int[]nums,int[]dp)
    {
        if(n<start)
        {
            return 0;
        }
        if(start==n)
        {
            return nums[start];
        }
        if(dp[n]!=-1)
        {
            return dp[n];
        }
        int take =nums[n]+func(n-2,start,nums,dp);
        int notTake=func(n-1,start,nums,dp);
        return dp[n]=Math.max(take,notTake);
    }

    public int rob(int[] nums) {
        int n=nums.length;
        int [] dp=new int[n+1];
        int [] dp2=new int [n+1];
        Arrays.fill(dp,-1);
        Arrays.fill(dp2,-1);
        if(n==1)
        {
            return nums[0];
        }
        int first=func(n-1,1,nums,dp);
        int last=func(n-2,0,nums,dp2);
        return Math.max(first,last);

        
    }
}