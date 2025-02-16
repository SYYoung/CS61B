package ngordnet.main;

import java.util.*;

public class Graph {
    // variables
    // the adj list can be implemented as hashmap, key is the source, value is the nodes which the source
    // connected to
    private HashMap<Integer, LinkedList<Integer>> gr;
    private HashSet<Integer> vertices;

    public Graph() {
        gr = new HashMap<>();
        vertices = new HashSet<>();
    }

    public void createNode(int node) {
        if (!vertices.contains(node)) {
            LinkedList<Integer> edge = new LinkedList<>();
            gr.put(node, edge);
            vertices.add(node);
        }
    }

    public void addEdge(int from, int to) {
        LinkedList<Integer> edgeList = gr.get(from);
        edgeList.add(to);
        gr.put(from, edgeList);
    }

    public List<Integer> getNodes() {
       return new ArrayList<Integer>(gr.keySet());
    }

    public List<Integer> getNeighbor(int from) {
        return new LinkedList<Integer>(gr.get(from));
    }

    public List<Integer> traverse(int from) {
        Queue<Integer> fringe = new ArrayDeque<>();
        LinkedList<Integer> decendant = new LinkedList<>();
        fringe.add(from);
        while (!fringe.isEmpty()) {
            int v = fringe.remove();
            decendant.add(v);
            fringe.addAll(getNeighbor(v));
        }
        return decendant;
    }

    // resizing
    public String youDoSomething() {
        return "Hello!";
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        for (int i = 0; i <= 10; i++) {
            g.createNode(i);
        }
        // create edge: from hyponyms11
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(8, 10);
        g.addEdge(9, 10);

        List<Integer> list1 = g.getNodes();
        // test neighbors
        for (int i = 0 ; i <=10; i++) {
            list1 = g.getNeighbor(i);
        }
        // test traverse
        for (int i = 0; i <= 10; i++) {
            list1 = g.traverse(i);
        }
    }
}
