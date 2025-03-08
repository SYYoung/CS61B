import java.util.List;

public class Graph {
    List<Integer> nodes;

}

/*
public class Dijkstra {
    public Dijkstra() {
        PQ = new PriorityQueue();
        PQ.add(A, 0); // for A*, it is PQ.add(A, h(A));
        PQ.add(v, infinity);

        distTo = new HashMap<Integer, Integer>();
        distTo[A] 0 0;
        distTo[v] = infinity

        while (! PQ.isEmpty()) {
            poppedNode, poppedPriority = PQ.pop(); // if A*, add one more line: if(poppedNode==goal) break;

            for (child in poppedNode.children()) {
                if (PQ.contains(child)) {
                    potentialDist = distTo[poppedNode] + edgeWeight(poppedNode, child);
                    if (potentialDist < distTo[child]) {
                        distTo.put(child, potentialDist);
                        PQ.changePriority(child, potentialDist); // for A*, PQ.changePriority(child,
                                                                    //potentialDist + h(child))
                        edgeTo[child] = poppedNode;
                    }
                }
            }
        }
}

public class Kruskal {
    while there are still nodes not in MST:
        add the lightest edge that does not create a cycle
        add the new node to the set of nodes in the MST
}

public class Prim {
    start with any node
    add that node to the set of nodes in the MST
    while there are still nodes not in the MST:
        add the lightest edge from a node in the MST that leads to a new node that is unvisited
        add the new node to the set of nodes in the MST
}
 */
