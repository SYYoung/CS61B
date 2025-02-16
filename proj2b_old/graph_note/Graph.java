package graph_note;

public class Graph {
    public Graph(int V) {

    }

    public void addEdge(int v, int w) {

    }

    Iterable<Integer> adj(int v) {
        return null;
    }

    public int V(){
        return 0;
    }

    public int E(){
        return 0;
    }

    public static int degree(Graph G, int v) {
        int deg = 0;
        for (int w : G.adj(v)) {
            deg += 1;
        }
        return deg;
    }

    public static void print(Graph G) {
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                System.out.println(v + "--" + w);
            }
        }
    }


}
