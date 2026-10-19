class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);
       ArrayList<Integer> arr=new ArrayList<>();
        int count=0;
        for(int i=0;i<nums.length;i++)
        {
            if(nums[i]==target)
            {
              arr.add(i);
            }
        }
        return arr;
        
    }
}