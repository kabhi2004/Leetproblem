class Solution {
    class Edge{
        int src;
        int des;
        int wie;
        Edge(int src,int des,int wie)
        {
            this.src=src;
            this.des=des;
            this.wie=wie;
        }
    }
    public void createGraph(ArrayList<Edge> [] graph,int[][] roads,int n )
    {
        for(int i=1;i<=n;i++)
        {
            graph[i]=new ArrayList<>();
        }
        for(int[] road:roads)
        {
            int u=road[0];
            int v=road[1];
            int w=road[2];
            graph[u].add(new Edge(u,v,w));
            graph[v].add(new Edge(v,u,w));
        }

    }
    public  int bfs(ArrayList<Edge>[] graph,int n)
    {
        boolean[] vis=new boolean[n+1];
        Queue<Integer> q=new LinkedList<>();
        int min=Integer.MAX_VALUE;
         

        q.add(1);
        vis[1] = true;

        while (!q.isEmpty()) {

            int curr = q.remove();

            for (Edge e : graph[curr]) {

                min = Math.min(min, e.wie);

                if (!vis[e.des]) {
                    vis[e.des] = true;
                    q.add(e.des);
                }
            }
            }
            return min;
    }

      public int minScore(int n, int[][] roads) {
        ArrayList<Edge>[] graph=new ArrayList[n+1];
        createGraph(graph,roads,n);
       return bfs(graph,n);
        
    }
}