// class Solution {
//     public int minOperations(int[] nums, int x) {
//         int n=nums.length;
//         int i=0;
//         int j=n-1;
//         int count=0;
//         while(i<j)
//         {
//             if(nums[i]>=nums[j]&&x>=nums[i])
//             {
//                 count++;
//                 x=x-nums[i];
//                 i++;
                
//             }
//             else if(nums[j]>nums[i]&&x>=nums[j])
//             {
//                 count++;
//                 x=x-nums[j];
//                 j--;
                
//             }
//             else 
//             {
//                 return -1;
//             }

//         }
//         return count;
        
//     }
// }
class Solution {
    public int minOperations(int[] nums, int x) {

        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int target = total - x;

        // We need to remove everything
        if (target == 0) {
            return nums.length;
        }

        int left = 0;
        int sum = 0;
        int maxLen = -1;

        for (int right = 0; right < nums.length; right++) {

            sum += nums[right];

            while (sum > target && left <= right) {
                sum -= nums[left];
                left++;
            }

            if (sum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        if (maxLen == -1) {
            return -1;
        }

        return nums.length - maxLen;
    }
}