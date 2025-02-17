package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

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
        for (String w : words) {
            response.append(wn.getHyponym(w));
        }
        return response.toString();
    }
}
