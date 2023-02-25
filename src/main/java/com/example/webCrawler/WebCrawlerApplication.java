package com.example.webCrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WebCrawlerApplication {

	private static final String URL = "https://www.javatpoint.com/";
	public static void main(String[] args) {

		ConfigurableApplicationContext webCrawler = SpringApplication.run(WebCrawlerApplication.class, args);
		WebCrawler webCrawler1 = webCrawler.getBean(WebCrawler.class);
		webCrawler1.crawlWebsite(URL,0);

	}

}
