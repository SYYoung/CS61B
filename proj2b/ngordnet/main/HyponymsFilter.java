package ngordnet.main;

import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.util.*;

public class HyponymsFilter {
    NGramMap ngm;
    public HyponymsFilter(NGramMap ngm) {
        this.ngm = ngm;
    }

    public List<String> filterByPopularity(int k, HashSet<String> inputSet) {
        int startYear = 1900;
        int endYear = 2020;

        List<String> resultList = filterByPopularity(startYear, endYear, k,  inputSet);
        return resultList;
    }

    public List<String> filterByPopularity(int startYear, int endYear, int k,
                                          HashSet<String> inputSet) {
        TreeMap<Double, String> popularMap = new TreeMap<>(Comparator.reverseOrder());
        List<String> resultList = new LinkedList<>();
        for (String s : inputSet) {
            // get the popularity figure of each word, add them up. if non-zero, put it
            // popularMap
            TimeSeries ts = ngm.weightHistory(s, startYear, endYear);
            if (ts.isEmpty())
                continue;
            double total = 0;
            for (double val: ts.values())
                total += val;
            if (total != 0)
                popularMap.put(total, s);
            /*
            List<Double> val = (List<Double>) ts.values();
            double rating = 0;
            for (double d : val)
                rating += d;
            if (rating != 0)
                popularMap.put(rating, s);
             */
        }
        if (!popularMap.isEmpty()) {
            List<String> tmpList = new LinkedList<>(popularMap.values());
            if (k < popularMap.size())
                resultList = tmpList.subList(0, k);
            else
                resultList = tmpList;

        }
        return resultList;
    }
}
