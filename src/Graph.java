public class Graph {
    public static int disc[];

        public static final int INFINITY = Integer.MAX_VALUE;
    class Edge {
        int src, dest, weight; //odkud, kam, hodnota
        Edge() {
            src = 0;
            dest = 0;
            weight = 0;
        }
    }

    int V , E;
    Edge edge[];

    Graph(int v, int e){
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; i++){
            edge[i] = new Edge();
        }
    }
    public static void BellmanFord(Graph graph, int src){
        int V = graph.V;
        int E = graph.E;
        disc = new int[V];

        for (int i = 0; i < V; i++){
            disc[i] = INFINITY;
        }
        disc[src] = 0;

        for (int i = 0; i < V; i++){
            for (int j = 0; j < E; j++){
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;

                if (disc[u] != INFINITY &&
                        (disc[u] + weight) < disc[v]){
                   // System.out.println("v " + v + " u " + u);
                    disc[v] = disc[u] + weight;
                }
            }
        }
    }
}
