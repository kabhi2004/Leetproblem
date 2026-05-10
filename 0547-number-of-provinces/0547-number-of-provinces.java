class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n= isConnected.length;
        ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            adj.add(new ArrayList<>());
    }

    //Converting matrix into adj list
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
               if(isConnected[i][j]==1&&i!=j)
               {
                adj.get(i).add(j);
               }
            }
        }
        int count=0;
        boolean vis[]=new boolean[n];
        for(int i=0;i<n;i++)
        {
            if(vis[i]==false)
            {
                count++;
                dfs(adj,vis,i);
            }
        }
        return count;

    }

    public static void dfs(ArrayList<ArrayList<Integer>> adj,boolean vis[],int i)
    {

        vis[i]=true;
        for(int  j=0;j<adj.get(i).size();j++)
        {
            int node=adj.get(i).get(j);
            if(vis[node]==false)
            {
                dfs(adj,vis,node);
            }
        }

    }
}