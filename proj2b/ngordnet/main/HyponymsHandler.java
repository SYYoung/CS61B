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
    private NGramMap ngm;

    public HyponymsHandler(WordNet wn, NGramMap ngm) {
        this.wn = wn;
        this.ngm = ngm;
    }

    public String handle(NgordnetQuery q) {
        // it will invoke WordNet to return the list of hyponyms
        List<String> words = q.words();
        int startYear = (int)q.startYear();
        int endYear = (int)q.endYear();
        int k = (int)q.k();
        System.out.println("Start Year: " + q.startYear());
        System.out.println("End Year: " + q.endYear());

        StringBuilder response = new StringBuilder();
        // take the first word and build into a set
        HashSet<String> resultSet = new HashSet<>(wn.getHyponym(words.get(0)));
        for (String w : words) {
            HashSet<String> setB = new HashSet<>(wn.getHyponym(w));
            resultSet.retainAll(setB);
            //response.append(wn.getHyponym(w));
        }
        List<String> resultList = new LinkedList<>(resultSet);
        //String[] resultList = (String[]) resultSet.toArray();

        // now take care the k, start year and end year
        // convert the result Set to list and then sorted it
        if (k != 0){
            // call helper function to filter the first k popular words
            // pass the input set, k, start year, end year
            // return the result set
            HyponymsFilter hyFilter = new HyponymsFilter(ngm);
            List<String> tempList = hyFilter.filterByPopularity(startYear, endYear, k, resultSet);
            if (tempList.size() > k)
                resultList = tempList.subList(0, k-1);
        }
        //resultList = new LinkedList<>(resultSet);
        Collections.sort(resultList);
        return resultList.toString();
    }
}
