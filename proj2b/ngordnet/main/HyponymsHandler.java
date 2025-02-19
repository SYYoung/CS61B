package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;

    public HyponymsHandler(WordNet wn) {
        this.wn = wn;
    }

    public String handle(NgordnetQuery q) {
        // it will invoke WordNet to return the list of hyponyms
        List<String> words = q.words();

        StringBuilder response = new StringBuilder();
        // take the first word and build into a set
        HashSet<String> resultSet = new HashSet<>(wn.getHyponym(words.get(0)));
        for (String w : words) {
            HashSet<String> setB = new HashSet<>(wn.getHyponym(w));
            resultSet.retainAll(setB);
            //response.append(wn.getHyponym(w));
        }
        // convert the result Set to list and then sorted it
        List<String> resultList = new LinkedList<>(resultSet);
        Collections.sort(resultList);
        return resultList.toString();
    }
}
