package exercise3;

import common.StringUtils;
import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;
import java.util.concurrent.Callable;

public class WordCounter implements Callable{

    private final String documentUrl;
    private final String wordToCount;

    public WordCounter(String documentUrl, String wordToCount) {
        this.documentUrl = documentUrl;
        this.wordToCount = wordToCount;
    }

    public Object call() throws Exception {
        HtmlDocument document = new GazetaHtmlDocument(documentUrl);
        
        String content = document.getContent();
        content = content.toLowerCase();
        return StringUtils.countOccurrences(content, wordToCount);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
