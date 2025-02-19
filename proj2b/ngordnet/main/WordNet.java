package ngordnet.main;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class WordNet {
    // wrapper for a graph
    private int INVALID_NODE_NUMBER = Integer.MAX_VALUE;
    private Graph graph;
    private HashMap<String, LinkedList<String>> wordChain;

    public WordNet(String sysnetFName, String hypoFName) {
        graph = new Graph();
        wordChain = new HashMap<>();
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
        //List<Integer> fromList = graph.getNodeNumber(word);
        // from wordChain, get all related names associated with the input word
        List<String> relatedWordList = wordChain.getOrDefault(word, new LinkedList<>());
        if (relatedWordList.isEmpty())
            return new LinkedList<>();
        List<Integer> fromList = new LinkedList<>();
        for (String w : relatedWordList)
            fromList.addAll(graph.getNodeNumber(w));
        if (fromList.isEmpty())
            return new LinkedList<>();
        List<Integer> hypoNumList = new LinkedList<>();
        for (int from: fromList)
            hypoNumList.addAll(graph.traverse(from));
        List<String> hypoNameList = new LinkedList<>();
        // for each node number, get the string back
        for (int i : hypoNumList) {
            hypoNameList.add(graph.getNodeName(i));
        }
        // break down each individual word in the list
        Set<String> wordSet = new HashSet<>();
        for (String s : hypoNameList) {
            String[] subS = s.split(" ");
            wordSet.addAll(List.of(subS));
        }
        hypoNameList = new LinkedList<>(wordSet);
        Collections.sort(hypoNameList);
        return hypoNameList;
    }

    private void updateWordChain(String entry) {
        LinkedList<String> val;
        String[] wordList = entry.split(" ");
        for (String word: wordList) {
            val = wordChain.getOrDefault(word, new LinkedList<>());
            val.add(entry);
            wordChain.put(word, val);
        }
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
            updateWordChain(tmpArray[1]);
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

    public void printWordChain() {
        for (String word: wordChain.keySet()) {
            System.out.print(word + ":   ");
            System.out.println(wordChain.get(word));
        }
    }

    // graph helper function
    public String doSomething() {
        return graph.youDoSomething();
    }

    public static void main(String[] args) {
        String sysFName = "data/wordnet/synsets16.txt";
        String hypoFName = "data/wordnet/hyponyms16.txt";
        WordNet wn = new WordNet(sysFName, hypoFName);
        // test simple case
        String[] entry = {"change", "bbb", "transition"};
        for (String s: entry){
            List<String> resultName = wn.getHyponym(s);
            System.out.println(resultName);
        }
        wn.printWordChain();
    }
}
