class Solution {
public:
    bool check(vector<int>& nums) {
        int rotate=0;
        for(int i=0;i<nums.size();i++)
        {
            if(nums[i]<=nums[(i+1)%nums.size()]){
                   
           }
           else if(nums[i]>nums[(i+1)%nums.size()]){
            rotate++;
           }
          
        }
        if(rotate<=1)
        return true;
        else
        return false;
        
    }
};