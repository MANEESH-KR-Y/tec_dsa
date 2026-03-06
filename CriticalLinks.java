import java.util.*;

class CriticalLinks {

    int time = 0;

    public List<List<Integer>> findBridges(int n, List<List<Integer>> edges) {

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0;i<n;i++)
            graph.add(new ArrayList<>());

        for(List<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] disc = new int[n];
        int[] low = new int[n];

        boolean[] visited = new boolean[n];

	List<List<Integer>> bridges = new ArrayList<>();

        for(int i=0;i<n;i++)
            if(!visited[i])
                dfs(i,-1,graph,disc,low,visited,bridges);

        return bridges;
    }

    private void dfs(int u, int parent,
                     List<List<Integer>> graph,
                     int[] disc, int[] low,
                     boolean[] visited,
                     List<List<Integer>> bridges) {

        visited[u] = true;
        disc[u] = low[u] = ++time;

        for(int v : graph.get(u)) {

            if(v == parent)
                continue;

            if(!visited[v]) {

                dfs(v,u,graph,disc,low,visited,bridges);

                low[u] = Math.min(low[u], low[v]);

                if(low[v] > disc[u])
                    bridges.add(Arrays.asList(u,v));
            }

            else
                low[u] = Math.min(low[u], disc[v]);
        }
    }
}


