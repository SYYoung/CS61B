package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import ngordnet.ngrams.TimeSeries;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    NGramMap ngm;

    public HistoryTextHandler(NGramMap map) {
        ngm = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();

        // invoke NGramMap to get the information back
        //TimeSeries fishWeight = ngm.weightHistory("fish", 1850, 1933);
        TimeSeries resultTS = new TimeSeries();
        String response = "";
        for (String w: words) {
            TimeSeries ts = ngm.weightHistory(w, startYear, endYear);
            response += w + ": " + ts.toString() + "\n";
        }
        /*
        String response = "You entered the following info into the browser:\n";
        response += "Words: " + q.words() + "\n";
        response += "Start Year: " + q.startYear() + "\n";
        response += "End Year: " + q.endYear() + "\n";
         */
        return response;
    }
}
