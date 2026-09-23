class Solution {
    public int func(int n,int[]nums,int[]dp)
    {
        if(n<0)
        {
            return 0;
        }
        if(n==1)
        {
            return Math.max(nums[n],nums[n-1]);
        }
        if(dp[n]!=-1)
        {
            return dp[n];
        }
        int take =nums[n]+func(n-2,nums,dp);
        int notTake=func(n-1,nums,dp);
        return dp[n]=Math.max(take,notTake);
    }

    public int rob(int[] nums) {
        int n=nums.length;
        int [] dp=new int[n+1];
        Arrays.fill(dp,-1);
        return func(n-1,nums,dp);
        
    }
}