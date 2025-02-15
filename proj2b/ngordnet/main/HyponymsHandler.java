package ngordnet.main;

import ngordnet.browser.NgordnetQuery;
import ngordnet.browser.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;

public class HyponymsHandler extends NgordnetQueryHandler {

    public HyponymsHandler(NGramMap ngm) {

    }

    public String handle(NgordnetQuery q) {
        return "hello";
    }
}
