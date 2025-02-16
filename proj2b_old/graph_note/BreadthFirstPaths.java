package graph_note;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;

    public BreadthFirstPaths(Graph G, int s) {
        int numVertex = G.V();
        marked = new boolean[numVertex];
        edgeTo = new int[numVertex];

        for (int i = 0; i < numVertex; i++) {
            marked[i] = false;
            edgeTo[i] = Integer.MAX_VALUE;
        }
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        Queue<Integer> fringe = new ArrayDeque<>();
        fringe.add(s);
        marked[s] = true;
        while (!fringe.isEmpty()) {
            int v = fringe.remove();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    fringe.add(w);
                    marked[w] = true;
                    edgeTo[w] = v;
                }
            }
        }
    }


}
