class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i=0;
        int j=numbers.length-1;
        while(i<j)
        {
            int sum=numbers[i]+numbers[j];
            if(target==sum)
            {
                return new int[]{i+1,j+1};
            }
            else if(target<sum)
            {
                j--;
            }
            else
            {
                i++;
            }
        }
        return new int[] {-1,-1};
        
    }
}