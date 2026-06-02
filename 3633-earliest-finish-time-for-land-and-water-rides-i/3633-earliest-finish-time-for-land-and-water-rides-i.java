class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int ans=Integer.MAX_VALUE;

            for(int i=0;i<landStartTime.length;i++){
             for(int j=0;j<waterStartTime.length;j++)
             {
                //Land -> Water
                int land=landStartTime[i]+landDuration[i];
                int water=Math.max(land,waterStartTime[j]);
                int finish1=water+ waterDuration[j];

                //Water -> Land
                int Water=waterStartTime[j]+waterDuration[j];
                int Land=Math.max(Water,landStartTime[i]);
                int finish2=Land+landDuration[i];

                ans=Math.min(ans,finish1);
                ans=Math.min(ans,finish2);

                }
            }
          return ans;
        }
}