class Solution {
    public int findGCD(int[] nums) {
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++)
        {
          if(nums[i]<min)
          {
            min=nums[i];
          }
          if(nums[i]>max)
          {
            max=nums[i];
          }
        }
        while(max>0)
        {
            if(max>min)
            {
               max=max-min;
            }
            else if(max<min)
            {
                min=min-max;
            }
            else
            {
                break;
            }
        }
        return min;
    }
}