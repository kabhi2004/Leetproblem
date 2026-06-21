class Solution {
    public int maxIceCream(int[] costs, int coins) {
        int maxCost=Integer.MIN_VALUE;
        for(int cost:costs){
            maxCost=Math.max(cost,maxCost);
        }
        int [] freq= new int[maxCost+1];

        for(int cost:costs)
        {
            freq[cost]++;
        }
        int count=0;
        for(int cost=1;cost<freq.length;cost++)
        {
            if(cost>coins)
            {
                break;
            }
           

            int buy=Math.min(freq[cost],coins/cost);
            count+=buy;
            coins-=buy*cost;
             if(freq[cost]==0)
            {
                continue;
            }

        }
        return count;


        
    }
}