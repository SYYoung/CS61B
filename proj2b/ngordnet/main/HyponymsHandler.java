package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;

    public HyponymsHandler(WordNet wn) {
        this.wn = wn;
    }

    public String handle(NgordnetQuery q) {
        // it will invoke WordNet to return the list of hyponyms

        return wn.doSomething();
        //return "hello";
    }
}
