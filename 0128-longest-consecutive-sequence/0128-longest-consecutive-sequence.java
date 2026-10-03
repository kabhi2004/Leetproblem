class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> values=new HashSet<>();
        for(int value:nums)
        {
            values.add(value);
        }
        int longest=0;
     
        for( int val:values)
        {
            int count=1;
            int next=val+1;
            if(values.contains(val-1))
            {
                continue;
            }
            
            while(values.contains(next))
            {
                count++;
                next++;
            }
             longest=Math.max(count,longest);
        }
        return longest;

        
    }
}