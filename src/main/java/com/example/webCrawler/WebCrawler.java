package com.example.webCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class WebCrawler {
    @Autowired
    private SearchRepository searchRepository;

    private HashSet<String> urlLink;

    public WebCrawler() {
        // default implementation
        urlLink = new HashSet<String>();
    }
    public void crawlWebsite(String URL,int depth) {
        if(!urlLink.contains(URL)){

            try {
                if(urlLink.add(URL)){
                    System.out.println(URL);
                }
                Document document = Jsoup.connect(URL).userAgent("chrome").timeout(5000).get();
                String text = document.text().length()<501?document.text():document.text().substring(0,500);
                Elements links = document.select("a[href]");

                depth++;
                if(depth == 2){
                    return;
                }



                for (var link : links) {
                    SearchResult searchResult = new SearchResult();
                    searchResult.setTitle(link.text());
                    searchResult.setLink(link.attr("abs:href"));
                    searchResult.setData(text);
                    searchRepository.save(searchResult);

                    Logger logger = Logger.getLogger(WebCrawler.class.getName());
                    logger.log(Level.INFO,"link:" + link.attr("abs:href"));

                    crawlWebsite(link.attr("abs:href"),depth);

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
