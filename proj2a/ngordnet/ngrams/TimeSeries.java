package ngordnet.ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    // TODO: Add any necessary static/instance variables.

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        //super();
        // TODO: Fill in this constructor.
        super(ts.subMap(startYear, endYear));
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        List<Integer> arr = new ArrayList<>();
        arr.addAll(keySet());
        return arr;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Double> arr = new ArrayList<>();
        arr.addAll(values());
        return arr;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        // 1. get the key of this TimeSeries and the key of ts
        // 2. combine the set
        // 3. for each key, get the values from both TimeSeries.
        // 4. add them up, then put this new entry in the new TimeSeries
        TimeSeries combineTS = new TimeSeries();
        Set<Integer> unionKey = new HashSet<>(keySet());
        unionKey.addAll(ts.keySet());
        for (Integer k : unionKey) {
            double v1 = 0, v2 = 0;
            if (containsKey(k)) {v1 = get(k);}
            if (ts.containsKey(k)) {v2 = ts.get(k);}
            // add this entry into new TimeSeries
            combineTS.put(k, v1+v2);
        }
        return combineTS;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries divideTS = new TimeSeries();
        for (Integer k : keySet()) {
            double v1 = 0, v2 = 0;
            v1 = get(k);
            if (!ts.containsKey(k))
                throw new IllegalArgumentException();
            v2 = ts.get(k);
            // add this entry into new TimeSeries
            divideTS.put(k, v2/v1);
        }
        return divideTS;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
