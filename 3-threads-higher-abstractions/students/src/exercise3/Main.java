package exercise3;

import common.html.GazetaHtmlDocument;
import common.html.HtmlDocument;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws Exception {
        HtmlDocument rootDocument = new GazetaHtmlDocument("http://wiadomosci.gazeta.pl/");
        Set<String> links = rootDocument.getLinks();
        String wordToFound = "sikorski";

        ExecutorService executor = Executors.newCachedThreadPool();

        List<Future<Integer>> results = new ArrayList();
        // TODO: Create list of results of type List<Future<Integer>>

        for (String link : links) {
            results.add(executor.submit(new WordCounter(link, wordToFound)));
            // TODO: Create new WordCounter and submit it to executorService
            // TODO: Store Future object in list of results
        }

        executor.shutdown();
        // TODO: shutdown executor

        int numberOfWords = 0;
        Iterator iterator = results.iterator();
        while (iterator.hasNext()) {
            numberOfWords += (Integer)((Future)iterator.next()).get();
        }
        // TODO: Iterate over list of results and for each Future invoke get() method
        // TODO: add value returned from get() method to numberOfWords variable

        System.out.printf("Number of words '%s': %d", wordToFound, numberOfWords);
    }
}
