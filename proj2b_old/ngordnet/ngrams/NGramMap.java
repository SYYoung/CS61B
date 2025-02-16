package ngordnet.ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    // TODO: Add any necessary static/instance variables.
    private HashMap<String, TimeSeries> wordCountMap;
    private TimeSeries totalCountTS;
    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        // 1. read countsFilename and build totalCountTS
        totalCountTS = new TimeSeries();
        In in = new In(countsFilename);
        // countsFile is in csv format, therefore read the whole line at a time
        String[] tmpArray;
        String line;
        while (in.hasNextLine()) {
            line = in.readLine();
            tmpArray = line.split(",");
            int year = Integer.parseInt(tmpArray[0]);
            double total = Double.parseDouble(tmpArray[1]);
            totalCountTS.put(year, total);
        }
        // 2. read wordsFilename and build wordCountMap
        wordCountMap = new HashMap<>();
        in = new In(wordsFilename);
        while (in.hasNextLine()) {
            if (in.isEmpty())
                break;
            String word = in.readString();
            int year = in.readInt();
            double count = in.readDouble();
            int dummy = in.readInt();
            TimeSeries ts;
            if (wordCountMap.containsKey(word)) {
                ts = wordCountMap.get(word);
            }
            else {
                ts = new TimeSeries();
            }
            ts.put(year, count);
            wordCountMap.put(word, ts);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries ts;
        if (wordCountMap.containsKey(word)) {
            ts = new TimeSeries(wordCountMap.get(word), startYear, endYear);
        }
        else {
            ts = new TimeSeries();
        }
        return ts;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy,
     * not a link to this NGramMap's TimeSeries. In other words, changes made
     * to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy".
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        TimeSeries ts = countHistory(word, MIN_YEAR, MAX_YEAR);
        return ts;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        TimeSeries ts = new TimeSeries(totalCountTS, MIN_YEAR, MAX_YEAR);
        return ts;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        // 1. obtain the TimeSeries of the word in wordCountMap in that period
        // 2. then divide each value with the corresponding entry in totalCountTS
        if (!wordCountMap.containsKey(word))
            return new TimeSeries();
        TimeSeries ts = new TimeSeries(wordCountMap.get(word), startYear, endYear);
        TimeSeries relFreq = ts.dividedBy(totalCountTS);
        return relFreq;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to
     * all words recorded in that year. If the word is not in the data files, return an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        return weightHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS
     * between STARTYEAR and ENDYEAR, inclusive of both ends. If a word does not exist in
     * this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries totalTS = new TimeSeries();
        for (String s: words) {
            TimeSeries ts2 = weightHistory(s, startYear, endYear);
            totalTS = totalTS.plus(ts2);
        }
        return totalTS;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        return summedWeightHistory(words, MIN_YEAR, MAX_YEAR);
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
