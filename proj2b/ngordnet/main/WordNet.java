package ngordnet.main;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WordNet {
    // wrapper for a graph
    private int INVALID_NODE_NUMBER = Integer.MAX_VALUE;
    private Graph graph;

    public WordNet(String sysnetFName, String hypoFName) {
        graph = new Graph();
        // build the graph -> add all the edges
        // 1. read syssets.txt first
        readSysnetFile(sysnetFName);
        readHypoFile(hypoFName);
    }

    public WordNet() {
        graph = new Graph();
    }

    public List<String> getHyponym(String word) {
        // get the corresponding node number first
        int from = graph.getNodeNumber(word);
        if (from == INVALID_NODE_NUMBER)
            return new LinkedList<>();
        List<Integer> hypoNumList = graph.traverse(from);
        List<String> hypoNameList = new LinkedList<>();
        // for each node number, get the string back
        for (int i : hypoNumList) {
            hypoNameList.add(graph.getNodeName(i));
        }
        Collections.sort(hypoNameList);
        return hypoNameList;
    }

    private void readSysnetFile(String sysnetFName) {
        In in = new In(sysnetFName);
        String line;
        String[] tmpArray;
        while (in.hasNextLine()) {
            line = in.readLine();
            tmpArray = line.split(",");
            int nodeNumber = Integer.parseInt(tmpArray[0]);
            graph.createNode(tmpArray[1], nodeNumber);
        }
    }

    private void readHypoFile(String hypoFname) {
        In in = new In(hypoFname);
        String line;
        String[] tmpArray;
        while (in.hasNextLine()) {
            line = in.readLine();
            tmpArray = line.split(",");
            int from = Integer.parseInt(tmpArray[0]);
            for (int i = 1; i < tmpArray.length; i++) {
                graph.addEdge(from, Integer.parseInt(tmpArray[i]));
            }
        }
    }

    // graph helper function
    public String doSomething() {
        return graph.youDoSomething();
    }

    public static void main(String[] args) {
        String sysFName = "data/wordnet/synsets11.txt";
        String hypoFName = "data/wordnet/hyponyms11.txt";
        WordNet wn = new WordNet(sysFName, hypoFName);
        // test simple case
        String entry = "bbb";
        List<String> resultName = wn.getHyponym(entry);
        System.out.println(resultName);
    }
}
