package ngordnet.main;

import ngordnet.browser.NgordnetServer;
import ngordnet.ngrams.NGramMap;

public class Main {
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();
        String wordFile = "./data/ngrams/top_49887_words.csv";
        String countFile = "./data/ngrams/total_counts.csv";

        // use a smaller file for testing
        //String synsetFile = "./data/wordnet/synsets11.txt";
        //String hyponymFile = "./data/wordnet/hyponyms11.txt";

        String synsetFile = "./data/wordnet/synsets.txt";
        String hyponymFile = "./data/wordnet/hyponyms.txt";


        // comment out till 2nd part of proj2b
        //NGramMap ngm = new NGramMap(wordFile, countFile);
        WordNet wn = new WordNet();

        hns.startUp();
        // comment out till 2nd part of proj2b
        //hns.register("history", new HistoryHandler(ngm));
        //hns.register("historytext", new HistoryTextHandler(ngm));
        hns.register("hyponyms", new HyponymsHandler(wn));
    }
}
