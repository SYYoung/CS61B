package ngordnet.main;

import java.util.*;

public class Graph {
    // variables
    // the adj list can be implemented as hashmap, key is the source, value is the nodes which the source
    // connected to
    private HashMap<Integer, Set<Integer>> gr;
    private HashMap<String, Set<Integer>> name;
    private HashMap<Integer, String> vertices;

    public Graph() {
        gr = new HashMap<>();
        vertices = new HashMap<>();
        name = new HashMap<>();
    }

    public void createNode(String nodeName, int node) {
        Set<Integer> nodeSet = name.getOrDefault(nodeName, new HashSet<Integer>());
        nodeSet.add(node);
        name.put(nodeName, nodeSet);
        createNode(node);
        vertices.put(node, nodeName);
    }

    public void createNode(int node) {
        if (!vertices.containsKey(node)) {
            Set<Integer> edge = new HashSet<>();
            gr.put(node, edge);
            //vertices.put(node);
        }
    }

    public void addEdge(int from, int to) {
        Set<Integer> edgeList = gr.get(from);
        edgeList.add(to);
        gr.put(from, edgeList);
    }

    public List<Integer> getNodes() {
        List<Integer> nodeList = new LinkedList(gr.keySet());
        return nodeList;
    }

    public List<Integer> getNodeNumber(String nodeName) {
        List<Integer> nodeList = new LinkedList(name.getOrDefault(nodeName, new HashSet<>()));
        return nodeList;
    }

    public String getNodeName(int num) {
        // given the node number, get the corresponding node name
        return vertices.get(num);
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
    public List<String> getAllNodeName() {
        // get the values from the map vertices
        List<String> val = new LinkedList<>();
        if (!vertices.isEmpty()) {
            val.addAll(vertices.values());
        }
        /* old code
        if (!name.isEmpty())
            val =  new HashMap<>(name);
        else
            val = new HashMap<>();

         */
        return val;
    }

    // resizing
    public String youDoSomething() {
        return "Hello!";
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        for (int i = 0; i <= 10; i++) {
            g.createNode(Integer.toString(i), i);
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
        if (!g.name.isEmpty())
            System.out.print(g.name);
        System.out.println();

        // test neighbors
        for (int i = 0 ; i <=10; i++) {
            list1 = g.getNeighbor(i);
            System.out.print(i + ": ");
            System.out.println(list1);
        }
        // test traverse
        for (int i = 0; i <= 10; i++) {
            list1 = g.traverse(i);
            System.out.print(list1);
        }
        System.out.println();

        // test printAllNodeName
        List<String> list2;
        list2 = g.getAllNodeName();
        for (String s : list2)
            System.out.println(s);
    }
}
